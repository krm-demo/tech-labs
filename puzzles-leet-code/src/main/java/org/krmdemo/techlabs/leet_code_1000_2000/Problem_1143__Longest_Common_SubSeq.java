package org.krmdemo.techlabs.leet_code_1000_2000;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.lineSeparator;

/**
 * <h3><a href="https://leetcode.com/problems/longest-common-subsequence/description/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency">
 *     1143. Longest Common Subsequence
 * </a></h3>
 * Given two strings <b><code>textOne</code></b> and <b><code>textTwo</code></b>, return the length of
 * their longest common subsequence. If there is no common subsequence, return <b><code>0</code></b>.
 * <hr/>
 * A subsequence of a string is a new string generated from the original string
 * with some characters (can be none) deleted without changing the relative order of the remaining characters.
 * For example, <code>"ace"</code> is a sub-sequence of <code>"abcde"</code>.
 * <hr/>
 * A <b>common sub-sequence</b> of two strings is a sub-sequence that is common to both strings.
 */
public interface Problem_1143__Longest_Common_SubSeq {

    /**
     * Solution entry-point
     *
     * @param textOne the first string
     * @param textTwo the second string
     * @return the length of common sub-sequence for <code>textOne</code> and <code>textTwo</code>
     */
    int longestCommonSubsequence(String textOne, String textTwo);

    enum Solution implements Problem_1143__Longest_Common_SubSeq {

        DP_2D {  // <-- 2D-Matrix is used for tabulation of the bottom-up DP-results
            @Override
            public int longestCommonSubsequence(String textOne, String textTwo) {
                System.out.printf("%n------- %s.longestCommonSubsequence: ----------%n", this);
                System.out.println("textOne = '" + textOne + "' of len = " + textOne.length());
                System.out.println("textTwo = '" + textTwo + "' of len = " + textTwo.length());
                int[][] commonLen = Stream.generate(() -> new int[textTwo.length()])
                    .limit(textOne.length()).toArray(int[][]::new);
                for (int posOne = 0; posOne < textOne.length(); posOne++) {
                    char chOne = textOne.charAt(posOne);
                    for (int posTwo = 0; posTwo < textTwo.length(); posTwo++) {
                        char chTwo = textTwo.charAt(posTwo);
                        // . . . . .  . . . . . . .
                        // . . upLeft . .  up   . .
                        // . .  left  . . curr  . .
                        // . . . . .  . . . . . . .
                        int upLeft = posOne > 0 && posTwo > 0 ? commonLen[posOne - 1][posTwo - 1] : 0;
                        int up = posOne > 0 ? commonLen[posOne - 1][posTwo] : 0;
                        int left = posTwo > 0 ? commonLen[posOne][posTwo - 1] : 0;
                        if (chOne == chTwo) {
                            commonLen[posOne][posTwo] = upLeft + 1;
                        } else {
                            commonLen[posOne][posTwo] = Math.max(up, left);
                        }
                    }
                }
                System.out.println("... final state of 'commonLen': ...");
                System.out.println(dumpCommonLen(commonLen, textOne, textTwo));
                return commonLen[textOne.length() - 1][textTwo.length() - 1];
            }
        },

        DP_1D {  // <-- only last row of 2D-Matrix is used for tabulation of the bottom-up DP-results
            @Override
            public int longestCommonSubsequence(String textOne, String textTwo) {
                System.out.printf("%n------- %s.longestCommonSubsequence: ----------%n", this);
                System.out.println("textOne = '" + textOne + "' of len = " + textOne.length());
                System.out.println("textTwo = '" + textTwo + "' of len = " + textTwo.length());
                int[] currentRow = new int[textTwo.length()];
                for (int posOne = 0; posOne < textOne.length(); posOne++) {
                    char chOne = textOne.charAt(posOne);
                    int left = 0, upLeft = 0;
                    for (int posTwo = 0; posTwo < textTwo.length(); posTwo++) {
                        char chTwo = textTwo.charAt(posTwo);
                        // . . . . .  . . . . . . . ----------------------
                        // . . upLeft . .  up   . . ---  upLeft' = up  ---
                        // . .  left  . . curr  . . ---  left' = curr. ---
                        // . . . . .  . . . . . . . ----------------------
                        int up = currentRow[posTwo];
                        int curr = (chOne == chTwo) ? upLeft + 1 : Math.max(up, left);
                        left = curr; upLeft = up;
                        currentRow[posTwo] = curr;
                    }
                    System.out.println(dumpRow(currentRow));
                }
                System.out.println("... final state of the last row: ...");
                System.out.println(dumpRow(currentRow));
                return currentRow[textTwo.length() - 1];
            }
        },

        DP_1D_SHORT {  //  <-- switch arguments in order to allocate the smaller space for "DP_1D"

            @Override
            public int longestCommonSubsequence(String textOne, String textTwo) {
                if (textOne.length() >= textTwo.length()) {
                    System.out.printf("%n   ____ %s: (use arguments as is) ____   ", this);
                    return DP_1D.longestCommonSubsequence(textOne, textTwo);
                } else {
                    System.out.printf("%n   ~~~~ %s: ( swap arguments ) ~~~~~~~   ", this);
                    return DP_1D.longestCommonSubsequence(textTwo, textOne);
                }
            }
        };

        String dumpCommonLen(int[][] commonLen, String textOne, String textTwo) {
            StringBuilder sb = new StringBuilder();
            sb.append(" ".repeat(3));
            sb.append("|");
            String topBar = textTwo.chars()
                .mapToObj(this::dumpChar)
                .collect(Collectors.joining(","));
            sb.append(topBar);
            for (int posOne = 0; posOne < textOne.length(); posOne++) {
                sb.append(lineSeparator());
                sb.append(dumpChar(textOne.charAt(posOne)));
                sb.append("|");
                sb.append(dumpRow(commonLen[posOne]));
            }
            return sb.toString();
        }
        String dumpRow(int[] row) {
            return Arrays.stream(row)
                .mapToObj(this::dumpLen)
                .collect(Collectors.joining(","));
        }
        private String dumpChar(int ch) {
            return String.format("'%c'", ch);
        }
        private String dumpLen(int len) {
            return String.format("%2d ", len);
        }
    }
}
