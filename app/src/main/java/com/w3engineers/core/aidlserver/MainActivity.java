package com.w3engineers.core.aidlserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.w3engineers.core.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static List<String> list = new ArrayList<>();
    private static List<Person> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list.clear();
        list.add("Val 1");
        list.add("Val 2");
        list.add("Val 3");

        persons.clear();
        persons.add(new Person(1, "P 1", 19));
        persons.add(new Person(2, "P 2", 49));
        persons.add(new Person(3, "P 3", 29));
    }

    public static List<String> getList() {
        return list;
    }

    public static void setList(List<String> list) {
        MainActivity.list = list;
    }

    public static List<Person> getPersons() {
        return persons;
    }

    public static void setPersons(List<Person> persons) {
        MainActivity.persons = persons;
    }
}
