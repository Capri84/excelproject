package database;

import creators.CreatePersons;
import model.Person;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Random;

import static java.sql.JDBCType.DATE;

public class DBOperations {

    private static final String DB_NAME = "excel_project";
    private static final String ADDRESS_TABLE = ".address";
    private static final String PERSONS_TABLE = ".persons";
    private static DBConnection conn = new DBConnection();

    public static void fillDB() {
        try (Connection connection = conn.getDBConnection();
             Statement stmt = connection.createStatement()) {

            for (Person person : CreatePersons.persons) {
                String query = "SELECT * FROM " + DB_NAME + PERSONS_TABLE + " where persons.name = " +
                        "'" + person.getName() + "'" + " AND persons.surname = " + "'" + person.getSurname() + "'" +
                        " AND persons.middlename = " + "'" + person.getPatronymic() + "'";
                ResultSet resultSet = stmt.executeQuery(query);

                if (resultSet.next()) {
                    String name = resultSet.getString("persons.name");
                    String surname = resultSet.getString("persons.surname");
                    String patronymic = resultSet.getString("persons.middlename");
                    System.out.println("Identical FIO: " + name + " " + surname + " " + patronymic + " will be updated.");

                    // Update person
                    String updateAddressQuery = "update " + DB_NAME + ADDRESS_TABLE + " set " +
                            "address.postcode = " + person.getPostCode() + ", " +
                            "address.country = " + person.getCountry() + ", " +
                            "address.region = " + person.getRegion() + ", " +
                            "address.city = " + person.getCity() + ", " +
                            "address.street = " + person.getStreet() + ", " +
                            "address.house = " + person.getHouseNum() + ", " +
                            "address.flat = " + person.getFlatNum();
                    stmt.executeUpdate(updateAddressQuery);

                    String updatePersonsQuery = "update " + DB_NAME + PERSONS_TABLE + "set " +
                            "persons.birthday = " + person.getDateOfBirth() + ", " +
                            "persons.gender = " + person.getGender() + ", " +
                            "persons.inn = " + person.getInn();
                    stmt.executeUpdate(updatePersonsQuery);
                } else {
                    // Insert person
                    String insertAddressQuery = "insert into " + DB_NAME + ADDRESS_TABLE + " (address.postcode, " +
                            "address.country, address.region, address.city, address.street, address.house, " +
                            "address.flat) " +
                            "values (" + person.getPostCode() + ", " +
                            "'" + person.getCountry().replaceAll("'", "''") + "'" + ", " +
                            "'" + person.getRegion().replaceAll("'", "''") + "'" + ", " +
                            "'" + person.getCity().replaceAll("'", "''") + "'" + ", " +
                            "'" + person.getStreet().replaceAll("'", "''") + "'" + ", " +
                            person.getHouseNum() + ", " +
                            person.getFlatNum() + ")";
                    stmt.executeUpdate(insertAddressQuery);

                    String insertPersonsQuery = "insert into " + DB_NAME + PERSONS_TABLE + " (persons.surname, " +
                            "persons.name, persons.middlename, persons.birthday, persons.gender, persons.inn, " +
                            "persons.address_id) " +
                            "values (" +
                            "'" + person.getSurname().replaceAll("'", "''") + "'" + ", " +
                            "'" + person.getName().replaceAll("'", "''") + "'" + ", " +
                            "'" + person.getPatronymic().replaceAll("'", "''") + "'" + ", " +
                            DATE + " '" + person.getDateOfBirth() + "'" + ", " +
                            "'" + person.getGender() + "'" + ", " +
                            "'" + person.getInn() + "'" + ", " + "last_insert_id()" + ")";
                    stmt.executeUpdate(insertPersonsQuery);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectRndFromDB(int peopleAmount) {
        try (Connection connection = conn.getDBConnection();
             Statement stmt = connection.createStatement()) {

            // Count current number of lines in the table
            ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM " + DB_NAME + ADDRESS_TABLE);
            resultSet.last();
            int numOfLines = resultSet.getInt(1);

            if (peopleAmount <= numOfLines) {
                for (int i = 0; i < peopleAmount; i++) {
                    Random random = new Random();
                    int rndLine = random.nextInt(numOfLines) + 1;
                    ResultSet rset = stmt.executeQuery("SELECT * FROM " + DB_NAME + ADDRESS_TABLE + ", " +
                            DB_NAME + PERSONS_TABLE +
                            " WHERE excel_project.address.id = excel_project.persons.address_id" +
                            " LIMIT " + rndLine + ", 1");
                    if (rset.next()) {
                        String name = rset.getString("persons.name");
                        String surname = rset.getString("persons.surname");
                        String patronymic = rset.getString("persons.middlename");
                        String gender = rset.getString("persons.gender");
                        int age = CreatePersons.calculateAge(rset.getDate("persons.birthday").toLocalDate(),
                                LocalDate.now());
                        LocalDate dateOfBirth = rset.getDate("persons.birthday").toLocalDate();
                        long inn = Long.parseLong(rset.getString("persons.inn"));
                        String country = rset.getString("address.country");
                        String postCode = rset.getString("address.postcode");
                        String region = rset.getString("address.region");
                        String city = rset.getString("address.city");
                        String street = rset.getString("address.street");
                        int houseNum = rset.getInt("address.house");
                        int flatNum = rset.getInt("address.flat");
                        CreatePersons.persons.add(new Person(name, surname, patronymic, age, gender, dateOfBirth, inn,
                                postCode, country, region, city, street, houseNum, flatNum));
                    }
                }
            } else {
                for (int i = 0; i < numOfLines; i++) {
                    ResultSet rset = stmt.executeQuery("SELECT * FROM " + DB_NAME + ADDRESS_TABLE + ", " +
                            DB_NAME + PERSONS_TABLE +
                            " WHERE excel_project.address.id = excel_project.persons.address_id" +
                            " LIMIT " + i + ", 1");
                    if (rset.next()) {
                        String name = rset.getString("persons.name");
                        String surname = rset.getString("persons.surname");
                        String patronymic = rset.getString("persons.middlename");
                        String gender = rset.getString("persons.gender");
                        int age = CreatePersons.calculateAge(rset.getDate("persons.birthday").toLocalDate(),
                                LocalDate.now());
                        LocalDate dateOfBirth = rset.getDate("persons.birthday").toLocalDate();
                        long inn = Long.parseLong(rset.getString("persons.inn"));
                        String country = rset.getString("address.country");
                        String postCode = rset.getString("address.postcode");
                        String region = rset.getString("address.region");
                        String city = rset.getString("address.city");
                        String street = rset.getString("address.street");
                        int houseNum = rset.getInt("address.house");
                        int flatNum = rset.getInt("address.flat");
                        CreatePersons.persons.add(new Person(name, surname, patronymic, age, gender, dateOfBirth, inn,
                                postCode, country, region, city, street, houseNum, flatNum));
                    }
                }
                CreatePersons personCreator = new CreatePersons();
                try {
                    personCreator.buildPersonsList(peopleAmount - numOfLines);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
