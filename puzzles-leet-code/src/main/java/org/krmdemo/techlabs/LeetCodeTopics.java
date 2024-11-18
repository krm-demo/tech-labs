package org.krmdemo.techlabs;

import java.net.URI;

import static org.krmdemo.techlabs.LeetCodeUtils.resolveLeetCodeURI;

/**
 * This Java-enum encapsulates the proper way to resolve particular algorithmic-puzzle
 */
public enum LeetCodeTopics {

    DYNAMIC_PROGRAMMING(
        "Dynamic Programming",
        "/problem-list/dynamic-programming/"
    ),

    UNION_FIND(
        "Disjoint-Set Union-Find (DSU)",
        "/problem-list/union-find/"
    );

    final String title;
    final URI leetCodeURI;

    LeetCodeTopics(String title, String suffixURL) {
        this.title = title;
        this.leetCodeURI = resolveLeetCodeURI(suffixURL);
    }

    @Override
    public String toString() {
        return this.title;
    }
}
