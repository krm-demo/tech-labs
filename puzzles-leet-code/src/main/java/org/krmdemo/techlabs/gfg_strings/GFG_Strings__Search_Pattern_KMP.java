package org.krmdemo.techlabs.gfg_strings;

import java.util.*;

/**
 * <h3><a href="https://www.geeksforgeeks.org/problems/search-pattern0205/1">
 *     Search Pattern (KMP-Algorithm)
 * </a></h3>
 * Given two strings, one is a text string <b><code>txt</code></b>
 * and the other is a pattern string <b><code>pat</code></b>.
 * The task is to print the indexes of all the occurrences of the pattern string in the text string.
 * Use 0-based indexing while returning the indices.
 * <h5>Note:</h5>
 * Return an empty list in case of no occurrences of pattern.
 * <h5>Constraints:</h5><pre>
 * 1 ≤ txt.size() ≤ 10^6
 * 1 ≤ pat.size() < txt.size()</pre>
 * Both the strings consist of lowercase English alphabets.
 *
 * @see org.krmdemo.techlabs.leet_code_0000_1000.Problem_028__Index_Of_SubStr
 * @see org.krmdemo.techlabs.leet_code_1000_2000.Problem_1392__Longest_Happy_Prefix
 */
public interface GFG_Strings__Search_Pattern_KMP {

    /**
     * GFG-Solution entry-point
     *
     * @param pat a pattern for the strict-search (no wild-cards or regexp)
     * @param txt the string to find indexes of all sub-strings that equals to <code>pat</code>
     * @return indexes of found sub-strings as {@link ArrayList}
     */
    ArrayList<Integer> search(String pat, String txt);

    enum Solution implements GFG_Strings__Search_Pattern_KMP {
        /**
         * This approach is just subsequently looking for index of sub-string
         * <h6>GFG: Time Taken <code>4.62</code></h6>
         */
        BRUTE_FORCE {
            @Override
            public ArrayList<Integer> search(String pat, String txt) {
                ArrayList<Integer> resultList = new ArrayList<>();
                int index = txt.indexOf(pat);
                while (index >= 0) {
                    resultList.add(index);
                    index = txt.indexOf(pat, index + 1);
                }
                return resultList;
            }
        },
        /**
         * This approach uses {@link #longestBorderFor(String) LPS-Array}
         * and follows the classic version of Knuth-Morris-Pratt Algorithm.
         * <h6>GFG: Time Taken <code>2.37</code></h6>
         */
        KMP_CLASSIC {
            @Override
            public ArrayList<Integer> search(String pat, String txt) {
                ArrayList<Integer> resultList = new ArrayList<>();
                int indexPat = 0;
                int indexTxt = 0;
                int lenPat = pat.length();
                int lenTxt = txt.length();
                int[] lpsPat = longestBorderFor(pat);
                while (indexTxt < lenTxt) {
                    if (pat.charAt(indexPat) == txt.charAt(indexTxt)) {
                        indexPat++;
                        indexTxt++;
                        if (indexPat == lenPat) {
                            resultList.add(indexTxt - lenPat);
                            // here we just take the last element of LPS-Array,
                            // because "indexPat" was already incremented several lines above
                            indexPat = lpsPat[indexPat - 1];
                        }
                    } else if (indexPat > 0) {
                        // in case of mismatch we are trying to match shorter pattern
                        // according to the longest border of previous sub-pattern (LPS-Array)
                        indexPat = lpsPat[indexPat - 1];
                    } else {
                        indexTxt++;
                    }
                }
                return resultList;
            }
        },
        /**
         * In this approach the search-pattern and the text are concatenated via <code>'$'</code>-symbol
         * and the found array is just indexes of proper elements of {@link #longestBorderFor(String) LPS-Array}.
         * <h6>GFG: Time Taken <code>2.26</code></h6>
         */
        KMP_CONCAT {
            @Override
            public ArrayList<Integer> search(String pat, String txt) {
                final int lenPat = pat.length();
                final int lenPat2 = lenPat << 1;
                int[] lpsConcat = longestBorderFor(pat + '$' + txt);
                // System.out.println(Arrays.toString(lpsConcat));
                // java.util.stream.IntStream.range(0, lpsConcat.length)
                //     .filter(index -> lpsConcat[index] == lenPat)
                //     .forEach(index -> {
                //         System.out.printf("index = %d;%n", index);
                //     });
                return java.util.stream.IntStream.range(lenPat2, lpsConcat.length)
                    .filter(index -> lpsConcat[index] == lenPat)
                    .map(index -> index - lenPat2)
                    .boxed()
                    .collect(java.util.stream.Collectors.toCollection(
                        ArrayList::new
                    ));
            }
        };

        /**
         * Calculating the longest-border (longest proper-suffix, LPS-Array) according to
         * <a href="https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/">
         *     (GFG) KMP Algorithm for Pattern Searching
         * </a>
         * <hr/>
         * The <b>border</b> of string is its prefix that is equal to its suffix, but excluding the whole string itself.
         * In case of no such prefixes and suffixes the border is considered to be an empty string.
         * In scope of KMP-Algorithm we are interested to find the length of the longest border
         * for each sub-string like <code>[s[:i] for i in range(1..len(s))]</code>.
         *
         * @param str the string to build LPS-Array (longest border)
         * @return the array, where the first element is always zero and for <code>i > 0</code>
         *         it's the longest border of <code>str.{@link String#substring(int, int) substring}(0,i)</code>
         */
        public static int[] longestBorderFor(String str) {
            final int N = str.length();
            char[] charsArr = str.toCharArray();
            int[] longestBorder = new int[N];
            int borderLength = 0;
            int pos = 1; // <-- starting from the second position
            while (pos < N) {
                char charPos = charsArr[pos];
                char charPrefix = charsArr[borderLength];
                if (charPos == charPrefix) {
                    borderLength++;
                    longestBorder[pos] = borderLength;
                    pos++;
                } else if (borderLength == 0) {
                    pos++;
                } else {
                    // this is the most important trick of KMP-algorithm
                    // NOTE! that "pos" is not incremented here
                    borderLength = longestBorder[borderLength - 1];
                }
            }
            return longestBorder;
        }
    }
}
