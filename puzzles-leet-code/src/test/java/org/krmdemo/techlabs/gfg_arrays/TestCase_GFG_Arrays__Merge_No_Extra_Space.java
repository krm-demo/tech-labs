package org.krmdemo.techlabs.gfg_arrays;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Geek-For-Geeks puzzle {@link GFG_Arrays__Merge_No_Extra_Space}
 */
public class TestCase_GFG_Arrays__Merge_No_Extra_Space {

    private final GFG_Arrays__Merge_No_Extra_Space sln =
        GFG_Arrays__Merge_No_Extra_Space.Solution.FLIP_MERGE;

    @Test
    void test_ex_01() {
        int[] a = new int[] { 2, 4, 7, 10 };
        int[] b = new int[] { 2, 3 };
        sln.mergeArrays(a, b);
        System.out.printf("result: a --> %s ; b --> %s ;%n",
            Arrays.toString(a), Arrays.toString(b));
        assertThat(a).containsExactly( 2, 2, 3, 4 );
        assertThat(b).containsExactly( 7, 10 );
    }

    @Test
    void test_ex_02() {
        int[] a = new int[] { 1, 5, 9, 10, 15, 20 };
        int[] b = new int[] { 2, 3, 8, 13 };
        sln.mergeArrays(a, b);
        System.out.printf("result: a --> %s ; b --> %s ;%n",
            Arrays.toString(a), Arrays.toString(b));
        assertThat(a).containsExactly( 1, 2, 3, 5, 8, 9 );
        assertThat(b).containsExactly( 10, 13, 15, 20 );
    }

    @Test
    void test_ex_03() {
        int[] a = new int[] { 0, 1 };
        int[] b = new int[] { 2, 3 };
        sln.mergeArrays(a, b);
        System.out.printf("result: a --> %s ; b --> %s ;%n",
            Arrays.toString(a), Arrays.toString(b));
        assertThat(a).containsExactly( 0, 1 );
        assertThat(b).containsExactly( 2, 3 );
    }

    @Test
    void test_flip_01() {
        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        int flipIndex = 3;
        System.out.println("before --> " + sln.dumpFlipArr(arr, 0, arr.length, flipIndex));
        sln.flip(arr, 0, arr.length, flipIndex);
        System.out.println("after ---> " + sln.dumpFlipArr(arr, 0, arr.length, arr.length - flipIndex));
        assertThat(arr).containsExactly(4, 5, 6, 7, 1, 2, 3);
        System.out.println();
    }

    @Test
    void test_flip_02() {
        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        int flipIndex = 4;
        System.out.println("before --> " + sln.dumpFlipArr(arr, 0, arr.length, flipIndex));
        sln.flip(arr, 0, arr.length, flipIndex);
        System.out.println("after ---> " + sln.dumpFlipArr(arr, 0, arr.length, arr.length - flipIndex));
        assertThat(arr).containsExactly(5, 6, 7, 1, 2, 3, 4);
        System.out.println();
    }

    @Test
    void test_flip_03() {
        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        int flipIndex = 1;
        System.out.println("before --> " + sln.dumpFlipArr(arr, 0, arr.length, flipIndex));
        sln.flip(arr, 0, arr.length, flipIndex);
        System.out.println("after ---> " + sln.dumpFlipArr(arr, 0, arr.length, arr.length - flipIndex));
        assertThat(arr).containsExactly(2, 3, 4, 5, 6, 7, 1);
        System.out.println();
    }

    @Test
    void test_flip_04() {
        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        int flipIndex = 0;
        System.out.println("before --> " + sln.dumpFlipArr(arr, 0, arr.length, flipIndex));
        sln.flip(arr, 0, arr.length, flipIndex);
        System.out.println("after ---> " + sln.dumpFlipArr(arr, 0, arr.length, arr.length - flipIndex));
        assertThat(arr).containsExactly(1, 2, 3, 4, 5, 6, 7);
        System.out.println();
    }

    @Test
    void test_flip_05() {
        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        int flipIndex = 6;
        System.out.println("before --> " + sln.dumpFlipArr(arr, 0, arr.length, flipIndex));
        sln.flip(arr, 0, arr.length, flipIndex);
        System.out.println("after ---> " + sln.dumpFlipArr(arr, 0, arr.length, arr.length - flipIndex));
        assertThat(arr).containsExactly(7, 1, 2, 3, 4, 5, 6);
        System.out.println();
    }

    @Test
    void test_flip_06() {
        int[] arr = new int[] { 1, 2, 3, 4, 5, 6, 7 };
        int flipIndex = 7;
        System.out.println("before --> " + sln.dumpFlipArr(arr, 0, arr.length, flipIndex));
        sln.flip(arr, 0, arr.length, flipIndex);
        //noinspection ConstantValue
        System.out.println("after ---> " + sln.dumpFlipArr(arr, 0, arr.length, arr.length - flipIndex));
        assertThat(arr).containsExactly(1, 2, 3, 4, 5, 6, 7);
        System.out.println();
    }
}
