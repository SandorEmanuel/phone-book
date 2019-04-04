package org.fasttrackit.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfiguration {

    public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
        Properties prop = new Properties();


        try (InputStream inputStream = DatabaseConfiguration.class.getClassLoader().getResourceAsStream("db.properties")) {
            prop.load(inputStream);
            prop.getProperty("DB_URL");


            Class.forName(prop.getProperty("DB_DRIVER_CLASS"));
            return DriverManager.getConnection(
                    prop.getProperty("DB_URL"),
                    prop.getProperty("DB_USERNAME"),
                    prop.getProperty("DB_PASSWORD"));
        }
    }
}