package com.dao;

import com.model.Person;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.util.ArrayList;


public class PersonDAOImpl extends AbstractCrudOperation implements PersonDAO {

    static Logger logger = Logger.getLogger(PersonDAO.class);
    private final String GET_PERSON_ID = "SELECT * FROM person Where id=?";
    private final String ADD_PERSON = "INSERT INTO person(id,isim,soyisim) VALUES(?,?,?)";
    private final String UPDATE_PERSON = "UPDATE person SET isim=?,soyisim=? WHERE id=? ";
    private final String DELETE_USER = "DELETE FROM person WHERE id=?";
    private final String GET_PERSON_ALL = "SELECT * FROM person";


    public PersonDAOImpl() {
        setConnection();
    }

    @Override
    public Person getPersonById(int personId) {
        try {
            ResultSet resultSet = executeQuery(GET_PERSON_ID, personId);
            while (resultSet.next()) {
                int id = resultSet.getInt(personId);
                String isim = resultSet.getString("isim");
                String soyisim = resultSet.getString("soyisim");
                Person person = new Person(id, isim, soyisim);
                logger.info("Veritabanından veri çekilmiştir.");
                return person;
            }
        } catch (Exception e) {
           logger.error("Veritabanından veri çekilirken hata oluşmuştur  Hata: " + e);
        }
        return null;
    }

    @Override
    public void updatePerson(Person person) {
        try {
            execute(UPDATE_PERSON, person.getIsim(), person.getSoyisim(), person.getId());
           logger.info("Kayit guncellendi.");

        } catch (Exception e) {
            logger.error("Kayıt güncellenirken hata oluşmuştur.  Hata:" + e);
        }
    }

    @Override
    public ArrayList<Person> getPersonAll() {
        ArrayList<Person> personList = new ArrayList<Person>();
        try {
            ResultSet resultSet = executeQuery(GET_PERSON_ALL);
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setIsim(resultSet.getString("isim"));
                person.setSoyisim(resultSet.getString("soyisim"));
                personList.add(person);
                System.out.println(person);
                logger.info("Kayıt alındı.");
            }

        } catch (Exception e) {
            logger.error("Kayıt alınırken hata meydana gelmiştir.   Hata: "+e);
        }
        return personList;
    }

    @Override
    public void addPerson(Person person) {
        try {
            super.execute(ADD_PERSON, person.getId(), person.getIsim(), person.getSoyisim());
           logger.info("Kayit eklendi.");

        } catch (Exception exception) {
            logger.error("Kayıt eklenirken hata oluşmuştur.  Hata: "+exception);
        }
    }

    @Override
    public void deletePerson(int personId) {
        try {
            execute(DELETE_USER, personId);
            logger.info("Kayit silindi.");

        } catch (Exception exception) {
            logger.error("Kayıt silinirken hata oluşmuştur.  Hata:" + exception);
        }
    }
}

