package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <h3><a href="https://leetcode.com/problems/paint-house/description/">
 *     256. Paint House
 * </a></h3>
 * There is a row of <b><code>n</code></b> houses, where each house can be painted one of three colors:<ul>
 *     <li>red</li>
 *     <li>blue</li>
 *     <li>green</li>
 * </ul>
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that <b>no two adjacent houses</b> have the same color.
 * The cost of painting each house with a certain color is represented by an <code>n x 3</code> cost matrix costs.
 * <hr/>
 * For example, <code>costs[0][0]</code> is the cost of painting house <code>0</code> with the color red;
 * <code>costs[1][2]</code> is the cost of painting house <code>1</code> with color green, and so on...
 * <hr/>
 * Return the <b>minimum cost</b> to paint all houses.
 */
public interface Problem_256__Paint_Houses {

    /**
     * Solution entry-point.
     *
     * @param costs The cost of painting each house with a certain (one of three) color
     * @return the <b>minimum cost</b> to paint all houses
     */
    int minCost(int[][] costs);

    enum Color {
        RED, BLUE, GREEN
    }

    default List<EnumMap<Color,Integer>> colorMapList(int[][] costs) {
        return Arrays.stream(costs)
            .map(this::colorMap)
            .toList();
    }
    default EnumMap<Color,Integer> colorMap(int[] paintCost) {
        return Arrays.stream(Color.values()).collect(Collectors.toMap(
            Function.identity(),
            color -> paintCost[color.ordinal()],
            this::throwingMerger,
            this::newColorMap
        ));
    }
    default <T> T throwingMerger(T u, T v) {
        throw new IllegalStateException(String.format("Duplicate key %s", u));
    }
    default EnumMap<Color,Integer> newColorMap() {
        return new EnumMap<>(Color.class);
    }

    enum Solution implements Problem_256__Paint_Houses {
        DP_BOTTOM_UP_MATRIX {
            @Override
            public int minCost(int[][] costs) {
                Deque<EnumMap<Color,Integer>> paintedHouses = new ArrayDeque<>();
                for (EnumMap<Color,Integer> paintCost : colorMapList(costs)) {
                    EnumMap<Color,Integer> currCost = newColorMap();
                    EnumMap<Color,Integer> prevCost = paintedHouses.peekLast();
                    for (Color color : Color.values()) {
                        int minCostPrev = prevCost == null ? 0 : prevCost.entrySet().stream()
                            .filter(e -> e.getKey() != color)
                            .min(CMP_COLOR_COST).orElseThrow().getValue();
                        currCost.put(color, minCostPrev + paintCost.get(color));
                    }
                    paintedHouses.addLast(currCost);
                }
                EnumMap<Color,Integer> lastCost = paintedHouses.peekLast();
                return lastCost == null ? 0 : lastCost.entrySet().stream()
                    .min(CMP_COLOR_COST).orElseThrow().getValue();
            }
        },
        DP_BOTTOM_UP_LAST_ROW {
            @Override
            public int minCost(int[][] costs) {
                EnumMap<Color,Integer> prevCost = null;
                for (EnumMap<Color,Integer> paintCost : colorMapList(costs)) {
                    EnumMap<Color,Integer> currCost = newColorMap();
                    for (Color color : Color.values()) {
                        int minCostPrev = prevCost == null ? 0 : prevCost.entrySet().stream()
                            .filter(e -> e.getKey() != color)
                            .min(CMP_COLOR_COST).orElseThrow().getValue();
                        currCost.put(color, minCostPrev + paintCost.get(color));
                    }
                    prevCost = currCost;
                }
                return prevCost == null ? 0 : prevCost.entrySet().stream()
                    .min(CMP_COLOR_COST).orElseThrow().getValue();
            }
        },
        DP_TOP_DOWN {
            @Override
            public int minCost(int[][] costs) {
                CostCalculator costCalc = new CostCalculator(costs);
                return costCalc.minCostSince(null, 0);
            }
        },
        DP_TOP_DOWN_MEMO {
            @Override
            public int minCost(int[][] costs) {
                CostCalculator costCalc = new CostCalculatorMemo(costs);
                return costCalc.minCostSince(null, 0);
            }
        };

        final Comparator<Map.Entry<Color,Integer>> CMP_COLOR_COST =
            Comparator.comparingInt(Map.Entry::getValue);

        class CostCalculator {
            final List<EnumMap<Color,Integer>> costsList;
            CostCalculator(int[][] costs) {
                this.costsList = colorMapList(costs);
            }
            int housesCount() {
                return costsList.size();
            }
            int minCostSince(Color prevColor, int houseNum) {
                if (houseNum == housesCount()) {
                    return 0;
                }
                EnumMap<Color,Integer> currColorMap = newColorMap();
                costsList.get(houseNum).forEach((color, cost) -> {
                    if (color == prevColor) {
                        return;
                    }
                    currColorMap.put(color, cost + minCostSince(color, houseNum + 1));
                });
                Map.Entry<Color, Integer> minColorCost =
                    currColorMap.entrySet().stream().min(CMP_COLOR_COST).orElseThrow();
//                System.out.printf("prevColor = %5s, houseNum = %d --> %s;%n", prevColor, houseNum, minColorCost);
                return minColorCost.getValue();
            }
        }

        class CostCalculatorMemo extends CostCalculator {
            final List<EnumMap<Color,Integer>> memo;
            public CostCalculatorMemo(int[][] costs) {
                super(costs);
                this.memo = Stream.generate(Solution.this::newColorMap).limit(costs.length + 1).toList();
//                this.memo = Collections.nCopies(costs.length + 1, newColorMap());  // <-- this will not work !!!
//                this.memo = new ArrayList<>(costs.length + 1);
//                for (int i = 0; i <= costs.length; i++) {
//                    this.memo.add(newColorMap());
//                }
                Arrays.stream(Color.values()).forEach(color ->
                    this.memo.get(costs.length).put(color, 0)
                );
            }
            @Override
            int minCostSince(Color prevColor, int houseNum) {
                EnumMap<Color,Integer> memoItem = memo.get(houseNum);
                if (prevColor != null && memoItem.containsKey(prevColor)) {
                    return memoItem.get(prevColor);
                }
                int minCost = super.minCostSince(prevColor, houseNum);
                if (prevColor != null) {
                    memoItem.put(prevColor, minCost);
                }
                return minCost;
            }
        }

    }
}
