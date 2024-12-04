package org.krmdemo.techlabs.leet_code_0000_1000;

/**
 * <h3><a href="https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/description/">
 *     28. Find the Index of the First Occurrence in a String
 * </a></h3>
 * Given two strings <b><code>needle</code></b> and <b><code>haystack</code></b>,
 * return the index of the first occurrence of <b><code>needle</code></b>
 * in <b><code>haystack</code></b>, or <b><code>-1</code></b> if <b><code>needle</code></b>
 * is not part of <b><code>haystack</code></b>.
 *
 * @see org.krmdemo.techlabs.leet_code_1000_2000.Problem_1392__Longest_Happy_Prefix
 */
public interface Problem_028__Index_Of_SubStr {

    /**
     * Solution entry-point.
     *
     * @param haystack a string to find the sub-string within
     * @param needle a content of sub-string to find
     * @return the index of found sub-string or <b><code>-1</code></b>, if it was not found
     */
    int strStr(String haystack, String needle);

    enum Solution implements Problem_028__Index_Of_SubStr {
        DEFAULT;

        @Override
        public int strStr(String haystack, String needle) {
            return haystack.indexOf(needle);
        }
    }
}
