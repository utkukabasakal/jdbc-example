package com;

import com.dao.PersonDAO;
import com.dao.PersonDAOImpl;
import com.model.Person;

public class App {
    public static void main (String [] args) {

        PersonDAO personDAO = new PersonDAOImpl();
        Person person=new Person(7,"veli","genc");
        personDAO.updatePerson(person);
        System.out.println(person);
        personDAO.getPersonAll();

    }
}
