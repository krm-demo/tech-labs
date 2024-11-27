package org.krmdemo.techlabs.leet_code_0000_1000;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.rangeClosed;
import static org.assertj.core.api.Assertions.assertThat;
import static org.krmdemo.techlabs.leet_code_0000_1000.Problem_031__Next_Permutation.dump;
import static org.krmdemo.techlabs.utils.ArrayUtils.constantIntArr;
import static org.krmdemo.techlabs.utils.NumberUtils.factorialInt;

/**
 * Test-Case for Leet-Code puzzle {@link Problem_031__Next_Permutation}
 */
public class TestCase_031__Next_Permutation {

    private final Problem_031__Next_Permutation sln = Problem_031__Next_Permutation.Solution.DEFAULT;

    @Test
    public void test_ex_01() {
        int[] numsArr = new int[] { 1, 2, 3 };
        sln.nextPermutation(numsArr);
        assertThat(numsArr).isEqualTo(new int[] { 1, 3, 2 });
    }

    @Test
    public void test_ex_02() {
        int[] numsArr = new int[] { 3, 2, 1 };
        sln.nextPermutation(numsArr);
        assertThat(numsArr).isEqualTo(new int[] { 1, 2, 3 });
    }

    @Test
    public void test_ex_03() {
        int[] numsArr = new int[] { 1, 1, 5 };
        sln.nextPermutation(numsArr);
        assertThat(numsArr).isEqualTo(new int[] { 1, 5, 1 });
    }

    @Test
    public void testDuplicates() {
        int[] numsArr = new int[] { 5, 1, 1 };
        sln.nextPermutation(numsArr);
        assertThat(numsArr).isEqualTo(new int[] { 1, 1, 5 });
    }

    @Test
    public void testCombo_01110() {
        int[] numsArr = new int[] { 0, 1, 1, 1, 0 };
        System.out.print(Arrays.toString(numsArr) + " --> ");
        sln.nextPermutation(numsArr);
        System.out.println(Arrays.toString(numsArr));
        assertThat(numsArr).isEqualTo(new int[] { 1, 0, 0, 1, 1 });
    }

    @Test
    public void testComboTwo() {
        final int M = 3;
        final int K = 5;
        int[] numsArrM = constantIntArr(M, 0);
        int[] numsArrN = constantIntArr(K, 1);

        final int N = M + K;
        int[] numsArr = IntStream.concat(Arrays.stream(numsArrM), Arrays.stream(numsArrN)).toArray();
        int[] originalCopy = Arrays.copyOf(numsArr, numsArr.length);
        System.out.printf("%n==== testComboTwo(M = %d, K = %d, N = %d): ====%n", M, K, N);
        System.out.println("initial numsArr --> " + dump(numsArr));
        SortedSet<String> permutationsSet = new TreeSet<>();
        final int totalCount = factorialInt(N) / (factorialInt(M) * factorialInt(K));
        System.out.println("expected combinations number is " + totalCount);
        for (int i = 1; i < totalCount; i++) {
            System.out.printf("%n---- permutation (%d + %d = %d) #%d ----%n", M, K, N, i);
            sln.nextPermutation(numsArr);
            String numsStr = dump(numsArr);
            System.out.println(numsStr);
            permutationsSet.add(numsStr);
        }
        System.out.printf("%n=====================================%n");
        assertThat(permutationsSet).hasSize(totalCount - 1);
        System.out.println("following iteration must transform the array into initial order:");
        sln.nextPermutation(numsArr);
        assertThat(numsArr).isEqualTo(originalCopy);

        permutationsSet.add(dump(numsArr));
        System.out.println("permutationsSet.size = " + permutationsSet.size());
        assertThat(permutationsSet).hasSize(totalCount);
        int count = 0;
        for (String permStr : permutationsSet) {
            if (count % 10 == 0) {
                System.out.println();
            } else if (count > 0) {
                System.out.print("..");
            }
            System.out.print(permStr);
            count++;
        }
    }

    @Test
    public void testAscCycle5() {
        final int N = 5;
        System.out.printf("%n==== testAscCycle(N = %d): ====%n", N);
        int[] numsArr = rangeClosed(1, N).toArray();
        int[] originalCopy = Arrays.copyOf(numsArr, numsArr.length);
        SortedSet<String> permutationsSet = new TreeSet<>();
        for (int i = 1; i < factorialInt(N); i++) {
            System.out.printf("%n---- permutation #%d ----%n", i);
            sln.nextPermutation(numsArr);
            String numsStr = dump(numsArr);
            System.out.println(numsStr);
            permutationsSet.add(numsStr);
        }
        System.out.printf("%n=====================================%n");
        assertThat(permutationsSet).hasSize(factorialInt(N) - 1);
        System.out.println("following iteration must transform the array into initial order:");
        sln.nextPermutation(numsArr);
        assertThat(numsArr).isEqualTo(originalCopy);

        permutationsSet.add(dump(numsArr));
        System.out.println("permutationsSet.size = " + permutationsSet.size());
        assertThat(permutationsSet).hasSize(factorialInt(N));
        int count = 0;
        for (String permStr : permutationsSet) {
            if (count % 10 == 0) {
                System.out.println();
            } else if (count > 0) {
                System.out.print("..");
            }
            System.out.print(permStr);
            count++;
        }
    }

    @Test
    public void testAscCycle3() {
        final int N = 3;
        System.out.printf("%n==== testAscCycle(N = %d): ====%n", N);
        int[] numsArr = rangeClosed(7, 9).toArray();
        int[] originalCopy = Arrays.copyOf(numsArr, numsArr.length);
        SortedSet<String> permutationsSet = new TreeSet<>();
        for (int i = 1; i < factorialInt(N); i++) {
            System.out.printf("%n---- permutation #%d ----%n", i);
            sln.nextPermutation(numsArr);
            String numsStr = dump(numsArr);
            System.out.println(numsStr);
            permutationsSet.add(numsStr);
        }
        System.out.printf("%n=====================================%n");
        assertThat(permutationsSet).hasSize(factorialInt(N) - 1);
        System.out.println("following iteration must transform the array into initial order:");
        sln.nextPermutation(numsArr);
        assertThat(numsArr).isEqualTo(originalCopy);

        permutationsSet.add(dump(numsArr));
        System.out.println("permutationsSet.size = " + permutationsSet.size());
        assertThat(permutationsSet).hasSize(factorialInt(N));
        System.out.println("permutationsSet contains -->  " + permutationsSet);
    }
}
