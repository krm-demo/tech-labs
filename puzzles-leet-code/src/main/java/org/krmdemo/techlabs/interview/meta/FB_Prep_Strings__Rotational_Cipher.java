package org.krmdemo.techlabs.interview.meta;

import java.util.*;

/**
 * <h3><a href="https://www.metacareers.com/profile/coding_practice_question/?problem_id=238827593802550&c=3513648482275061&psid=275492097255885&practice_plan=0&b=0111122">
 *     Strings #1. Rotational Cipher.
 * </a></h3>
 * One simple way to encrypt a string is to <code>"rotate"</code> every alphanumeric character by a certain amount.
 * Rotating a character means replacing it with another character that is a certain number of steps away
 * in normal alphabetic or numerical order.
 * <hr/>
 * For example, if the string <code>"Zebra-493?"</code> is rotated <code>3</code> places,
 * the resulting string is <code>"Cheud-726?"</code>. Every alphabetic character is replaced
 * with the character <code>3</code> letters higher (wrapping around from Z to A),
 * and every numeric character replaced with the character <code>3</code> digits higher
 * (wrapping around from 9 to 0).
 * <hr/>
 * <u><i>Note:</i></u> the non-alphanumeric characters remain unchanged.
 * <hr/>
 * Given a string and a rotation factor, return an encrypted string.
 */
public interface FB_Prep_Strings__Rotational_Cipher {

    /**
     * Solution entry-point.
     *
     * @param input an input string to encrypt
     * @param rotationFactor a forward rotation factor
     * @return the encrypted string
     */
    String rotationalCipher(String input, int rotationFactor);

    /**
     * The default implementation is based on stack (in Java {@link LinkedList} is a good choice)
     */
    enum Solution implements FB_Prep_Strings__Rotational_Cipher {
        DEFAULT;

        @Override
        public String rotationalCipher(String input, int rotationFactor) {
            // TODO: implement and conver with unit-tests
            return "";
        }
    }
}
