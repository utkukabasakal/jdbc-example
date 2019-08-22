package com.dao;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public abstract class AbstractCrudOperation {
    static Logger logger = Logger.getLogger(PersonDAO.class);
    private String jdbcClass;
    private String jdbcUrl;
    private String jdbcUsername;
    private String jdbcPassword;


    private Connection connection;
    public static final String PROPERTIES_FILE = "database.properties";

    public static Properties properties = new Properties();

    private Properties getPropertyOnText() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                // TODO Add your custom fail-over code here
                e.printStackTrace();
            }
        }
        return properties;
    }


    public void setConnection() throws ClassNotFoundException {

        Properties prop = getPropertyOnText();
        jdbcClass = prop.getProperty("jdbc.driverClassName");
        Class.forName(jdbcClass);
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
            logger.info("Veritababı bağlantısı kurulmuştur");
        } catch (Exception e) {
            logger.error("Veritababı bağlantısı kurulamamıştır...  HATA : " + e);
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
            logger.error("ResultSet functionunda hata meydana geldi  HATA :" + e);
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
            logger.error("ResultSet functionunda hata meydana geldi  HATA :" + e);
        }
        return null;
    }

}
