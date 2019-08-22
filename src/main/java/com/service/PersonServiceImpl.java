package com.service;

import com.dao.PersonDAO;
import com.dao.PersonDAOImpl;
import com.model.Person;

import java.util.ArrayList;

public class PersonServiceImpl implements PersonService {

    private PersonDAO personDAO;

    public PersonServiceImpl() throws ClassNotFoundException {
        this.personDAO = new PersonDAOImpl();
    }


    @Override
    public Person getPersonById(int personId) {
        return personDAO.getPersonById(personId);
    }

    @Override
    public void addPerson(Person person) {
        personDAO.addPerson(person);
    }

    @Override
    public ArrayList<Person> getPersonAll() {
        return personDAO.getPersonAll();
    }

    @Override
    public void updatePerson(Person person) {
        personDAO.updatePerson(person);
    }

    @Override
    public void deletePerson(int personId) {
        personDAO.deletePerson(personId);
    }

    @Override
    public void deletePerson(Person person) {
        personDAO.deletePerson(person);
    }
}


