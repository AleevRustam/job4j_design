package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute
    private boolean sex;

    @XmlAttribute
    private int level;
    private Address address;

    @XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
    private String[] subjects;

    public Student() {
    }

    public Student(boolean sex, int level, Address address, String... subjects) {
        this.sex = sex;
        this.level = level;
        this.address = address;
        this.subjects = subjects;
    }

    public boolean getSex() {
        return sex;
    }

    public int getLevel() {
        return level;
    }

    public Address getAddress() {
        return address;
    }

    public String[] getSubjects() {
        return subjects;
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
