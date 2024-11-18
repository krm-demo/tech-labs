package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_046__Permutations}
 */
public class TestCase_046__Permutations {

    private final Problem_046__Permutations sln = Problem_046__Permutations.Solution.ITER_NEXT_PERM;

    @Test
    public void test_ex_01() {
        assertThat(sln.permute(new int[] { 1, 2, 3 })).isEqualTo(asList(
            asList(1,2,3), asList(1,3,2), asList(2,1,3),
            asList(2,3,1), asList(3,1,2), asList(3,2,1)
        ));
    }
}
