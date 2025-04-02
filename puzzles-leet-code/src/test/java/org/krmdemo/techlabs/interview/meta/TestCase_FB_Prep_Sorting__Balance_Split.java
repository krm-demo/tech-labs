package org.krmdemo.techlabs.interview.meta;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for META/Facebook interview preparation puzzle {@link FB_Prep_Sorting__Balance_Split}
 */
@TestMethodOrder(MethodOrderer.MethodName.class) // <-- good example when order is strange without this annotation
public class TestCase_FB_Prep_Sorting__Balance_Split {

    final FB_Prep_Sorting__Balance_Split sln =
        FB_Prep_Sorting__Balance_Split.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.balancedSplitExists(new int[] { 1, 5, 7, 1 })).isTrue();
    }

    @Test
    void test_ex_02() {
        assertThat(sln.balancedSplitExists(new int[] { 12, 7, 6, 7, 6 })).isFalse();
    }

    @Test
    void test_ex_02a() {
        assertThat(sln.balancedSplitExists(new int[] { 2, 7, 6, 7, 6 })).isTrue();
    }

    @Test
    void test_main_01() {
        assertThat(sln.balancedSplitExists(new int[] { 2, 1, 2, 5 })).isTrue();
    }

    @Test
    void test_main_02() {
        assertThat(sln.balancedSplitExists(new int[] { 3, 6, 3, 4, 4 })).isFalse();
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("---------------------------------------");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("--- --- --- --- --- --- --- --- --- ---");
        System.out.println("--- --- --- --- --- --- --- --- --- ---");
    }
}
