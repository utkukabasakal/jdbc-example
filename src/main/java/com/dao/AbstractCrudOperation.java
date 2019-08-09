package com.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public abstract class AbstractCrudOperation {
    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPassword;

    private Connection connection;



    public void setConnection() {

        Properties prop = getPropertyOnText();

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

    protected void execute(String sql, Object... queryParameters) {
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

    protected ResultSet executeQuery(String sql, Object... queryParameters) {
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

    private Properties getPropertyOnText() {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = ClassLoader.getSystemClassLoader().getResourceAsStream("database.properties");
            prop.load(input);
        } catch (IOException io) {
            System.err.println("input oluşturmada hata meydana geldi  HATA : " + io);
        }
        return prop;
    }
}