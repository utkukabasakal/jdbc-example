package dao;

import model.Person;

public class App {
    public static void main (String [] args){
       PersonDAOImpl personDAO = new PersonDAOImpl();
        Person p=new Person(7,"ali","genc");
        personDAO.updatePerson(p);
       System.out.println(p);
    }
}
