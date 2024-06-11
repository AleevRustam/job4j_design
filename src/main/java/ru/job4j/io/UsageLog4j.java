package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 127;
        short sh = 32767;
        int age = 2_000_000;
        long l = 1L;
        double d = 10.10;
        float f = 10.11F;
        char ch = 'a';
        boolean isTrue = true;
        LOG.debug("Primitive types: byte : {}, short : {}, int : {}, long : {}"
                + ", double : {}, float : {}, char : {}, boolean : {}", b, sh, age, l, d, f, ch, isTrue);
    }
}
