package creators;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ResourcesData {

    static final String NAMES_MALE = "./src/main/resources/male/names-male.txt";
    static final String NAMES_FEMALE = "./src/main/resources/female/names-female.txt";
    static final String PATRONYMICS_MALE = "./src/main/resources/male/patronymics-male.txt";
    static final String PATRONYMICS_FEMALE = "./src/main/resources/female/patronymics-female.txt";
    static final String COUNTRIES = "./src/main/resources/common/countries.txt";
    static final String REGIONS = "./src/main/resources//common/regions.txt";
    static final String CITIES = "./src/main/resources/common/cities.txt";
    static final String STREETS = "./src/main/resources/common/streets.txt";
    private static final String SURNAMES_MALE = "./src/main/resources/male/surnames-male.txt";
    private static final String SURNAMES_FEMALE = "./src/main/resources/female/surnames-female.txt";
    static final String MALE_GENDER = "М";
    static final String FEMALE_GENDER = "Ж";
    static final String TABLE_HEADER = "./src/main/resources/table_header.txt";
    static final String ALL_SURNAMES = "./src/main/resources/common/surnames.txt";

    static List<String> getMaleSurnamesList() throws FileNotFoundException {
        List<String> maleSurnames = new ArrayList<>();
        Scanner scanner = new Scanner(new File(SURNAMES_MALE));
        while (scanner.hasNextLine()) {
            maleSurnames.add(scanner.nextLine());
        }
        scanner.close();
        return maleSurnames;
    }

    static List<String> getFemaleSurnamesList() throws FileNotFoundException {
        List<String> femaleSurnames = new ArrayList<>();
        Scanner scanner = new Scanner(new File(SURNAMES_FEMALE));
        while (scanner.hasNextLine()) {
            femaleSurnames.add(scanner.nextLine());
        }
        scanner.close();
        return femaleSurnames;
    }
}