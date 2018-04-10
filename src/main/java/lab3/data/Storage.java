package lab3.data;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Storage {
    private static Storage instance;
    private int lastKey = 1;

    public static Storage getInstance() {
        if (instance == null)
            instance = new Storage();
        return instance;
    }
    private final Map<Integer, Person> people = new TreeMap<>();
    {
        TreeMap<String, String> a = new TreeMap<>();
        a.put("name","name1");
        a.put("surname","name2");
        a.put("sex","false");
        this.addPerson(new Person(a));
        a.put("name", "name2");
        a.put("surname", "surname2");
        a.put("sex","true");
        this.addPerson(new Person(a));
    }

    public void addPerson(Person p)
    {
        people.put(lastKey++,p);
    }

    public Map<Integer, Person> getPeople() {
        return new TreeMap<>(people);
    }
    public void removeAll(int... keys)
    {
        Arrays.stream(keys).forEach(people::remove);
    }
    public void update(Integer key, Person p)
    {
        people.put(key, p);
    }
}
