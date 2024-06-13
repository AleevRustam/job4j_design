package ru.job4j.serialization.json;

public class Address {
    private final String city;

    public Address(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "{city:" + city + "}";
    }
}
