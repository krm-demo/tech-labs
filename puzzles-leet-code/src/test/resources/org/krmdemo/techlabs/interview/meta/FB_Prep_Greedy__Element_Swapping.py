"""
`META-Prep - Greedy #2. Element Swapping`_

To solve the problem of finding the lexicographically smallest sequence after at most k element swaps
(where each swap involves a pair of consecutive elements), we can follow a greedy approach.

Approach:

    Greedy Strategy:
    At each step, we try to place the smallest possible number in the current position,
    using the allowed swaps. If we are at index i, we look for the smallest element
    within the next k elements (since we can swap only consecutive elements) and move it to the current position.
    This reduces the problem to progressively fixing the sequence from left to right while respecting the swap limit k.

    Choosing the Smallest Element:
    - For each position i, we need to find the smallest element in the subarray arr[i, i+1, ..., i+k]
      (as we can swap only up to k positions ahead).
    - We swap this smallest element to position i, and each swap will decrement k by the number of swaps used.

    Termination: We continue until either:
    - The array is sorted lexicographically.
    - Or we've made k swaps.

.. _`META-Prep - Greedy #2. Element Swapping`: https://www.metacareers.com/profile/coding_practice_question/?problem_id=838749853303393&c=3513648482275061&psid=275492097255885&practice_plan=0&b=0111122
"""

def lexicographically_smallest_sequence(arr: list[int], k: int) -> list[int]:
    """
    Solution entry-point

    :param arr: initial array
    :param k: the maximum number of swaps
    :return: the result array
    """

    n = len(arr)

    # Iterate through the array
    for i in range(n):
        if k <= 0:
            break  # No swaps left, stop

        # Find the smallest element within the next k elements
        min_idx = i
        for j in range(i + 1, min(i + k + 1, n)):
            if arr[j] < arr[min_idx]:
                min_idx = j

        # If the minimum element is not at the current position, we swap it to the front
        if min_idx != i:
            # Swap elements from min_idx to i, which will bring the min element to position i
            for j in range(min_idx, i, -1):
                arr[j], arr[j - 1] = arr[j - 1], arr[j]
            k -= (min_idx - i)  # Decrease the number of available swaps

    return arr


# Example usage:
for k in range(1, 10):
    arr = [5, 2, 9, 1, 5, 6]
    print(f"k = {k}; {arr = } --> ", end='')
    lexicographically_smallest_sequence(arr, k)
    print(f"result --> {arr}", end='')
    if sorted(arr) == arr:
        print(' SORTED !!!')
    else:
        print(' still NOT sorted')

# Output must look something like:
# >>> k = 1; arr = [5, 2, 9, 1, 5, 6] --> result --> [2, 5, 9, 1, 5, 6] still NOTsorted
# >>> k = 2; arr = [5, 2, 9, 1, 5, 6] --> result --> [2, 5, 1, 9, 5, 6] still NOT sorted
# >>> k = 3; arr = [5, 2, 9, 1, 5, 6] --> result --> [1, 5, 2, 9, 5, 6] still NOT sorted
# >>> k = 4; arr = [5, 2, 9, 1, 5, 6] --> result --> [1, 2, 5, 9, 5, 6] still NOT sorted
# >>> k = 5; arr = [5, 2, 9, 1, 5, 6] --> result --> [1, 2, 5, 5, 9, 6] still NOT sorted
# >>> k = 6; arr = [5, 2, 9, 1, 5, 6] --> result --> [1, 2, 5, 5, 6, 9] SORTED !!!
# >>> k = 7; arr = [5, 2, 9, 1, 5, 6] --> result --> [1, 2, 5, 5, 6, 9] SORTED !!!
# >>> k = 8; arr = [5, 2, 9, 1, 5, 6] --> result --> [1, 2, 5, 5, 6, 9] SORTED !!!
# >>> k = 9; arr = [5, 2, 9, 1, 5, 6] --> result --> [1, 2, 5, 5, 6, 9] SORTED !!!
