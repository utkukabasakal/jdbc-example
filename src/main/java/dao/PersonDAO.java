package dao;

import model.Person;

public interface PersonDAO {
    public Person getPersonById(int personId);
    public void addPerson(int personId);
    public void getPerson (Person person);
    public void updatePerson(Person person);
    public void deletePerson(int personId);
}