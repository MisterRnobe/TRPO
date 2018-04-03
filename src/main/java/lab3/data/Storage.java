package lab3.data;

import java.util.ArrayList;

public class Storage {
    private static Storage instance;

    public static Storage getInstance() {
        if (instance == null)
            instance = new Storage();
        return instance;
    }
    private final ArrayList<Person> people = new ArrayList<>();
    public void addPerson(Person p)
    {
        people.add(p);
    }

    public ArrayList<Person> getPeople() {
        return new ArrayList<>(people);
    }
}
