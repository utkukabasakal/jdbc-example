package com.dao;

import com.model.Person;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface PersonDAO {
    public Person getPersonById(int personId);
    public void addPerson(Person person);
    public ArrayList<Person> getPersonAll();
    public void updatePerson(Person person);
    public void deletePerson(int personId);
}