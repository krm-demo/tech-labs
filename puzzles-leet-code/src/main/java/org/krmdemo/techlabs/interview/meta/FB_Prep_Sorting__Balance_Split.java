package org.krmdemo.techlabs.interview.meta;

import java.util.*;

/**
 * <h3><a href="https://www.metacareers.com/profile/coding_practice_question/?problem_id=226994905008716&psid=275492097255885&practice_plan=0&p=GENERAL_SWE&b=0111122">
 *     Sorting #1. Balance Split.
 * </a></h3>
 * Given an array of integers (which may include repeated integers),
 * determine if there's a way to split the array into two subsequences <b><code>A</code></b>
 * and <b><code>B</code></b> such that the sum of the integers in both arrays is the same,
 * and all elements in <b><code>A</code></b> are strictly smaller than all elements in <b><code>B</code></b>.
 * <hr/>
 * <small>
 * <u><i>Note:</i></u> Strictly smaller denotes that every integer in <b><code>A</code></b>
 * must be less than, and not equal to, every integer in B.
 * </small>
 */
public interface FB_Prep_Sorting__Balance_Split {

    /**
     * Solution entry-point.
     *
     * @param arr an array of integers (which may include repeated integers)
     * @return <code>true</code> if balance split exists, <code>false</code> - otherwise
     */
    boolean balancedSplitExists(int[] arr) ;

    /**
     * The default implementation is based on stack (in Java {@link LinkedList} is a good choice)
     */
    enum Solution implements FB_Prep_Sorting__Balance_Split {
        DEFAULT;

        @Override
        public boolean balancedSplitExists(int[] arr) {
            System.out.println("initial arr --> " + Arrays.toString(arr));
            Arrays.sort(arr);
            System.out.println("sorted arr ---> " + Arrays.toString(arr));
            int totalSum = Arrays.stream(arr).sum();
            System.out.println("totalSum = " + totalSum);
            int cumSum = 0;
            for (int i = 0; i < arr.length; i++) {
                if (i > 0 && arr[i-1] != arr[i] && 2 * cumSum == totalSum) {
                    System.out.printf("Balance-Split starting the index #%d - break and return true :-)%n", i);
                    return true;
                } else if (2 * cumSum > totalSum) {
                    System.out.printf("no more Balance-Split since index #%d - break and return false :-(%n", i);
                    return false;
                } else {
                    System.out.println("continue iterating");
                }
                cumSum += arr[i];
                System.out.printf("%3d) %d --> cumSum = %d; ", i, arr[i], cumSum);
            }
            System.out.println("end of array - no Balance-Split");
            return false;
        }
    }
}
