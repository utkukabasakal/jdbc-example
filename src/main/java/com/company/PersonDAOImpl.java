package com.company;

import model.Person;

import java.sql.*;

import static java.lang.System.getProperty;

public class PersonDAOImpl implements PersonDAO throws SQLException{
        /*jdbcUrl = prop.getProperty("dburl");
        jdbcUsername = prop.getProperty("username");
        jdbcPassword = prop.getProperty("password");*/
        PersonDAOImpl() {
        @Override
        public Person getPersonById ( int personId){
        ResultSet resultSet;
        DbHelper dbHelper = new DbHelper();
        while (resultSet.next()) {
        int id = resultSet.getInt(personId);
        String isim = resultSet.getString("isim");
        String soyisim = resultSet.getString("soyisim");
        Person person = new Person(id, isim, soyisim);
        return person;
        }
        @Override
        public void ReadPerson () {
            Connection connection = null;
            DbHelper dbHelper = new DbHelper();
            Statement statement = null;
            try {
                connection = dbHelper.getConnection();
                System.out.println("Bağlantı kuruldu");
                statement = connection.createStatement();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("id") + "    " + resultSet.getString("isim") + "   " + resultSet.getString("soyisim"));
                }
            } catch (SQLException exception) {
                dbHelper.showErrorMessage(exception);
            } finally {
                connection.close();
            }
        }
        @Override
        public static void UpdatePerson () {
            Connection connection = null;
            DbHelper dbHelper = new DbHelper();
            PreparedStatement statement = null;
            try {
                connection = dbHelper.getConnection();
                statement = connection.prepareStatement(sql);
                statement.executeUpdate();
                System.out.println("Kayit guncellendi.");

            } catch (SQLException exception) {
                dbHelper.showErrorMessage(exception);
            } finally {
                statement.close();
                connection.close();
            }
        }
        @Override
        public void CreatePerson () {
            Connection connection = null;
            DbHelper dbHelper = new DbHelper();
            PreparedStatement statement = null;
            try {
                connection = dbHelper.getConnection();
                statement.executeUpdate();
                System.out.println("Kayit eklendi.");

            } catch (SQLException exception) {
                dbHelper.showErrorMessage(exception);
            } finally {
                statement.close();
                connection.close();
            }
        }
        @Override
        public void DeletePerson () {
            Connection connection = null;
            DbHelper dbHelper = new DbHelper();
            PreparedStatement statement = null;
            try {
                connection = dbHelper.getConnection();
                statement = connection.prepareStatement(sql);
                statement.executeUpdate();
                System.out.println("Kayit silindi.");

            } catch (SQLException exception) {
                dbHelper.showErrorMessage(exception);
            } finally {
                statement.close();
                connection.close();
            }
        }
        private ResultSet executeQuery(String sql, Object... queryParameters) {
        try {

        PreparedStatement ps=connection.prepareStatement(sql);
        int index=1;
        if(queryParameters!=null){
                for(Object paramater:queryParameters){
                ps.setObject(index++,paramater);
                }
        }
        ResultSet resultSet=ps.executeQuery();
        return resultSet;
        } catch (Exception e) {
        System.err.println("ResultSet functionunda hata meydana geldi  HATA :"+e);
        }
        return null;
        }
    }
}