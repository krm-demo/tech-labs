package org.krmdemo.techlabs.leet_code_0000_1000;

/**
 * <h3><a href="https://leetcode.com/problems/paint-fence/description/">
 *     276. Paint Fence
 * </a></h3>
 * You are painting a fence of <b><code>n</code></b> posts with <b><code>k</code></b> different colors.
 * You must paint the posts following these rules:<ul>
 *     <li>Every post must be painted exactly one color.</li>
 *     <li>There <b>cannot be three or more consecutive posts</b> with the same color.</li>
 * </ul>
 * Given the two integers <b><code>n</code></b> and <b><code>k</code></b>,
 * return the number of ways you can paint the fence.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Lucas_sequence#Distinct_roots">
 *    (Wiki) Lucas sequence
 * </a>
 */
public interface Problem_276__Paint_Fence {

    /**
     * Solution entry-point.
     *
     * @param n the number of posts in a fence
     * @param k the number of colors
     * @return the number of ways you can paint the fence (with <b>no three or more consecutive colors</b>)
     */
    int numWays(int n, int k);

    /**
     * TODO: implement either Lucas-formula or matrix-power solution
     */
    enum Solution implements Problem_276__Paint_Fence {
        DP_BOTTOM_UP {
            public int numWays(int n, int k) {
                if (n == 1) return k;
                int twoPostsBack = k;
                int onePostBack = k * k;
                for (int i = 3; i <= n; i++) {
                    int curr = (k - 1) * (onePostBack + twoPostsBack);
                    twoPostsBack = onePostBack;
                    onePostBack = curr;
                }
                return onePostBack;
            }
        };
    }
}
