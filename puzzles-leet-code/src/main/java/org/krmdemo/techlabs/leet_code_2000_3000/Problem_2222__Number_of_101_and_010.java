package org.krmdemo.techlabs.leet_code_2000_3000;

import java.util.*;

/**
 * <h3><a href="https://leetcode.com/problems/number-of-ways-to-select-buildings/description/?envType=study-plan-v2&envId=amazon-spring-23-high-frequency">
 *     2222. Number of Ways to Select Buildings
 * </a></h3>
 * You are given a <code>0</code>-indexed binary string <b><code>s</code></b>,
 * which represents the types of buildings along a street where:<ul>
 *     <li><code>s[i] = '0'</code> denotes that the <code>i</code>th building is an office;</li>
 *     <li><code>s[i] = '1'</code> denotes that the <code>i</code>th building is a restaurant;</li>
 * </ul>
 * As a city official, you would like to select <b>3 buildings</b> for random inspection.
 * However, to ensure variety, no two consecutive buildings out of the selected buildings can be of the same type.
 * <hr/>
 * For example, given <code>s = "001101"</code>, we cannot select the
 * 1st, 3rd, and 5th buildings as that would form <code>"011"</code> which is not allowed
 * due to having two consecutive buildings of the same type.
 * <hr/>
 * Return the number of valid ways to select 3 buildings.
 * <h5>Constraints:</h5><pre>
 * 3 <= s.length <= 10^5
 * s[i] is either '0' or '1'.
 * </pre>
 */
public interface Problem_2222__Number_of_101_and_010 {

    /**
     * Solution entry-point
     *
     * @param s a binary-string (contains '0' or '1')
     * @return the lexicographically largest sequence, that satisfies the requirements above
     */
    long numberOfWays(String s);

    enum Solution implements Problem_2222__Number_of_101_and_010 {
        DEFAULT;

        @Override
        public long numberOfWays(String s) {
            List<BuildingsAround> listAround = new ArrayList<>();
            int zerosBeforeCount = 0;
            int onesBeforeCount = 0;
            for (int i = 0; i < s.length(); i++) {
                BuildingsAround around = new BuildingsAround();
                around.zerosBefore = zerosBeforeCount;
                around.onesBefore = onesBeforeCount;
                listAround.add(around);
                char currentBuilding = s.charAt(i);
                if (currentBuilding == '0') {
                    zerosBeforeCount++;
                } else if (currentBuilding == '1') {
                    onesBeforeCount++;
                }
            }
            int zerosAfterCount = 0;
            int onesAfterCount = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                BuildingsAround around = listAround.get(i);
                around.zerosAfter = zerosAfterCount;
                around.onesAfter = onesAfterCount;
                char currentBuilding = s.charAt(i);
                if (currentBuilding == '0') {
                    zerosAfterCount++;
                } else if (currentBuilding == '1') {
                    onesAfterCount++;
                }
            }
            System.out.println("s = '" + s + "'");
            int totalCount = 0;
            for (int i = 0; i < s.length(); i++) {
                BuildingsAround around = listAround.get(i);
                char building = s.charAt(i);
                int multOthers = around.multOthers(building);
                System.out.printf("%2d) '%c' --> %s :: %d;%n",
                    i, building, around, multOthers);
                totalCount += multOthers;
            }
            System.out.println("totalCount = " + totalCount);
            return totalCount;
        }

        static class BuildingsAround {
            int zerosBefore = 0;
            int zerosAfter = 0;
            int onesBefore = 0;
            int onesAfter = 0;
            int multZeros() {
                return zerosBefore * zerosAfter;
            }
            int multOnes() {
                return onesBefore * onesAfter;
            }
            int multOthers(char building) {
                return building == '0' ? multOnes() : multZeros();
            }
            @Override
            public String toString() {
                return String.format("'0'%d,'1'%d <|> '1'%d,'0'%d",
                    zerosBefore, onesBefore, zerosAfter, onesAfter);
            }
        }
    }
}
