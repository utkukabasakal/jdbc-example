package com.company;

import model.Person;

public interface PersonDAO {
    public Person getPersonById(int personId);
    public void CreatePerson();
    public void ReadPerson ();
    public void UpdatePerson();
    public void DeletePerson();
}