package ru.job4j.generics;

public class Animal {
    String name;

    public Animal() {
        this.name = "Animal";
    }

    @Override
    public String toString() {
        return "Animal{"
                + "name='" + name + '\''
                + '}';
    }
}
