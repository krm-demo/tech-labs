package org.krmdemo.techlabs.interview.meta;

import java.util.*;

/**
 * <h3><a href="https://www.metacareers.com/profile/coding_practice_question/?problem_id=838749853303393&c=3513648482275061&psid=275492097255885&practice_plan=0&b=0111122">
 *     Greedy #2. Element Swapping.
 * </a></h3>
 * Given a sequence of <code>arr.length</code> integers <b><code>arr</code></b>,
 * determine the <a href="https://en.wikipedia.org/wiki/Lexicographic_order">lexicographically smallest</a> sequence
 * which may be obtained from it after performing at most <b><code>K</code></b> element swaps,
 * each involving a pair of <u>consecutive elements</u> in the sequence.
 * <hr/>
 * <small>
 * <u><i>Note:</i></u> A <code>list x</code> is lexicographically smaller
 * than a different equal-length <code>list y</code> if and only if, for the earliest index  at which the two lists differ,
 * <code>x</code>'s element at that index is smaller than <code>y</code>'s element at that index.
 * </small>
 * <h5>Constraints:</h5><pre>
 * 1 ≤ arr.length ≤ 10^3
 *      1 ≤ K ≤ 1000
 *     1 ≤ arr[i] ≤ 10^6
 * </pre>
 *
 * @see org.krmdemo.techlabs.leet_code_0000_1000.Problem_031__Next_Permutation (more advanced problem)
 */
public interface FB_Prep_Greedy__Element_Swapping {

    /**
     * Solution entry-point.
     *
     * @param arr an array of positive integers (values don't really matter) of size not more than <code>1000</code>
     * @param K the exact number swaps to perform for <u>only consecutive pair</u> of elements
     * @return the lexicographically smallest array after those <code>K</code> swaps
     */
    int[] findMinArray(int[] arr, int K);

    /**
     * The proposed solution is based on {@link PriorityQueue}
     * and appears to be a classic greedy algorithm (the strict proof is not trivial)
     */
    enum Solution implements FB_Prep_Greedy__Element_Swapping {
        IN_PLACE;

        @Override
        public int[] findMinArray(int[] arr, int K) {
            System.out.println("arr --> " + Arrays.toString(arr));
            System.out.println("initially K = " + K);
            for (int index = 0; index < arr.length; index++) {
                if (K <= 0) {
                    break;
                }
                OptionalInt minIndexOpt = nextIndexOfMin(arr, K, index);
                if (minIndexOpt.isPresent()) {
                    int minIndex = minIndexOpt.getAsInt();
                    System.out.printf("- swapping [%d]%d and [%d]%d K: %d - %d = %d; ",
                        index, arr[index], minIndex, arr[minIndex],
                        K, minIndex - index, K - (minIndex - index));
//                    swapElems(arr, index, minIndex);  // <-- this is not correct
                    bubbleValue(arr, index, minIndex);
                    K -= (minIndex - index);
                    System.out.println("--> " + Arrays.toString(arr));
                };
            }
            System.out.println("finally K = " + K);
            System.out.println("finally arr --> " + Arrays.toString(arr));
            if (Arrays.equals(arr, Arrays.stream(arr).sorted().toArray())) {
                System.out.println("<<< the array is sorted !!! >>>\n");
            } else {
                System.out.println(">>> the array is still un-sorted, but no swaps remain <<<\n");
            }
            return arr;
        }

        /**
         * Looking for the minimal element among not more than next <code>K</code> elements
         * since the element with the passed index <code>indexBefore</code>.
         *
         * @param arr an array to search the index of minimal element at
         * @param K the maximum number of next elements to search the minimal one
         * @param indexBefore the index of element whose value must be <b>greater</b> than next minimal one
         * @return the optional wrapper over the found index of the found next minimal element
         */
        OptionalInt nextIndexOfMin(int[] arr, int K, int indexBefore) {
            int minValue = arr[indexBefore];
            OptionalInt minIndex = OptionalInt.empty();
            int rightBound = Math.min(indexBefore + K + 1, arr.length);
            for (int i = indexBefore + 1; i < rightBound; i++) {
                if (minValue > arr[i]) {
                    minValue = arr[i];
                    minIndex = OptionalInt.of(i);
                }
            }
            return minIndex;
        }

        /**
         * Bubble the element to the target index by consecutive swaps
         *
         * @param arr an array to swap elements
         * @param targetIndex the raget index of element that is going to bubble
         * @param indexToBubble the initial index of element to bubble
         */
        private void bubbleValue(int[] arr, int targetIndex, int indexToBubble) {
            for (int index = indexToBubble; index > targetIndex; index--) {
                int bubbleValue = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = bubbleValue;
            }
        }

        /**
         * Swapping the elements in array - this MUST NOT be used in this puzzle
         *
         * @param arr an array to swap elements
         * @param indexOne the first index to swap
         * @param indexTwo the second index to swap
         */
        private void swapElems(int[] arr, int indexOne, int indexTwo) {
            int valueOne = arr[indexOne];
            arr[indexOne] = arr[indexTwo];
            arr[indexTwo] = valueOne;
        }
    }
}
