package com.dao;

import com.sun.org.apache.xml.internal.dtm.ref.DTMDefaultBaseTraversers;
import com.model.Person;

import java.util.*;
import java.io.*;
import java.sql.*;

import static java.lang.System.getProperty;

public class PersonDAOImpl implements PersonDAO {
    private Connection connection;
    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPassword;

    private final String GET_PERSON_ID = "SELECT * FROM person Where id=?";
    private final String ADD_PERSON = "INSERT INTO person(id,isim,soyisim) VALUES(?,?,?)";
    private final String UPDATE_PERSON = "UPDATE person SET isim=?,soyisim=? WHERE id=? ";
    private final String DELETE_USER = "  DELETE FROM person WHERE id=?";

    public PersonDAOImpl() {
        setConnection();
    }

    private void setConnection() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = ClassLoader.getSystemClassLoader().getResourceAsStream("database.properties");
            prop.load(input);
        } catch (IOException io) {
            System.err.println("input oluşturmada hata meydana geldi  HATA : " + io);
        }

        jdbcUrl = prop.getProperty("jdbc.url");
        jdbcUsername = prop.getProperty("jdbc.username");
        jdbcPassword = prop.getProperty("jdbc.password");

        Properties properties = new Properties();
        properties.setProperty("user", jdbcUsername);
        properties.setProperty("password", jdbcPassword);
        properties.setProperty("useUnicode", "yes");
        properties.setProperty("characterEncoding", "UTF-8");
        properties.setProperty("serverTimezone", "UTC");
        properties.setProperty("autoReconnect", "true");
        properties.setProperty("useSSL", "false");

        try {
            connection = DriverManager.getConnection(jdbcUrl, properties);
            System.out.println("Veritababı bağlantısı kurulmuştur");
        } catch (Exception e) {
            System.err.println("Veritababı bağlantısı kurulamamıştır...  HATA : " + e);
        }
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
                return person;
            }
        } catch (Exception e) {
            System.err.println("hata: " + e);
        }
        return null;
    }

    @Override
    public void updatePerson(Person person) {
        try {
            execute(UPDATE_PERSON, person.getIsim(), person.getSoyisim(), person.getId());
            System.out.println("Kayit guncellendi.");

        } catch (Exception exception) {
            System.err.println(exception);
        }
    }

    @Override
    public void getPersonAll() {
        try {
            Statement statement=null;
            ResultSet resultSet ;
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM person");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id") + "    " + resultSet.getString("isim") + "   " + resultSet.getString("soyisim"));
            }
        } catch (Exception exception) {
            System.err.println(exception);
        }

    }

    @Override
    public void addPerson(Person person) {
        try {
            execute(ADD_PERSON, person.getId(), person.getIsim(), person.getSoyisim());
            System.out.println("Kayit eklendi.");

        } catch (Exception exception) {
            System.err.println(exception);
        }
    }

    @Override
    public void deletePerson(int personId) {
        try {
            execute(DELETE_USER, personId);
            System.out.println("Kayit silindi.");

        } catch (Exception exception) {
            System.err.println(exception);
        }
    }

    private void execute(String sql, Object... queryParameters) {
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            int index = 1;
            if (queryParameters != null) {
                for (Object paramater : queryParameters) {
                    ps.setObject(index++, paramater);
                }
            }
            ps.execute();

        } catch (Exception e) {
            System.err.println("ResultSet functionunda hata meydana geldi  HATA :" + e);
        }
    }

    private ResultSet executeQuery(String sql, Object... queryParameters) {
        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            int index = 1;
            if (queryParameters != null) {
                for (Object paramater : queryParameters) {
                    ps.setObject(index++, paramater);
                }
            }
            ResultSet resultSet = ps.executeQuery();
            return resultSet;
        } catch (Exception e) {
            System.err.println("ResultSet functionunda hata meydana geldi  HATA :" + e);
        }
        return null;
    }
}

