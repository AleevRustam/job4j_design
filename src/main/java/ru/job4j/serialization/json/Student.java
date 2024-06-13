package ru.job4j.serialization.json;

import java.util.Arrays;

public class Student {
    private final boolean sex;
    private final int level;
    private final Address address;
    private final String[] subjects;

    public Student(boolean sex, int level, Address address, String[] subjects) {
        this.sex = sex;
        this.level = level;
        this.address = address;
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "sex:" + sex
                + ", level:" + level
                + ", address:" + address
                + ", subjects:" + Arrays.toString(subjects)
                + '}';
    }
}
