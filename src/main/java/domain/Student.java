package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private final List<Tuple> exams;
    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = new ArrayList<Tuple>(Arrays.asList(exams));
    }

    public JsonObject toJsonObject() {
        JsonObject jo = new BasicStudent(name, surname, year).toJsonObject();
        JsonArray jsonArray = new JsonArray();
        Iterator<Tuple> jsonIterator = exams.iterator();
        while (jsonIterator.hasNext()) {
            Tuple ex = jsonIterator.next();
            JsonPair newJsonPair1 = new JsonPair("course", new JsonString(ex.getKey().toString()));
            int mark = Integer.parseInt(ex.getValue().toString());
            JsonPair newJsonPair2 = new JsonPair("mark", new JsonNumber(mark));
            boolean passed = false;
            if (mark > 2){
                passed = true;
            }
            JsonPair newJsonPair3 = new JsonPair("passed", new JsonBoolean(passed));
            JsonObject newJsonObject =  new JsonObject(newJsonPair1, newJsonPair2, newJsonPair3);
            jsonArray.add_json(newJsonObject);
        }
        jo.add(new JsonPair("exams", jsonArray));
        return jo;
    }
}
