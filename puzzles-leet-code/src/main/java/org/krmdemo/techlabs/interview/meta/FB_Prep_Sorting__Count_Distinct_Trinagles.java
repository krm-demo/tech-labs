package org.krmdemo.techlabs.interview.meta;

import java.util.*;

/**
 * <h3><a href="https://www.metacareers.com/profile/coding_practice_question/?problem_id=720422605157879&psid=275492097255885&practice_plan=0&p=GENERAL_SWE&b=0111122">
 *     Sorting #2. Counting Triangles (distinct).
 * </a></h3>
 * Given a list of N triangles with integer side lengths, determine how many different triangles there are. Two triangles are considered to be the same if they can both be placed on the plane such that their vertices occupy exactly the same three points.
 */
public interface FB_Prep_Sorting__Count_Distinct_Trinagles {

    /**
     * This class is provided by <a href="https://www.metacareers.com/">META</a>
     * and represents the triangle by the sizes of their 3 sides.
     * <hr/>
     * It's guaranteed that all triplets of side lengths represent real triangles.
     */
    class Sides{
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

    /**
     * The default implementation is based on stack (in Java {@link LinkedList} is a good choice)
     */
    enum Solution implements FB_Prep_Sorting__Count_Distinct_Trinagles {
        HASH_SET {
            @Override
            public int countDistinctTriangles(ArrayList<Sides> arr) {
                // TODO: override "equals", put into HashSet and return "size()"
                return 0;
            }
        },
        SORTED_SET {
            @Override
            public int countDistinctTriangles(ArrayList<Sides> arr) {
                // TODO: implement "Comparator", put into TreeSet and return "size()"
                return 0;
            }
        };
    }
}
