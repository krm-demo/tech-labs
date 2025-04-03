package org.krmdemo.techlabs.interview.meta;

import org.krmdemo.techlabs.leet_code_0000_1000.Problem_323__Number_of_Connected_SubGraphs;

import java.util.*;
import java.util.stream.Stream;

/**
 * <h3><a href="https://www.metacareers.com/profile/coding_practice_question/?problem_id=226517205173943&psid=275492097255885&practice_plan=0&p=GENERAL_SWE&b=0111122">
 *     Arrays #3. Contiguous Sub-Arrays.
 * </a></h3>
 * You are given an array arr of <b><code>N</code></b> integers.
 * For each index <code>i</code>, you are required to determine the number of contiguous sub-arrays
 * that fulfill the following conditions:<ul>
 *     <li>The value at index <code>i</code> must be the maximum element in the contiguous sub-arrays</li>
 *     <li>These contiguous sub-arrays must either start from or end on index <code>i</code></li>
 * </ul>
 *
 * @see org.krmdemo.techlabs.leet_code_0000_1000.Problem_739__Daily_Temperatures
 */
public interface FB_Prep_Arrays__Contiguous_SubArrays {

    /**
     * Solution entry-point.
     *
     * @param arr a non-empty array of unique integers in a range <code>[ 1 , 10^9 ]</code>
     * @return the array where <code>i</code>th value is the number of contiguous sub-arrays for value <code>arr[i]</code>
     */
    int[] countSubArrays(int[] arr);

    /**
     * The solution is based on using two monotonic stack - decreasing and increasing.
     */
    enum Solution implements FB_Prep_Arrays__Contiguous_SubArrays {
        DEFAULT;

        @Override
        public int[] countSubArrays(int[] arr) {
            // TODO: to be implemented in a similar way as for daily-temperature
            throw new UnsupportedOperationException("not implemented yet");
            //return new int[0];
        }
    }
}
