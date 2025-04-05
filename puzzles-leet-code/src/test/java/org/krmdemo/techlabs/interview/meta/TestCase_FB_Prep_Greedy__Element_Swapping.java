package org.krmdemo.techlabs.interview.meta;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for META/Facebook interview preparation puzzle {@link FB_Prep_Greedy__Element_Swapping}
 *
 * @see <a href="/org/krmdemo/techlabs/interview/meta/FB_Prep_Greedy__Element_Swapping.py">
 *     A python-solution from chat-GPT
 * </a>
 */
public class TestCase_FB_Prep_Greedy__Element_Swapping {

    final FB_Prep_Greedy__Element_Swapping sln =
        FB_Prep_Greedy__Element_Swapping.Solution.IN_PLACE;

    @Test
    void test_ex_01() {
        assertThat(sln.findMinArray(new int[] { 5, 3, 1 }, 2))
            .containsExactly( 1, 5, 3 );
//            .containsExactly( 1, 3, 5 );  // <-- the result of using single-swap instead multi-swap
    }

    @Test
    void test_ex_02() {
        assertThat(sln.findMinArray(new int[] { 8, 9, 11, 2, 1 }, 3))
            .containsExactly( 2, 8, 9, 11, 1 );
//            .containsExactly( 2, 9, 11, 8, 1 );  // <-- the result of using single-swap instead multi-swap
    }

    @Test
    void test_chatGPT() {
        assertThat(sln.findMinArray(new int[] { 5, 2, 9, 1, 5, 6 }, 1))
            .containsExactly( 2, 5, 9, 1, 5, 6 );
        assertThat(sln.findMinArray(new int[] { 5, 2, 9, 1, 5, 6 }, 2))
            .containsExactly( 2, 5, 1, 9, 5, 6 );
        assertThat(sln.findMinArray(new int[] { 5, 2, 9, 1, 5, 6 }, 3))
            .containsExactly( 1, 5, 2, 9, 5, 6 );
//            .containsExactly( 1, 2, 9, 5, 5, 6 );  // <-- the result of using single-swap instead multi-swap
        assertThat(sln.findMinArray(new int[] { 5, 2, 9, 1, 5, 6 }, 4))
            .containsExactly( 1, 2, 5, 9, 5, 6 );
        assertThat(sln.findMinArray(new int[] { 5, 2, 9, 1, 5, 6 }, 5))
            .containsExactly( 1, 2, 5, 5, 9, 6 );
        assertThat(sln.findMinArray(new int[] { 5, 2, 9, 1, 5, 6 }, 6))
            .containsExactly( 1, 2, 5, 5, 6, 9 );
        assertThat(sln.findMinArray(new int[] { 5, 2, 9, 1, 5, 6 }, 7))
            .containsExactly( 1, 2, 5, 5, 6, 9 );
        assertThat(sln.findMinArray(new int[] { 5, 2, 9, 1, 5, 6 }, 17))
            .containsExactly( 1, 2, 5, 5, 6, 9 );
    }
}
