package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

class DBConnection {

    private static final String DB_CONFIG = Objects.requireNonNull(DBConnection.class.getClassLoader().
            getResource("dbconfig")).getPath();

    Connection getDBConnection() {
        Properties props = new Properties();
        FileInputStream fis;
        Connection connection = null;
        try {
            fis = new FileInputStream(DB_CONFIG);
            props.load(fis);

            /* Load the driver */
            Class.forName(props.getProperty("JDBC_DRIVER"));

            /* Create connection */
            connection = DriverManager.getConnection(
                    props.getProperty("URL"),
                    props.getProperty("USER"),
                    props.getProperty("PASS"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}