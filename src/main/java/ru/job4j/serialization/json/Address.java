package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
public class Address {

    @XmlAttribute
    private String city;

    public Address() {
    }

    public Address(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "{city:" + city + "}";
    }
}
