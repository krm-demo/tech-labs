package org.krmdemo.techlabs.interview.meta;

import java.util.*;

/**
 * <h3><a href="https://www.metacareers.com/profile/coding_practice_question/?problem_id=211548593612944&psid=275492097255885&practice_plan=0&p=GENERAL_SWE&b=0111122">
 *     Balance Brackets
 * </a></h3>
 * TODO: add unit-test and introduce interface like in other puzzles
 * <hr/>
 * A bracket is any of the following characters: (, ), {, }, [, or ].
 * We consider two brackets to be matching if the first bracket is an open-bracket, e.g., (, {, or [, and the second bracket is a close-bracket of the same type. That means ( and ), [ and ], and { and } are the only pairs of matching brackets.
 * Furthermore, a sequence of brackets is said to be balanced if the following conditions are met:
 * The sequence is empty, or
 * The sequence is composed of two or more non-empty sequences, all of which are balanced, or
 * The first and last brackets of the sequence are matching, and the portion of the sequence without the first and last elements is balanced.
 * You are given a string of brackets. Your task is to determine whether each sequence of brackets is balanced. If a string is balanced, return true, otherwise, return false
 */
public class FB_Prep_Stacks__Balance_Brackets {

    boolean isBalanced(String s) {
        System.out.printf("s = '%s':%n", s);
        System.out.println("fromOpenMap --> " + fromOpenMap);
        System.out.println("fromCloseMap --> " + fromCloseMap);
        List<Bracket> brackets = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            Optional<Bracket> bracketOpen = Bracket.fromOpen(ch);
            if (bracketOpen.isPresent()) {
                System.out.println("adding - " + bracketOpen);
                brackets.add(bracketOpen.get());
                continue;
            }
            Optional<Bracket> bracketClose = Bracket.fromClose(ch);
            if (bracketClose.isPresent()) {
                System.out.println("closing - " + bracketClose);
                if (!isTheLastTheSameAs(brackets, bracketClose.get())) {
                    // the top brackets-stack IS NOT  the same as the current bracket to close
                    System.out.println("brackets --> " + brackets + ", but the last one is not " + bracketClose.get());
                    System.out.println("returning - " + false);
                    return false;
                }
                // in JDK-21 the expression "brackets.removeLast()" could be used
                //noinspection SequencedCollectionMethodCanBeUsed
                brackets.remove(brackets.size() - 1);
            }
        }
        // if all close brackets violate nothing - everything is considered balanced !!!
        System.out.println("brackets --> " + brackets);
        System.out.println("returning - " + brackets.isEmpty());
        return brackets.isEmpty();
    }

    /**
     * NOTE! There is no method {@link List#getLast()} in JDK prior to 21
     *
     * @param bracketsList the list of brackets whose last element to check
     * @param bracketToMatch the expected last element in the list
     * @return  <code>true</code> if the last element equales as expected, or otherwise - <code>false</code>
     */
    boolean isTheLastTheSameAs(List<Bracket> bracketsList, Bracket bracketToMatch) {
        int size = bracketsList.size();
        return size > 0 && bracketsList.get(size - 1) == bracketToMatch;
    }

    private static final Map<Character,Bracket> fromOpenMap = new HashMap<>();
    private static final Map<Character,Bracket> fromCloseMap = new HashMap<>();

    enum Bracket {
        ROUND('(',')'),
        SQUARE('[',']'),
        FIGURE('{','}');

        Bracket(char charOpen, char charClose) {
            fromOpenMap.put(charOpen, this);
            fromCloseMap.put(charClose, this);
        }

        static Optional<Bracket> fromOpen(char ch) {
            return Optional.ofNullable(fromOpenMap.get(ch));
        }

        static Optional<Bracket> fromClose(char ch) {
            return Optional.ofNullable(fromCloseMap.get(ch));
        }
    }
}
