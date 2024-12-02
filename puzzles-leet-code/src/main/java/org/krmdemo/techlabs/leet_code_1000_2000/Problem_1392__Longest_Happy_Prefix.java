package org.krmdemo.techlabs.leet_code_1000_2000;

/**
 * <h3><a href="https://leetcode.com/problems/longest-happy-prefix/description/">
 *     1392. Longest Happy Prefix
 * </a></h3>
 * A string is called a <b>happy prefix</b> if is a <b>non-empty</b> prefix which is also a suffix (excluding itself).
 * <hr/>
 * Given a string <b><code>s</code></b>, return the <b>longest happy prefix</b> of <b><code>s</code></b>.
 * Return an empty string <code>""</code> if no such prefix exists.
 * <h5>Constraints:</h5><pre>
 * 1 <= s.length <= 10^5
 * </pre>
 * <b><code>s</code></b> contains only lowercase English letters.
 */
public interface Problem_1392__Longest_Happy_Prefix {

    /**
     * Solution entry-point.
     *
     * @param s a string to find the <b>longest happy prefix</b
     * @return <b>longest happy prefix</b or empty-string if such one is not found
     */
    String longestPrefix(String s);

    @SuppressWarnings("ForLoopReplaceableByForEach")
    enum Solution implements Problem_1392__Longest_Happy_Prefix {
        /**
         * A brute-force solution is based on verifying each sub-string (time complexity <code>O(N^2)</code>)
         */
        BRUTE_FORCE {
            @Override
            public String longestPrefix(String s) {
                if (s == null || s.length() <= 1) {
                    return "";
                }
                for (int i = 1; i < s.length(); i++) {
                    String suffix = s.substring(i);
                    if (s.startsWith(suffix)) {
                        return suffix;
                    }
                }
                return "";
            }
        }
    }
}
