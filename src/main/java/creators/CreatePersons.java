package creators;

import generators.*;
import model.Person;
import model.Response;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static creators.CreatePDF.choose;

public class CreatePersons {

    public static List<Person> persons = new ArrayList<>();
    public static int numOfPeople = NumOfPeopleGenerator.generateRandomInt();
    public static Response response1;

    public static void buildPersonsList() throws IOException {
        for (int i = 0; i < numOfPeople; i++) {
            String nameApi = response1.getResults().get(i).getName().getFirst();
            String name = nameApi.substring(0, 1).toUpperCase() + nameApi.substring(1);
            String surnameApi = response1.getResults().get(i).getName().getLast();
            String surname = surnameApi.substring(0, 1).toUpperCase() + surnameApi.substring(1);

            String gender = null;
            String patronymic = null;
            if (response1.getResults().get(i).getGender().equalsIgnoreCase("male")) {
                gender = ResourcesData.MALE_GENDER;
                patronymic = choose(new File(ResourcesData.PATRONYMICS_MALE), ResourcesData.PATRONYMICS_MALE);
            } else if (response1.getResults().get(i).getGender().equalsIgnoreCase("female")) {
                gender = ResourcesData.FEMALE_GENDER;
                patronymic = choose(new File(ResourcesData.PATRONYMICS_FEMALE), ResourcesData.PATRONYMICS_FEMALE);
            }
            int age = response1.getResults().get(i).getDob().getAge();

            String dateOfBirthApi = response1.getResults().get(i).getDob().getDate();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateOfBirth = LocalDate.parse(dateOfBirthApi.substring(0, 10), dtf);

            long inn = InnGenerator.makeINN();

            String zipCode = response1.getResults().get(i).getLocation().getPostcode();
            if (Integer.parseInt(zipCode) < ZipGenerator.ZIP_LOWER_LIMIT || Integer.parseInt(zipCode) > ZipGenerator.ZIP_UPPER_LIMIT) {
                zipCode = String.valueOf(ZipGenerator.generateRandomInt());
            }

            String country = choose(new File(ResourcesData.COUNTRIES), ResourcesData.COUNTRIES);
            String region = choose(new File(ResourcesData.REGIONS), ResourcesData.REGIONS);
            String cityApi = response1.getResults().get(i).getLocation().getCity();
            String city = cityApi.substring(0, 1).toUpperCase() + cityApi.substring(1);

            String streetApi = response1.getResults().get(i).getLocation().getStreet();
            Pattern p = Pattern.compile("[a-z]");
            Matcher m = p.matcher(streetApi);
            int position = 0;
            if (m.find()) {
                position = m.start();
            }
            String street = streetApi.substring(0, position) + streetApi.substring(position, position + 1).toUpperCase() + streetApi.substring(position + 1);

            int houseNum = HouseNumGenerator.generateRandomHouse();
            int flatNum = FlatNumGenerator.generateRandomFlatNum();
            Person person = new Person(name, surname, patronymic, age, gender, dateOfBirth, inn, zipCode, country, region, city,
                    street, houseNum, flatNum);
            persons.add(person);
        }
        System.out.print(Arrays.asList(persons));
    }
}
