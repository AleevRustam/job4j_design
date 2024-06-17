package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JAXBException {

        JSONObject jsonAddress = new JSONObject("{\"city\":\"Moscow\"}");
        List<String> subjects = new ArrayList<>();
        subjects.add("Math");
        subjects.add("Chemistry");
        JSONArray jsonSubjects = new JSONArray(subjects);

        final Student student = new Student(true, 3, new Address("Moscow"), "Math", "Chemistry");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", student.getSex());
        jsonObject.put("level", student.getLevel());
        jsonObject.put("address", jsonAddress);
        jsonObject.put("subjects", jsonSubjects);

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(student).toString());

    }
}
