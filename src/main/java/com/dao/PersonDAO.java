package com.dao;

import com.model.Person;

import java.util.ArrayList;

public interface PersonDAO {
    public Person getPersonById(int personId);

    public void addPerson(Person person);

    public ArrayList<Person> getPersonAll();

    public void updatePerson(Person person);

    public void deletePerson(int personId);

    public void deletePerson(Person person);
}