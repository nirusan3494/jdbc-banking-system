package com.bank;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        Properties props = new Properties();

        InputStream is = DBConnection.class
                .getClassLoader()
                .getResourceAsStream("config.properties");

        if (is == null) {
            throw new RuntimeException("config.properties not found");
        }

        props.load(is);

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String pass = props.getProperty("db.password");

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }
}
