package org.krmdemo.techlabs.interview.meta;

import java.util.*;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toCollection;

/**
 * <h3><a href="https://www.metacareers.com/profile/coding_practice_question/?problem_id=2444722699191194&c=3513648482275061&psid=275492097255885&practice_plan=0&b=0111122">
 *     Greedy #3. Seating Arrangements.
 * </a></h3>
 * There are <code>N</code> guests attending a dinner party, numbered from <code>1</code> to <code>N</code>.
 * The <code><b>i</b></code>th guest has a height of <code>arr[<b>i</b>-1]</code> inches.
 * <hr/>
 * The guests will sit down at a <b>circular table</b> which has <b><code>N</code></b> seats,
 * numbered from <code>1</code> to <code>N</code> in clockwise order around the table.
 * As the host, you will choose how to arrange the guests, one per seat.
 * <u>Note</u> that there are <code>n!</code> possible permutations of seat assignments.
 * <hr/>
 * Once the guests have sat down, the awkwardness between a pair of guests sitting in adjacent seats
 * is defined as the <b>absolute difference</b> between their two heights.
 * <u>Note</u> that, because the table is circular, seats <code>#1</code> and <code>#N</code> are considered
 * to be adjacent to one another, and that there are therefore <code>N</code> pairs of adjacent guests.
 * <hr/>
 * The overall awkwardness of the seating arrangement is then defined
 * as the <b>maximum awkwardness</b> of any pair of adjacent guests.
 * Determine the <b>minimum possible overall awkwardness</b> of any seating arrangement.
 * <h5>Constraints:</h5><pre>
 * 3 ≤ arr.length ≤ 10^3
 *   1 ≤ arr[i] ≤ 10^3
 * </pre>
 */
public interface FB_Prep_Greedy__Seating_Arrangements {

    /**
     * Solution entry-point.
     *
     * @param arr an array of guests' heights (the array is considered circular - the first comes after the last)
     * @return the minimal possible absolute difference between guests' heights at adjacent guests
     */
    int minOverallAwkwardness(int[] arr);

    /**
     * The proposed solution is based on building the mountain-array
     * with the least maximum gradient of its two slopes.
     */
    enum Solution implements FB_Prep_Greedy__Seating_Arrangements {
        /**
         * This solution is based on building the up-side-down mountain-array (bowl-array),
         * where the smallest element is in the middle and left and right slopes
         * are descending and ascending monotonically
         * <hr/>
         * Unlike Chat-GPT solution the source array is not sorted but copied into {@link PriorityQueue},
         * which allows to achieve some performance because if terms of HEAP-sort - only the first phase
         * is used (constructing the heap), but the second phase of HEAP-sort is skipped.
         */
        BOWL_QUEUE {
            @Override
            public int minOverallAwkwardness(int[] arr) {
                return super.minOverallAwkwardness(arr, PriorityQueue::new);
            }
        },
        /**
         * The same as {@link #BOWL_QUEUE}, but regular mountain-array (not up-side-down) is constructed
         * <hr/>
         * The only difference from previous approach - is using reverse-order {@link PriorityQueue}
         */
        MOUNTAIN_QUEUE {
            @Override
            public int minOverallAwkwardness(int[] arr) {
                return super.minOverallAwkwardness(arr,
                    () -> new PriorityQueue<>(Comparator.reverseOrder())
                );
            }
        };

        protected int minOverallAwkwardness(int[] arr, Supplier<PriorityQueue<Integer>> pqNew) {
            System.out.printf("%s for input array --> %s%n", name(), Arrays.toString(arr));
            PriorityQueue<Integer> pq = Arrays.stream(arr).boxed().collect(toCollection(pqNew));
            Deque<Integer> mountainQueue = new ArrayDeque<>();
            Deque<Integer> gradQueue = new ArrayDeque<>();
            if (!pq.isEmpty()) {
                mountainQueue.add(pq.poll());
            }
            int gradMax = 0;
            while (!pq.isEmpty()) {
                int value = pq.poll();
                if (mountainQueue.size() % 2 == 0) {
                    int gradLeft = value - mountainQueue.getFirst();
                    gradQueue.addFirst(-gradLeft);
                    gradMax = Math.max(gradMax, Math.abs(gradLeft));
                    mountainQueue.addFirst(value);
                } else {
                    int gradRight = value - mountainQueue.getLast();
                    gradQueue.addLast(gradRight);
                    gradMax = Math.max(gradMax, Math.abs(gradRight));
                    mountainQueue.addLast(value);
                }
            }
            System.out.println("mountainQueue --> " + mountainQueue);
            System.out.println("gradQueue --> " + gradQueue);
            System.out.printf("%s: gradMax = %d;%n", name(), gradMax);
            return gradMax;
        }
    }

    /**
     * Provided by <a href="https://chatgpt.com/c/67f0a391-fe94-8011-be5e-22cfd55d96dd">Chat-GPT</a>
     */
    class MinimizeMaxDiff {

        // Rearranges the array to minimize the maximum absolute difference between adjacent elements (circularly).
        public static int[] minimizeMaxDiff(int[] arr) {
            // Sort the array so that numbers close in value are adjacent.
            Arrays.sort(arr);

            // Use a deque to alternate adding numbers at the front and back.
            Deque<Integer> deque = new LinkedList<>();
            for (int i = 0; i < arr.length; i++) {
                if (i % 2 == 0) {
                    // For even indices, add to the front.
                    deque.addFirst(arr[i]);
                } else {
                    // For odd indices, add to the back.
                    deque.addLast(arr[i]);
                }
            }

            // Convert the deque back to an array.
            int[] result = new int[arr.length];
            int index = 0;
            for (int num : deque) {
                result[index++] = num;
            }

            return result;
        }

        // Helper method to compute the maximum absolute difference in a circular arrangement.
        public static int maxCircularDifference(int[] arr) {
            int n = arr.length;
            int maxDiff = 0;
            for (int i = 0; i < n; i++) {
                int next = (i + 1) % n;  // use modulo to wrap around the circular array
                maxDiff = Math.max(maxDiff, Math.abs(arr[i] - arr[next]));
            }
            return maxDiff;
        }

        public static void main(String[] args) {
            // Example array.
            int[] arr = {10, 1, 5, 8, 12, 6};

            // Rearrange the array.
            int[] arranged = minimizeMaxDiff(arr);

            // Output the arranged array.
            System.out.println("Rearranged array:");
            for (int num : arranged) {
                System.out.print(num + " ");
            }
            System.out.println();

            // Calculate and output the maximum absolute difference.
            int maxDiff = maxCircularDifference(arranged);
            System.out.println("Maximum absolute difference: " + maxDiff);
        }
    }

    /**
     * Provided by <a href="https://chat.deepseek.com/a/chat/s/c4c2a088-4b98-4d4d-92f5-08b1e8e7ef2c">Deep-Seek</a>
     */
    class CircularArrayRearrangement {

        public static int[] rearrangeArray(int[] arr) {
            Arrays.sort(arr);
            int n = arr.length;
            int mid = (n + 1) / 2;
            int[] firstHalf = Arrays.copyOfRange(arr, 0, mid);
            int[] secondHalf = Arrays.copyOfRange(arr, mid, n);
            reverseArray(secondHalf);
            int[] result = new int[n];
            System.arraycopy(firstHalf, 0, result, 0, firstHalf.length);
            System.arraycopy(secondHalf, 0, result, firstHalf.length, secondHalf.length);
            return result;
        }

        private static void reverseArray(int[] array) {
            int i = 0;
            int j = array.length - 1;
            while (i < j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        public static int maxAdjacentDifference(int[] arr) {
            int[] rearranged = rearrangeArray(arr);
            int maxDiff = 0;
            int n = rearranged.length;
            for (int i = 0; i < n; i++) {
                int current = rearranged[i];
                int next = rearranged[(i + 1) % n];
                int diff = Math.abs(current - next);
                if (diff > maxDiff) {
                    maxDiff = diff;
                }
            }
            return maxDiff;
        }

        public static void main(String[] args) {
            int[] arr = {4, 2, 1, 5};
            int[] rearranged = rearrangeArray(arr);
            System.out.println("Rearranged array: " + Arrays.toString(rearranged));
            System.out.println("Maximum adjacent difference: " + maxAdjacentDifference(arr));
        }
    }
}
