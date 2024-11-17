package org.krmdemo.techlabs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hello {

    /**
     * JVM entry-point
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello " + Hello.class);
        log.info("Hello from {}'s logging !!!", Hello.class.getSimpleName());
    }
}
