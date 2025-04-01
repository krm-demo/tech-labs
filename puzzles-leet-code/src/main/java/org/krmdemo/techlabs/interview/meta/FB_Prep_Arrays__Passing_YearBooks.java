package org.krmdemo.techlabs.interview.meta;

import java.util.*;
import java.util.stream.Stream;

/**
 * <h3><a href="https://www.metacareers.com/profile/coding_practice_question/?problem_id=146466059993201&psid=275492097255885&practice_plan=0&p=GENERAL_SWE&b=0111122">
 *     Arrays #2. Passing Yearbooks.
 * </a></h3>
 * There are <b><code>N</code></b> students, numbered from <code>1</code> to <code>N</code>,
 * each with their own yearbook. They would like to pass their yearbooks around and get them signed by other students.
 * You're given a list of <b><code>N</code></b> integers <code>arr[0..N-1]</code>,
 * which is guaranteed to be a permutation of <code>1..N</code> (in other words,
 * it includes the integers from <code>1</code> to <code>N</code> exactly once each, in some order).
 * The meaning of this list is described below:
 * <hr/>
 * Initially, each student is holding their own yearbook.
 * The students will then repeat the following two steps each minute:<ol>
 *     <li>Each student <code>i</code> will first sign the yearbook that they're currently holding
 *     (which may either belong to themselves or to another student),
 *     and then they'll pass it to student <code>arr[i-1]</code>.
 *     It's possible that <code>arr[i-1] = i</code> for any given <code>i</code>,
 *     in which case student <code>i</code> will pass their yearbook back to themselves.</li>
 *     <li>Once a student has received <b>their own yearbook back</b>,
 *     they will hold on to it and no longer participate in the passing process.</li>
 * </ol>
 * It's guaranteed that, for any possible valid input, each student will eventually receive their own yearbook back
 * and will never end up holding more than one yearbook at a time.
 * You must compute a list of <b><code>N</code></b> integers output,
 * whose element at <code>i-1</code> is equal to the number of signatures
 * that will be present in student <code>i</code>'s yearbook once they receive it back.
 *
 * @see ...
 */
public interface FB_Prep_Arrays__Passing_YearBooks {

    /**
     * Solution entry-point.
     *
     * @param arr the value by the index <code>i</code> is the index of the student to pass the year-book next
     * @return an array with number of signatures each user has in its year-book finally
     *          (after all year-books will be returned back)
     */
    int[] findSignatureCounts(int[] arr);

    /**
     * This is a graph where groups of students conform the disjoint loops (cycles).
     * The final result array must correspond to the length of cycle each user belong to.
     * <hr/>
     * TODO: add "Disjoint-SubSet-Union-Find" implementation
     */
    enum Solution implements FB_Prep_Arrays__Passing_YearBooks {
        /**
         * In a brute-force approach we can calculate the content of each year-book one by one in a loop.
         * Together with inner loop for each year-book we come up with <code>O(N^2)</code> time-complexity.
         */
        BRUTE_FORCE {
            @Override
            public int[] findSignatureCounts(int[] arr) {
                final int N = arr.length;
                System.out.println("--------------------------------------------");
                System.out.printf("arr --> %s N = %d;%n", Arrays.toString(arr), N);
                List<? extends Set<Integer>> yearBooks =
                    Stream.generate(() -> new LinkedHashSet<Integer>())
                    .limit(N).toList();
                for (int ybNum = 0; ybNum < N; ybNum++) {
                    Set<Integer> yb = yearBooks.get(ybNum);
                    int stNum = ybNum;
                    for (int step = 0; step < N; step++) {
                        if (yb.contains(stNum + 1)) {
                            break;
                        }
                        yb.add(stNum + 1);
                        stNum = arr[stNum] - 1;
                    }
                    System.out.printf("yb #%d: %s --> size = %d;%n", ybNum + 1, yb, yb.size());
                }
                System.out.println("... returning: " + yearBooks.stream().map(Collection::size).toList());
                return yearBooks.stream().mapToInt(Collection::size).toArray();
            }
//        },
//        UNION_FIND {
//            public int[] findSignatureCounts(int[] arr) {
//                return new int[0];
//            }
        };
    }
}
