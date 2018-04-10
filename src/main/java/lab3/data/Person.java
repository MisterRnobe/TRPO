package lab3.data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Person {
    private String name;
    private String surname;
    private String patronymic;

    private Short age;
    private Boolean sex;

    public Person(Map<String, String> initValues)
    {
        initValues.forEach((key, value) -> {
            try {
                set(key, value);
            } catch (IllegalAccessException | InvocationTargetException e1) {
                e1.printStackTrace();
            }
        });
    }

    public void set(String field, String value) throws IllegalAccessException, InvocationTargetException {
        Class<Person> personClass = Person.class;
        Method[] m = personClass.getMethods();
        for (Method method: m) {
            if (method.getName().equalsIgnoreCase("set"+field))
            {
                method.invoke(this, value);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(String age) {
        if (age.equals("null"))
            this.age = null;
        else
            this.age = Short.parseShort(age);
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(String sex) {
        if (sex.equals("null"))
            this.sex = null;
        else
            this.sex = sex.charAt(0) == 'M' || sex.charAt(0)=='m' || sex.charAt(0) == 't';
    }

    public static Person createOf(String[] strings)
    {
        String[] methods = new String[]{"name", "surname","patronymic","age","sex"};
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < methods.length; i++) {
            map.put(methods[i], strings[i]);
        }
        return new Person(map);
    }

}
