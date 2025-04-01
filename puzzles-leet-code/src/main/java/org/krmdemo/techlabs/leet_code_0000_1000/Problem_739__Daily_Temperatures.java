package org.krmdemo.techlabs.leet_code_0000_1000;

import java.util.*;
import java.util.stream.IntStream;

/**
 * <h3><a href="https://leetcode.com/problems/daily-temperatures/description/">
 *     739. Daily Temperatures
 * </a></h3>
 * Given an array of integers <code>temperatures</code> represents the daily temperatures,
 * return an array <code>answer</code> such that <code>answer[<b>i</b>]</code> is the number of days
 * you have to wait after the <code><b>i</b>th</code> day to get a warmer temperature.
 * If there is no future day for which this is possible, keep <code>answer[<b>i</b>] == 0</code> instead.
 * <h5>Constraints:</h5><pre>
 *     1 <= temperatures.length <= 10^5
 *     30 <= temperatures[i] <= 100
 * </pre>
 *
 * @see org.krmdemo.techlabs.interview.meta.FB_Prep_Arrays__Contiguous_SubArrays
 */
public interface Problem_739__Daily_Temperatures {

    /**
     * Solution entry-point.
     *
     * @param temperatures the daily temperature array
     * @return the forward distance to the next greater value in array <code>temperatures</code>
     */
    int[] dailyTemperatures(int[] temperatures);

    enum Solution implements Problem_739__Daily_Temperatures {
        DEFAULT;

        @Override
        public int[] dailyTemperatures(int[] temperatures) {

            class DailyTemperature {
                final int day;
                DailyTemperature nextIncreasing = null;
                private DailyTemperature(int day) { this.day = day; }
                int day() { return this.day; }
                int tmpr() { return temperatures[day()]; }
                int daysToNextIncreasing() {
                    return nextIncreasing == null ? 0 : nextIncreasing.day() - this.day();
                }
                @Override public String toString() {
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.dump());
                    if (nextIncreasing != null) {
                        sb.append(String.format(" next increasing in %d days --> %s",
                            daysToNextIncreasing(), nextIncreasing.dump()));
                    }
                    return sb.toString();
                }
                String dump() {
                    return String.format("day #%d : tmpr = %d;", day(), tmpr());
                }
            }

            List<DailyTemperature> dayTmprList = IntStream.range(0, temperatures.length)
                .mapToObj(DailyTemperature::new).toList();
//        System.out.println("before calculating the next increasing days:");
//        dayTmprList.forEach(System.out::println);

            Deque<DailyTemperature> monoDescStack = new ArrayDeque<>();  // <-- monotonically descending stack
            for (DailyTemperature current : dayTmprList) {
                if (monoDescStack.isEmpty()) {
                    monoDescStack.push(current);
                    continue;
                }
                while (!monoDescStack.isEmpty()
                    && monoDescStack.peek().tmpr() < current.tmpr()) {
                    monoDescStack.pop().nextIncreasing = current;
                }
                monoDescStack.push(current);
            }

//        System.out.println("after calculating the next increasing days:");
//        dayTmprList.forEach(System.out::println);

            return dayTmprList.stream()
                .mapToInt(DailyTemperature::daysToNextIncreasing)
                .toArray();
        }
    }
}
