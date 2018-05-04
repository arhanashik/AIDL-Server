package com.w3engineers.core.aidlserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.w3engineers.core.Person;
import com.w3engineers.core.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvMsg;

    private static List<String> list = new ArrayList<>();
    private static List<Person> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMsg = findViewById(R.id.txt_msg);

        list.clear();
        list.add("Val 1");
        list.add("Val 2");
        list.add("Val 3");

        persons.clear();
        persons.add(new Person(1, "P 1", 19));
        persons.add(new Person(2, "P 2", 49));
        persons.add(new Person(3, "P 3", 29));

        String msg = "AIDL SERVER\n\n";

        msg += "String array to sent:";
        for(String s : list){
            msg += "\n" + s;
        }

        msg += "\n\nObject to sent:";
        for (Person p : persons){
            msg += "\nID:" + p.getId() + ", Name:" + p.getName() + ", Age:" + p.getAge();
        }

        tvMsg.setText(msg);
    }

    public static List<String> getList() {
        if(list.size() == 0){
            list.add("Str 1");
            list.add("Str 2");
            list.add("Str 3");
        }
        return list;
    }

    public static void setList(List<String> list) {
        MainActivity.list = list;
    }

    public static List<Person> getPersons() {
        if(persons.size() == 0){
            persons.add(new Person(4, "Pn 1", 19));
            persons.add(new Person(5, "Pn 2", 49));
            persons.add(new Person(6, "Pn 3", 29));
        }
        return persons;
    }

    public static void setPersons(List<Person> persons) {
        MainActivity.persons = persons;
    }
}
