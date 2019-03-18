package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDBTables {

    private static DBConnection conn = new DBConnection();

    public static void createTables() {

        String addressTableSchema = "CREATE TABLE address ("
                + "id int(11) NOT NULL AUTO_INCREMENT, "
                + "postcode varchar(256) DEFAULT NULL, "
                + "country varchar(256) DEFAULT NULL, "
                + "region varchar(256) DEFAULT NULL, "
                + "city varchar(256) DEFAULT NULL, "
                + "street varchar(256) DEFAULT NULL, "
                + "house int(11) DEFAULT NULL, "
                + "flat int(11) DEFAULT NULL, "
                + "PRIMARY KEY (id) "
                + ")";

        String personsTableSchema = "CREATE TABLE persons ("
                + "id int(11) NOT NULL AUTO_INCREMENT, "
                + "surname varchar(256) DEFAULT NULL, "
                + "name varchar(256) DEFAULT NULL, "
                + "middlename varchar(256) DEFAULT NULL, "
                + "birthday date DEFAULT NULL, "
                + "gender varchar(1) DEFAULT NULL, "
                + "inn varchar(12) DEFAULT NULL, "
                + "address_id int(11) NOT NULL, "
                + "PRIMARY KEY (id) "
                + "CONSTRAINT `address_id` FOREIGN KEY (`id`) REFERENCES `excel_project`.`address` (`id`) "
                + ")";

        try (Connection connection = conn.getDBConnection();
             Statement stmt = connection.createStatement()) {

            stmt.execute(addressTableSchema);
            System.out.println("Table \"address\" is created!");

            stmt.execute(personsTableSchema);
            System.out.println("Table \"persons\" is created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
