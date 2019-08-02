package com.company;

import model.Person;

public class test {
    public static void main (String [] args){
       PersonDAOImpl personDAO = new PersonDAOImpl();
        Person person=new Person(7,"musa","kalan");
        personDAO.UpdatePerson(person);
       System.out.println(person);
    }
}
