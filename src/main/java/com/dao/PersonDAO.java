package com.dao;

import com.model.Person;

public interface PersonDAO {
    public Person getPersonById(int personId);
    public void addPerson(Person person);
    public void getPersonAll();
    public void updatePerson(Person person);
    public void deletePerson(int personId);
}