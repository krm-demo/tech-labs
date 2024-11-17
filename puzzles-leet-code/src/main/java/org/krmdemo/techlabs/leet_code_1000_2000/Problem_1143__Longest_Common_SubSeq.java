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
        DEFAULT;

        @Override
        public int longestCommonSubsequence(String textOne, String textTwo) {
            System.out.println("----------- longestCommonSubsequence: ------------------");
            System.out.println("textOne = '" + textOne + "' of len = " + textOne.length());
            System.out.println("textTwo = '" + textTwo + "' of len = " + textTwo.length());
            int[][] commonLen = Stream.generate(() -> new int[textTwo.length()])
                .limit(textOne.length()).toArray(int[][]::new);
//            System.out.println("... initial state of 'commonLen': ...");
//            System.out.println(dumpCommonLen(commonLen, textOne, textTwo));
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

        private String dumpCommonLen(int[][] commonLen, String textOne, String textTwo) {
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
                String row = Arrays.stream(commonLen[posOne])
                    .mapToObj(this::dumpLen)
                    .collect(Collectors.joining(","));
                sb.append(row);
            }
            return sb.toString();
        }
        private String dumpChar(int ch) {
            return String.format("'%c'", ch);
        }
        private String dumpLen(int len) {
            return String.format("%2d ", len);
        }
    }
}
