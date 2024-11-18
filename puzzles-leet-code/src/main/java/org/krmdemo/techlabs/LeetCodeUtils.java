package org.krmdemo.techlabs;

import java.net.URI;

public class LeetCodeUtils {

    public static final URI LEET_CODE_HOME = URI.create("https://leetcode.com");

    public static URI resolveLeetCodeURI(String suffixURL) {
        return LEET_CODE_HOME.resolve(suffixURL);
    }
}
