package dao;

import model.Person;

public class App {
    public static void main (String [] args){
       PersonDAOImpl personDAO = new PersonDAOImpl();
        Person person=new Person(7,"ali","genc");
        personDAO.updatePerson(person);
       System.out.println(person);
    }
}
