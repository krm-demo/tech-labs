package org.krmdemo.techlabs.leet_code_1000_2000;

/**
 * <h3><a href="https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/description/">
 *     1718. Construct the Lexicographically Largest Valid Sequence
 * </a></h3>
 * Given an integer <b><code>n</code></b>, find a sequence that satisfies all the following:<ul>
 *     <li>The integer <code>1</code> occurs <b>once</b> in the sequence.</li>
 *     <li>Each integer between <code>2</code> and <b>n</b> occurs <b>twice</b> in the sequence.</li>
 *     <li>For every integer <b><code>i</code></b> between <code>2</code> and <code>n</code>,
 *     the distance between the two occurrences of <code>i</code> is exactly <b><code>i</code></b>.</li>
 *     <li>The distance between two numbers on the sequence, <code>a[i]</code>
 *     and <code>a[j]</code>, is the absolute difference of their indices, <code>|j - i|</code>.</li>
 * </ul>
 * Return the lexicographically largest sequence. It is guaranteed that under the given constraints,
 * there is always a solution.
 * <hr/>
 * A sequence <code>a</code> is lexicographically larger than a sequence <code>b</code>
 * (of the same length) if in the first position where <code>a</code> and <code>b</code> differ,
 * sequence <code>a</code> has a number greater than the corresponding number in <code>b</code>.
 * For example, <code>[0,1,9,0]</code> is lexicographically larger than <code>[0,1,5,6]</code>
 * because the first position they differ is at the third number,
 * and <code>9</code> is greater than <code>5</code>.
 * <h5>Constraints:</h5>
 * <code>1 <= n <= 20</code>
 */
public interface Problem_1718__Lexico_Largest_Valid_Seq {

    /**
     * Solution entry-point
     *
     * @param n an integer in range <code>1 <= n <= 20</code>
     * @return the lexicographically largest sequence, that satisfies the requirements above
     */
    int[] constructDistancedSequence(int n);

    // TODO: to be done
}
