package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {

    @XmlAttribute
    private boolean sex;

    @XmlAttribute
    private int age;
    private Contact contact;

    @XmlElementWrapper(name = "statuses")
    @XmlElement(name = "status")
    private String[] statuses;

    public Person() {
    }

    public Person(boolean sex, int age, Contact contact, String... statuses) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.statuses = statuses;
    }

    public boolean getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getStatuses() {
        return statuses;
    }

    @Override
    public String toString() {
        return "Person{"
                + "sex=" + sex
                + ", age=" + age
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(statuses)
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        final Person person = new Person(false, 30, new Contact("11-111"), "Worker", "Married");
        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-11-11\"}");

        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person.getSex());
        jsonObject.put("age", person.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);
        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(person).toString());
    }
}
