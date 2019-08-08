package com;

import com.dao.PersonDAOImpl;
import com.model.Person;

public class App {
    public static void main (String [] args){
       PersonDAOImpl personDAO = new PersonDAOImpl();
        Person p=new Person(7,"veli","genc");
        personDAO.updatePerson(p);
        System.out.println(p);
        PersonDAOImpl personDAO1= new PersonDAOImpl();
        personDAO1.getPersonAll();
    }
}
