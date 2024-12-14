package org.krmdemo.techlabs.utils;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonUtilsTest {

    @Test
    void testDefault() {
        String resourcePath = "org/krmdemo/techlabs/leet_code_2000_3000/TestCase_2461__MaxSum_Distinct_SubArr.json";
        Map<String, Object> mapFromRes = JsonUtils.fromJsonRes(resourcePath);
        assertThat(mapFromRes).containsKeys("nums", "K");
    }
}
