package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.krmdemo.techlabs.utils.BitSetUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.krmdemo.techlabs.leet_code_0000_1000.Problem_077__Combinations.Solution.toOneBasedList;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_077__Combinations}
 *
 * @see <a href="https://en.wikipedia.org/wiki/Combinatorial_number_system">
 *      Combinatorial number system
 *     </a>
 */
public class TestCase_077__Combinations {

    @EnumSource
    @ParameterizedTest
    void test_ex_01(Problem_077__Combinations.Solution sln) {
        assertThat(sln.combine(4, 2)).contains(
            List.of(1, 2), List.of(1, 3), List.of(2, 3),
            List.of(1, 4), List.of(2, 4), List.of(3, 4)
        );
    }

    @EnumSource
    @ParameterizedTest
    void test_ex_02(Problem_077__Combinations.Solution sln) {
        assertThat(sln.combine(1, 1)).contains(List.of(1));
    }

    @EnumSource
    @ParameterizedTest
    void test_5_choose_3(Problem_077__Combinations.Solution sln) {
        List<List<Integer>> resultList = sln.combine(5, 3);
        assertThat(resultList).hasSize(10).contains(
            List.of(1, 2, 3),
            List.of(1, 2, 4),
            List.of(1, 3, 4),
            List.of(2, 3, 4),
            List.of(1, 2, 5),
            List.of(1, 3, 5),
            List.of(2, 3, 5),
            List.of(1, 4, 5),
            List.of(2, 4, 5),
            List.of(3, 4, 5)
        );
    }

    @EnumSource
    @ParameterizedTest
    void test_5_choose_2(Problem_077__Combinations.Solution sln) {
        List<List<Integer>> resultList = sln.combine(5, 2);
        assertThat(resultList).hasSize(10).contains(
            List.of(1, 2),
            List.of(1, 3),
            List.of(1, 4),
            List.of(1, 5),
            List.of(2, 3),
            List.of(2, 4),
            List.of(2, 5),
            List.of(3, 4),
            List.of(3, 5),
            List.of(4, 5)
        );
    }

    /**
     * @see <a href="https://en.wikipedia.org/wiki/Combination#Example_of_counting_combinations">
     *     Example of counting combinations
     * </a>
     */
    @EnumSource
    @ParameterizedTest
    void test_52_choose_5(Problem_077__Combinations.Solution sln) {
        List<List<Integer>> resultList = sln.combine(52, 5);
        assertThat(resultList.size()).isEqualTo(2_598_960);
        assertThat(resultList.getFirst()).isEqualTo(List.of(1, 2, 3, 4, 5));
        assertThat(resultList.getLast()).isEqualTo(List.of(48, 49, 50, 51, 52));
    }

    // ------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------

    int N = 5;
    int K = 3;

    @Test
    void testBacktracking() {
        System.out.printf("%n----- testBacktracking ( N = %d, K = %d ) ------%n", N, K);
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(new BitSet(), 0, bitSet -> {
            ans.add(toOneBasedList(bitSet));
            System.out.printf("%3d) %s = %s = %s;%n",
                ans.size(), bitSet, binaryHex(bitSet), BitSetUtils.toBigInt(bitSet));
        });
        backtrack(new ArrayList<>(), 0);
    }

    public void backtrack(BitSet curr, int firstBit, Consumer<BitSet> onNext) {
        if (curr.cardinality() == K) {
            onNext.accept(curr);
            return;
        }
        int lastBit = N - K + curr.cardinality();
        for (int bitIndex = firstBit; bitIndex <= lastBit; bitIndex++) {
            curr.set(bitIndex);
            backtrack(curr, bitIndex + 1, onNext);
            curr.clear(bitIndex);
        }
    }

    private String binaryHex(BitSet bitSet) {
        return IntStream.range(0, N)
            .mapToObj(bitIndex -> bitSet.get(N - bitIndex - 1) ? "1" : "0")
            .collect(Collectors.joining("","b'","'"));
    }

    public void backtrack(List<Integer> curr, int firstNum) {
        if (curr.size() == K) {
            System.out.println(curr);
            return;
        }

        int need = K - curr.size();
        int remain = N - firstNum + 1;
        int available = remain - need;

        for (int num = firstNum; num < firstNum + available; num++) {
            curr.add(num);
            backtrack(curr, num + 1);
            curr.removeLast();
        }
    }

    private static void nextCombination(BitSet bitSet) {
        // looking for the first 'clear'/'set' consecutive pair of bits
        int indexOfClearSet = bitSet.nextClearBit(0);
        if (indexOfClearSet < bitSet.length()) {
            while (!bitSet.get(indexOfClearSet + 1)) {
                indexOfClearSet++;
            }
        }
        // looking for the first 'set'/'clear' consecutive pair of bits
        int indexOfSetClear = bitSet.nextSetBit(0);
        while (bitSet.get(indexOfSetClear + 1)) {
            indexOfSetClear++;
        }
        bitSet.set(indexOfSetClear + 1);
        if (indexOfClearSet > indexOfSetClear) {
            // flip the pair 'set'/'clear'
            bitSet.clear(indexOfSetClear);
        } else {
            // flip the 'set'-block and 'clear-block
            int lengthSet = indexOfSetClear - indexOfClearSet;
            bitSet.set(0, lengthSet - 1);
            bitSet.clear(lengthSet - 1, indexOfSetClear + 1);
        }
    }

    @Test
    void testOneZeroPair() {
        System.out.printf("%n----- testOneZeroPair ( N = %d, K = %d ) ------%n", N, K);
        int count = 0;
        BitSet bitSet = new BitSet();
        bitSet.set(0, K);
        while (bitSet.length() <= N) {
            System.out.printf("%3d) %s = %s = %s;%n",
                ++count, bitSet, binaryHex(bitSet), BitSetUtils.toBigInt(bitSet));
            nextCombination(bitSet);
        }
    }
}
