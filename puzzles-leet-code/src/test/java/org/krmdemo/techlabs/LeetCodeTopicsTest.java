package org.krmdemo.techlabs;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.krmdemo.techlabs.LeetCodeUtils.LEET_CODE_HOME;

@Slf4j
public class LeetCodeTopicsTest {

    @Test
    void dumpLeetCodeTopics() {
        log.info("" + LEET_CODE_HOME);
        dumpLeetCodeTopics(LeetCodeTopics.DYNAMIC_PROGRAMMING);
        dumpLeetCodeTopics(LeetCodeTopics.UNION_FIND);
    }

    private static void dumpLeetCodeTopics(LeetCodeTopics leetCodeTopics) {
        log.info("'{}' --> {}", leetCodeTopics, leetCodeTopics.leetCodeURI);
    }
}
