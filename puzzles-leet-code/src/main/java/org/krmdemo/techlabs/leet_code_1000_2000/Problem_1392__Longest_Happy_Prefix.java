package org.krmdemo.techlabs.leet_code_1000_2000;

import java.math.BigInteger;
import java.util.*;

import static java.util.stream.Collectors.joining;

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
 *
 * @see <a href="https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm/">
 *     (wiki) Knuth–Morris–Pratt algorithm
 * </a>
 * @see <a href="https://brilliant.org/wiki/knuth-morris-pratt-algorithm/">
 *     (brilliant.org) Knuth-Morris-Pratt Algorithm
 * </a>
 * @see <a href="https://ds2-iiith.vlabs.ac.in/exp/kmp-algorithm/preprocessing-of-kmp-algorithm/concept-and-strategy-preprocessing.html#:~:text=The%20preprocessing%20for%20the%20KMP,is%20also%20a%20proper%20suffix.">
 *     (virtual labs) KMP Algorithm
 * </a>
 * @see <a href="https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/">
 *     (GFG) KMP Algorithm for Pattern Searching
 * </a>
 */
public interface Problem_1392__Longest_Happy_Prefix {

    /**
     * Solution entry-point.
     *
     * @param s a string to find the <b>longest happy prefix</b
     * @return <b>longest happy prefix</b or empty-string if such one is not found
     */
    String longestPrefix(String s);

    @SuppressWarnings("SameParameterValue")
    enum Solution implements Problem_1392__Longest_Happy_Prefix {
        /**
         * A brute-force solution is based on verifying each sub-string (time complexity <code>O(N^2)</code>)
         */
        BRUTE_FORCE {
            @Override
            public String longestPrefix(String s) {
                if (s == null || s.length() <= 1) {
                    System.out.printf("%s.longestPrefix: returning empty string.%n", name());
                    return "";
                }
                System.out.printf("%s.longestPrefix('%s'):%n", name(), s);
                for (int i = 1; i < s.length(); i++) {
                    String suffix = s.substring(i);
                    if (s.startsWith(suffix)) {
                        System.out.printf("- returning suffix = '%s'; len = %d;%n", suffix, suffix.length());
                        return suffix;
                    }
                }
                System.out.println("- no sufix found;");
                return "";
            }
        },
        /**
         * This approach is based on <b>longest border length</b> or <b>longest proper perfix</b>,
         * which is also a part of
         * <a href="https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm/">
         *     Knuth–Morris–Pratt algorithm
         * </a>
         */
        KMP_LONGEST_BORDER {
            @Override
            public String longestPrefix(String s) {
                if (s == null || s.length() <= 1) {
                    System.out.printf("%s.longestPrefix: returning empty string.%n", name());
                    return "";
                }
                System.out.printf("%s.longestPrefix('%s'):%n", name(), s);
                final int N = s.length();
                int[] longestBorder = new int[N];
                int borderLength = 0;
                int pos = 1; // <-- starting from the second position
                System.out.printf("prevPos = %2d'%c'; borderLength = %2d'%c'; longestBorder --> %s;%n",
                    0, s.charAt(0), borderLength, s.charAt(0), dumpPrefixArr(longestBorder, 1));
                while (pos < N) {
                    char charPos = s.charAt(pos);  // <-- using char-array here improves the performance >30% !!!
                    char charPrefix = s.charAt(borderLength); // ... from 10ms to 6ms ( beat from 63.67% to 97.58% )
                    int prevPos = pos;
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
                    System.out.printf("prevPos = %2d'%c'; borderLength = %2d'%c'; longestBorder --> %s;%n",
                        prevPos, charPos, borderLength, charPrefix, dumpPrefixArr(longestBorder, prevPos + 1));
                }
                System.out.printf("s[%d:] = '%s';%n", longestBorder[N-1], s.substring(N - longestBorder[N-1]));
                System.out.printf("s[:%d] = '%s';%n", longestBorder[N-1], s.substring(0, longestBorder[N-1]));
                // the last element of "longestBorder" contains the length of
                // the longest proper prefix of the whole string
                return s.substring(N - longestBorder[N-1]);
                // return s.substring(0, longestBorder[N-1]);  // <-- exactly the same as above
            }

            private String dumpPrefixArr(int[] arr, int prefixLen) {
                if (arr == null || arr.length == 0) {
                    return "";
                }
                prefixLen = Math.min(prefixLen, arr.length);
                return Arrays.stream(arr, 0, prefixLen)
                    .mapToObj(String::valueOf)
                    .collect(joining(", "));
            }
        },
        /**
         * This approach is using <b>incremental</b> single hash, which is also known as
         * <a href="https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/">
         *     Rabin-Karp Algorithm for Pattern Searching
         * </a>
         */
        RABIN_KARP_INCR {
            @Override
            public String longestPrefix(String s) {
                if (s == null || s.length() <= 1) {
                    System.out.printf("%s.longestPrefix: returning empty string.%n", name());
                    return "";
                }
                System.out.printf("%s.longestPrefix('%s'):%n", name(), s);
                final int N = s.length();
                int maxLen = 0;
                char[] charsArr = s.toCharArray();
                long hashPrefix = 0;
                long hashSuffix = 0;
                long powChNum = 1;
                for (int i = 0; i < N - 1; i++) {
                    char charPrefix = s.charAt(i);
                    char charSuffix = s.charAt(N - i - 1);
                    hashPrefix = (hashPrefix * CHARS_NUMBER + charPrefix) % MOD;
                    hashSuffix = (charSuffix * powChNum + hashSuffix) % MOD;
                    powChNum = powChNum * CHARS_NUMBER % MOD;
                    System.out.printf("%2d'%c' %2d'%c'",
                        i, charPrefix, N - i - 1, charSuffix);
                    if (hashPrefix == hashSuffix
                        && equalHeadTail(charsArr, i + 1, maxLen))
                    {
                        maxLen = i + 1;
                    } else {
                        System.out.println(" - hash mismatch");
                    }
                }
                return s.substring(0, maxLen);
            }
        },
        /**
         * This approach is using <b>decremental</b> single hash, which is also known as
         * <a href="https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/">
         *     Rabin-Karp Algorithm for Pattern Searching
         * </a>
         */
        RABIN_KARP_DECR {
            @Override
            public String longestPrefix(String s) {
                if (s == null || s.length() <= 1) {
                    System.out.printf("%s.longestPrefix: returning empty string.%n", name());
                    return "";
                }
                System.out.printf("%s.longestPrefix('%s'):%n", name(), s);
                final int N = s.length();
                char[] charsArr = s.toCharArray();
                long hashPrefix = forwardHash(s);
                long hashSuffix = hashPrefix;
                long invChNum = modInverse(CHARS_NUMBER, MOD);
                long powChNum = modPow(CHARS_NUMBER, N, MOD);
                System.out.printf("- invChNum = %,d;%n", invChNum);
                System.out.printf("- powChNum = %,d;%n", powChNum);
                for (int i = 0; i < N - 1; i++) {
                    int charPrefix = s.charAt(i) - 'a';
                    int charSuffix = s.charAt(N - i - 1) - 'a';
                    powChNum = powChNum * invChNum % MOD;
                    hashPrefix = (hashPrefix - charSuffix) * invChNum % MOD;
                    hashSuffix = (MOD + hashSuffix - powChNum * charPrefix % MOD) % MOD;
                    if (hashPrefix == hashSuffix
                        && equalTwoSubStr(charsArr, 0, i + 1, N - i - 1))
                    {
                        System.out.printf("- match at i = %d, len = %d: '%s'",
                            i, N - i - 1, s.substring(i + 1));
                        return s.substring(i + 1);
                    }
                }
                System.out.println("- no matches were found");
                return "";
            }
        };

        private static long forwardHash(String str) {
            return str.chars()
                .mapToLong(ch -> (long)ch - 'a')
                .reduce(0, (hash, value) ->
                    (hash * CHARS_NUMBER + value) % MOD
                );
        }

        private static long modInverse(long value, long mod) {
            return BigInteger.valueOf(value)
                .modInverse(BigInteger.valueOf(mod))
                .longValueExact();
        }

        private static long modPow(long value, long pow, long mod) {
            return BigInteger.valueOf(value)
                .modPow(BigInteger.valueOf(pow), BigInteger.valueOf(mod))
                .longValueExact();
        }

        final static int CHARS_NUMBER = 26;
        final static int MOD = 1_000_000_007;

        private static boolean equalHeadTail(char[] charsArr, int prefixLen, int partLen) {
            int startOne = 0;
            int startTwo = charsArr.length - prefixLen;
            int len = prefixLen - partLen;
            System.out.printf(" - matching prefix('%s') and suffix('%s') :: len = %d :: ",
                String.valueOf(charsArr, startOne, prefixLen),
                String.valueOf(charsArr, startTwo, prefixLen),
                len
            );
            if (notEqualTwoSubStr(charsArr, startOne, startTwo, len)) {
                System.out.println("heads do NOT equal;");
                return false;
            }
            startOne += partLen;
            startTwo += partLen;
            if (notEqualTwoSubStr(charsArr, startOne, startTwo, len)) {
                System.out.println("tails do NOT equal;");
                return false;
            }
            System.out.println("match !!!");
            return true;
        }

        private static boolean equalTwoSubStr(char[] charsArr, int startOne, int startTwo, int len) {
            return !notEqualTwoSubStr(charsArr, startOne, startTwo, len);
        }

        private static boolean notEqualTwoSubStr(char[] charsArr, int startOne, int startTwo, int len) {
            if (Math.max(startOne, startTwo) + len > charsArr.length) {
                return startOne != startTwo;
            }
            for (int pos = 0; pos < len; pos++) {
                if (charsArr[startOne + pos] != charsArr[startTwo + pos]) {
                    return true;
                }
            }
            return false;
        }
    }
}
