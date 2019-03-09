package creators;

import generators.*;
import model.Person;
import model.Response;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static creators.CreatePDF.choose;

public class CreatePersons {

    public static Response response1 = null;
    public static int numOfPeople = NumOfPeopleGenerator.generateRandomInt();
    static List<Person> persons = new ArrayList<>();
    private static LocalDate dobDate;

    public static void buildPersonsList() throws IOException {
        String name;
        String gender;
        String patronymic;
        int age;
        LocalDate dateOfBirth;
        long inn;
        String country;
        String zipCode;
        String region;
        String city;
        String street;
        int houseNum;
        int flatNum;
        if (response1 != null) {
            for (int i = 0; i < numOfPeople; i++) {
                String nameApi = response1.getResults().get(i).getName().getFirst();
                name = nameApi.substring(0, 1).toUpperCase() + nameApi.substring(1);
                String surnameApi = response1.getResults().get(i).getName().getLast();
                String surname = surnameApi.substring(0, 1).toUpperCase() + surnameApi.substring(1);

                gender = null;
                patronymic = null;
                if (response1.getResults().get(i).getGender().equalsIgnoreCase("male")) {
                    gender = ResourcesData.MALE_GENDER;
                    patronymic = choose(new File(ResourcesData.PATRONYMICS_MALE), ResourcesData.PATRONYMICS_MALE);
                } else if (response1.getResults().get(i).getGender().equalsIgnoreCase("female")) {
                    gender = ResourcesData.FEMALE_GENDER;
                    patronymic = choose(new File(ResourcesData.PATRONYMICS_FEMALE), ResourcesData.PATRONYMICS_FEMALE);
                }
                age = response1.getResults().get(i).getDob().getAge();

                String dateOfBirthApi = response1.getResults().get(i).getDob().getDate();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                dateOfBirth = LocalDate.parse(dateOfBirthApi.substring(0, 10), dtf);

                inn = InnGenerator.makeINN();

                zipCode = response1.getResults().get(i).getLocation().getPostcode();
                if (Integer.parseInt(zipCode) < ZipGenerator.ZIP_LOWER_LIMIT || Integer.parseInt(zipCode) > ZipGenerator.ZIP_UPPER_LIMIT) {
                    zipCode = String.valueOf(ZipGenerator.generateRandomInt());
                }

                country = choose(new File(ResourcesData.COUNTRIES), ResourcesData.COUNTRIES);
                region = choose(new File(ResourcesData.REGIONS), ResourcesData.REGIONS);
                String cityApi = response1.getResults().get(i).getLocation().getCity();
                city = cityApi.substring(0, 1).toUpperCase() + cityApi.substring(1);

                String streetApi = response1.getResults().get(i).getLocation().getStreet();
                Pattern p = Pattern.compile("[a-z]");
                Matcher m = p.matcher(streetApi);
                int position = 0;
                if (m.find()) {
                    position = m.start();
                }
                street = streetApi.substring(0, position) + streetApi.substring(position, position + 1).toUpperCase() + streetApi.substring(position + 1);

                houseNum = HouseNumGenerator.generateRandomHouse();
                flatNum = FlatNumGenerator.generateRandomFlatNum();

                persons.add(new Person(name, surname, patronymic, age, gender, dateOfBirth, inn, zipCode, country,
                        region, city, street, houseNum, flatNum));
            }
        } else {
            List<String> surnames = new ArrayList<>();
            for (int j = 0; j < numOfPeople; j++) {
                surnames.add(choose(new File(ResourcesData.ALL_SURNAMES), ResourcesData.ALL_SURNAMES));
            }
            for (String surname : surnames) {
                if (ResourcesData.getMaleSurnamesList().contains(surname)) {
                    name = choose(new File(ResourcesData.NAMES_MALE), ResourcesData.NAMES_MALE);
                    patronymic = choose(new File(ResourcesData.PATRONYMICS_MALE), ResourcesData.PATRONYMICS_MALE);
                    gender = ResourcesData.MALE_GENDER;
                } else if (ResourcesData.getFemaleSurnamesList().contains(surname)) {
                    name = choose(new File(ResourcesData.NAMES_FEMALE), ResourcesData.NAMES_FEMALE);
                    patronymic = choose(new File(ResourcesData.PATRONYMICS_FEMALE), ResourcesData.PATRONYMICS_FEMALE);
                    gender = ResourcesData.FEMALE_GENDER;
                } else {
                    System.out.println(surname + " " + "Фамилия не найдена в списках");
                    continue;
                }

                age = calculateAge(generateDOB(), LocalDate.now());
                dateOfBirth = dobDate;
                inn = InnGenerator.makeINN();
                zipCode = String.valueOf(ZipGenerator.generateRandomInt());
                country = choose(new File(ResourcesData.COUNTRIES), ResourcesData.COUNTRIES);
                region = choose(new File(ResourcesData.REGIONS), ResourcesData.REGIONS);
                city = choose(new File(ResourcesData.CITIES), ResourcesData.CITIES);
                street = choose(new File(ResourcesData.STREETS), ResourcesData.STREETS);
                houseNum = HouseNumGenerator.generateRandomHouse();
                flatNum = FlatNumGenerator.generateRandomFlatNum();

                persons.add(new Person(name, surname, patronymic, age, gender, dateOfBirth, inn, zipCode, country,
                        region, city, street, houseNum, flatNum));
            }
        }
        System.out.print(Collections.singletonList(persons));
    }

    private static LocalDate generateDOB() {
        DateOfBirthGenerator dob = new DateOfBirthGenerator(LocalDate.of(1919, 1, 1),
                LocalDate.of(2019, 1, 1));
        dobDate = dob.nextDate();
        return dobDate;
    }

    private static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return Period.between(birthDate, currentDate).getYears();
    }
}

