package domain;

import json.*;

/**
 * Created by Andrii_Rodionov on 1/5/2017.
 */
public class BasicStudent implements Jsonable {

    protected String name;
    protected String surname;
    protected Integer year;

    public BasicStudent() {
    }

    public BasicStudent(String name, String surname, Integer year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonPair pair1 = new JsonPair("name", new JsonString(name));
        JsonPair pair2 = new JsonPair("surname", new JsonString(surname));
        JsonPair pair3 = new JsonPair("year", new JsonNumber(year));
        JsonObject jo = new JsonObject(pair1, pair2, pair3);
        return jo;
    }
}
