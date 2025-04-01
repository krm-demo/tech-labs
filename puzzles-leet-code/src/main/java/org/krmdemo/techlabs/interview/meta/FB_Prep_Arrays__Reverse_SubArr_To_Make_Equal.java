package org.krmdemo.techlabs.interview.meta;

import java.util.*;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * <h3><a href="https://www.metacareers.com/profile/coding_practice_question/?problem_id=2869293499822992&psid=275492097255885&practice_plan=0&p=GENERAL_SWE&b=0111122">
 *     Arrays #1. Reverse to Make Equal.
 * </a></h3>
 * Given two arrays <b><code>A</code></b> and <b><code>B</code></b> of length <code>N</code>,
 * determine if there is a way to make <b><code>A</code></b> equal to <b><code>B</code></b>
 * by reversing any sub-arrays from array <b><code>B</code></b> any number of times.
 *
 * @see <a href="https://leetcode.com/problems/make-two-arrays-equal-by-reversing-subarrays/description/">
 *     1460. Make Two Arrays Equal by Reversing Subarrays
 * </a>
 */
public interface FB_Prep_Arrays__Reverse_SubArr_To_Make_Equal {

    /**
     * @param A an array of integers in the range <code>[0, 1,000,000,000]</code>.
     * @param B an array of integers in the range <code>[0, 1,000,000,000]</code>.
     * @return <code>true</code> - if there is a way to meak arrays equal, and <code>false</code> - otherwise
     */
    boolean areTheyEqual(int[] A, int[] B);

    /**
     * The default implementation is based on <b><code>counting-map</code></b>.
     * If the frequencies of values in both arrays are the same - it's always possible
     * to re-arrange elements in one element to make both arrays equal.
     * <hr/>
     *
     */
    enum Solution implements FB_Prep_Arrays__Reverse_SubArr_To_Make_Equal {
        SORTING {
            @Override
            public boolean areTheyEqual(int[] A, int[] B) {
                Arrays.sort(A);
                Arrays.sort(B);
                return Arrays.equals(A, B);
            }
        },
        TWO_COUNTING_MAPS {
            @Override
            public boolean areTheyEqual(int[] A, int[] B) {
                Map<Integer, Long> cntMapA = Arrays.stream(A).boxed()
                    .collect(groupingBy(identity(), counting()));
                Map<Integer, Long> cntMapB = Arrays.stream(B).boxed()
                    .collect(groupingBy(identity(), counting()));
                return cntMapA.equals(cntMapB);
            }
        },
        SINGLE_COUNTING_MAPS {
            @Override
            public boolean areTheyEqual(int[] A, int[] B) {
                if (A.length != B.length) {
                    return false;
                }
                Map<Integer, Integer> countingMap = new HashMap<>();
                for (int i = 0; i < A.length; i++) {
                    countingMap.merge(A[i], 1, Integer::sum);
                    countingMap.merge(B[i], -1, Integer::sum);
                }
                return countingMap.values().stream().noneMatch(cnt -> cnt != 0);
            }
        };
    }
}
