package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_261__Graph_Valid_Tree}
 */
public class TestCase_261__Graph_Valid_Tree {

    private final Problem_261__Graph_Valid_Tree sln =
        Problem_261__Graph_Valid_Tree.Solution.DEFAULT;

    @Test
    void test_ex_01() {
        assertThat(sln.validTree(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}})).isTrue();
    }

    @Test
    void test_ex_02() {
        assertThat(sln.validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}})).isFalse();
    }
}
