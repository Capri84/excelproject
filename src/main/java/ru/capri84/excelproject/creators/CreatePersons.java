package ru.capri84.excelproject.creators;

import ru.capri84.excelproject.generators.*;
import ru.capri84.excelproject.model.Person;
import ru.capri84.excelproject.model.Response;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreatePersons {

    public static Response apiResponse = null;
    private Generatable<Integer> numOfPeopleGenerator = new NumOfPeopleGenerator();
    public int numOfPeople = numOfPeopleGenerator.generate();
    static List<Person> persons = new ArrayList<>();
    private LocalDate dobDate;
    private Generatable<Integer> postCodeGenerator = new PostCodeGenerator();
    private Generatable<Integer> flatNumGenerator = new FlatNumGenerator();
    private Generatable<Integer> houseNumGenerator = new HouseNumGenerator();
    private String name;
    private String gender;
    private String patronymic;
    private int age;
    private LocalDate dateOfBirth;
    private long inn;
    private String country;
    private String postCode;
    private String region;
    private String city;
    private String street;
    private int houseNum;
    private int flatNum;

    public void buildPersonsList() throws IOException {
        if (apiResponse != null) {
            buildPersonsFromResponse();
        } else {
            buildPersonsFromFiles();
        }
    }

    /**
     * This method "builds" each Person using API response and forms a list of these Persons.
     */
    private void buildPersonsFromResponse() throws IOException {
        for (int i = 0; i < numOfPeople; i++) {
            name = capitalizeFirstLetter(apiResponse.getResults().get(i).getName().getFirst());
            String surname = capitalizeFirstLetter(apiResponse.getResults().get(i).getName().getLast());

            gender = null;
            patronymic = null;
            if (apiResponse.getResults().get(i).getGender().equalsIgnoreCase("male")) {
                gender = ResourcesData.MALE_GENDER;
                patronymic = RandomLinesReader.choose(new File(ResourcesData.PATRONYMICS_MALE),
                        ResourcesData.PATRONYMICS_MALE);
            } else if (apiResponse.getResults().get(i).getGender().equalsIgnoreCase("female")) {
                gender = ResourcesData.FEMALE_GENDER;
                patronymic = RandomLinesReader.choose(new File(ResourcesData.PATRONYMICS_FEMALE),
                        ResourcesData.PATRONYMICS_FEMALE);
            }

            age = apiResponse.getResults().get(i).getDob().getAge();

            String dateOfBirthApi = apiResponse.getResults().get(i).getDob().getDate();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            dateOfBirth = LocalDate.parse(dateOfBirthApi.substring(0, 10), dtf);

            inn = InnGenerator.generateINN();

            postCode = apiResponse.getResults().get(i).getLocation().getPostcode();
            if (Integer.parseInt(postCode) < GeneratorLimits.POST_LOWER_LIMIT.getValue()
                    || Integer.parseInt(postCode) > GeneratorLimits.POST_UPPER_LIMIT.getValue()) {
                postCode = String.valueOf(postCodeGenerator.generate());
            }

            country = RandomLinesReader.choose(new File(ResourcesData.COUNTRIES), ResourcesData.COUNTRIES);
            region = RandomLinesReader.choose(new File(ResourcesData.REGIONS), ResourcesData.REGIONS);
            city = capitalizeFirstLetter(apiResponse.getResults().get(i).getLocation().getCity());

            String streetApi = apiResponse.getResults().get(i).getLocation().getStreet();
            Pattern p = Pattern.compile("[a-z]");
            Matcher m = p.matcher(streetApi);
            int position = 0;
            if (m.find()) {
                position = m.start();
            }
            street = streetApi.substring(0, position) + streetApi.substring(position, position + 1).toUpperCase()
                    + streetApi.substring(position + 1);

            houseNum = houseNumGenerator.generate();
            flatNum = flatNumGenerator.generate();

            persons.add(new Person(name, surname, patronymic, age, gender, dateOfBirth, inn, postCode, country,
                    region, city, street, houseNum, flatNum));
        }
    }

    /**
     * This method "builds" each Person using resource's files and forms a list of these Persons.
     */
    private void buildPersonsFromFiles() throws IOException {
        List<String> surnames = new ArrayList<>();
        for (int j = 0; j < numOfPeople; j++) {
            surnames.add(RandomLinesReader.choose(new File(ResourcesData.ALL_SURNAMES), ResourcesData.ALL_SURNAMES));
        }

        for (String surname : surnames) {
            if (ResourcesData.getMaleSurnamesList().contains(surname)) {
                name = RandomLinesReader.choose(new File(ResourcesData.NAMES_MALE), ResourcesData.NAMES_MALE);
                patronymic = RandomLinesReader.choose(new File(ResourcesData.PATRONYMICS_MALE),
                        ResourcesData.PATRONYMICS_MALE);
                gender = ResourcesData.MALE_GENDER;
            } else if (ResourcesData.getFemaleSurnamesList().contains(surname)) {
                name = RandomLinesReader.choose(new File(ResourcesData.NAMES_FEMALE), ResourcesData.NAMES_FEMALE);
                patronymic = RandomLinesReader.choose(new File(ResourcesData.PATRONYMICS_FEMALE),
                        ResourcesData.PATRONYMICS_FEMALE);
                gender = ResourcesData.FEMALE_GENDER;
            } else {
                System.out.println(surname + " Фамилия не найдена в списках");
            }

            age = calculateAge(generateDOB(), LocalDate.now());
            dateOfBirth = dobDate;
            inn = InnGenerator.generateINN();
            postCode = String.valueOf(postCodeGenerator.generate());
            country = RandomLinesReader.choose(new File(ResourcesData.COUNTRIES), ResourcesData.COUNTRIES);
            region = RandomLinesReader.choose(new File(ResourcesData.REGIONS), ResourcesData.REGIONS);
            city = RandomLinesReader.choose(new File(ResourcesData.CITIES), ResourcesData.CITIES);
            street = RandomLinesReader.choose(new File(ResourcesData.STREETS), ResourcesData.STREETS);
            houseNum = houseNumGenerator.generate();
            flatNum = flatNumGenerator.generate();

            persons.add(new Person(name, surname, patronymic, age, gender, dateOfBirth, inn, postCode, country,
                    region, city, street, houseNum, flatNum));
        }
    }

    private LocalDate generateDOB() {
        Generatable<LocalDate> dobGenerator = new DateOfBirthGenerator();
        dobDate = dobGenerator.generate();
        return dobDate;
    }

    private static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        return Period.between(birthDate, currentDate).getYears();
    }

    /**
     * This method capitalizes the first letter of the names, surnames and cities recieved via API.
     */
    private static String capitalizeFirstLetter(String strToCaps) {
        return strToCaps.substring(0, 1).toUpperCase() + strToCaps.substring(1);
    }
}

