package org.krmdemo.techlabs.interview.meta;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * <h3><a href="https://www.metacareers.com/profile/coding_practice_question/?problem_id=720422605157879&psid=275492097255885&practice_plan=0&p=GENERAL_SWE&b=0111122">
 *     Sorting #2. Counting Triangles (distinct).
 * </a></h3>
 * Given a list of <b><code>N</code></b> triangles with integer side's lengths,
 * determine how many <b>different triangles</b> there are.
 * <hr/>
 * <small>
 * Two triangles are considered to be the same if they can both be placed on the plane
 * such that their vertices occupy exactly the same three points.
 * </small>
 */
public interface FB_Prep_Sorting__Count_Distinct_Trinagles {

    /**
     * This class is provided by <a href="https://www.metacareers.com/">META</a>
     * and represents the triangle by the sizes of their 3 sides.
     * <hr/>
     * It's guaranteed that all triplets of side lengths represent real triangles.
     */
    class Sides {
        int a;
        int b;
        int c;
        Sides(int a,int b,int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    /**
     * Solution entry-point.
     *
     * @param arr an array of triangles represented by {@link Sides the sizes of their sides}
     * @return the number of distinct triangles in the array <code>arr</code>
     */
    int countDistinctTriangles(ArrayList<Sides> arr)  ;

    enum Solution implements FB_Prep_Sorting__Count_Distinct_Trinagles {
        /**
         * This implementation is based on {@link HashSet}, that requires the class of elements
         * to implement the methods {@link Object#hashCode()} and {@link Object#equals(Object)} properly.
         */
        HASH_SET {
            @Override
            @SuppressWarnings("SimplifyStreamApiCallChains")
            public int countDistinctTriangles(ArrayList<Sides> arr) {
                class SidesWrapper extends Sides {
                    final List<Integer> sidesSortedList;
                    SidesWrapper(Sides sides) {
                        super(sides.a, sides.b, sides.c);
                        this.sidesSortedList = IntStream.of(sides.a, sides.b, sides.c)
                            .boxed()
                            .sorted()
                            .collect(toList());
                    }
                    @Override
                    public int hashCode() {
                        return this.sidesSortedList.hashCode();
                    }
                    @Override
                    @SuppressWarnings("PatternVariableCanBeUsed")  // <-- only since JDK-16
                    public boolean equals(Object obj) {
                        if (!(obj instanceof SidesWrapper)) {
                            return false;
                        } else {
                            SidesWrapper that = (SidesWrapper)obj;
                            return this.sidesSortedList.equals(that.sidesSortedList);
                        }
                    }
                    @Override
                    public String toString() {
                        return "SidesWrapper" + this.sidesSortedList;
                    }
                }
                List<SidesWrapper> listOfWrapped = arr.stream()
                    .map(SidesWrapper::new)
                    .collect(toList());  // <-- only since JDK-16
                System.out.println("listOfWrapped.size = " + listOfWrapped.size() + ":");
                listOfWrapped.stream().forEach(sw -> System.out.println("- " + sw));
                Set<SidesWrapper> setOfWrapped = new HashSet<>(listOfWrapped);
                System.out.println("setOfWrapped.size = " + setOfWrapped.size() + ":");
                return setOfWrapped.size();
            }
        },
        SORTED_SET {
            @Override
            public int countDistinctTriangles(ArrayList<Sides> arr) {
                final Function<Sides, String> sidesToString =sides ->
                    IntStream.of(sides.a, sides.b, sides.c)
                        .sorted()
                        .mapToObj(String::valueOf)
                        .collect(joining(";"));
                SortedSet<Sides> sortedSet = new TreeSet<>(Comparator.comparing(sidesToString));
                sortedSet.addAll(arr);
                return sortedSet.size();
            }
        };
    }
}
