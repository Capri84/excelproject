package ru.capri84.excelproject.creators;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class ResourcesData {

    static final String NAMES_MALE = Objects.requireNonNull(ResourcesData.class.getClassLoader().
            getResource("male/names_male.txt")).getPath();
    static final String NAMES_FEMALE = Objects.requireNonNull(ResourcesData.class.getClassLoader().
            getResource("female/names_female.txt")).getPath();
    static final String PATRONYMICS_MALE = Objects.requireNonNull(ResourcesData.class.getClassLoader().
            getResource("male/patronymics_male.txt")).getPath();
    static final String PATRONYMICS_FEMALE = Objects.requireNonNull(ResourcesData.class.getClassLoader().
            getResource("female/patronymics_female.txt")).getPath();
    static final String COUNTRIES = Objects.requireNonNull(ResourcesData.class.getClassLoader().
            getResource("common/countries.txt")).getPath();
    static final String REGIONS = Objects.requireNonNull(ResourcesData.class.getClassLoader().
            getResource("common/regions.txt")).getPath();
    static final String CITIES = Objects.requireNonNull(ResourcesData.class.getClassLoader().
            getResource("common/cities.txt")).getPath();
    static final String STREETS = Objects.requireNonNull(ResourcesData.class.getClassLoader().
            getResource("common/streets.txt")).getPath();
    static final String TABLE_HEADER = Objects.requireNonNull(ResourcesData.class.getClassLoader().
            getResource("table_header.txt")).getPath();
    static final String ALL_SURNAMES = Objects.requireNonNull(ResourcesData.class.getClassLoader().
            getResource("common/surnames.txt")).getPath();
    static final String MALE_GENDER = "М";
    static final String FEMALE_GENDER = "Ж";
    private static final String SURNAMES_MALE = Objects.requireNonNull(ResourcesData.class.getClassLoader().
            getResource("male/surnames_male.txt")).getPath();
    private static final String SURNAMES_FEMALE = Objects.requireNonNull(ResourcesData.class.getClassLoader().
            getResource("female/surnames_female.txt")).getPath();



    static List<String> getMaleSurnamesList() {
        List<String> maleSurnames = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(SURNAMES_MALE))) {
            while (scanner.hasNextLine()) {
                maleSurnames.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return maleSurnames;
    }

    static List<String> getFemaleSurnamesList() {
        List<String> femaleSurnames = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(SURNAMES_FEMALE))) {
            while (scanner.hasNextLine()) {
                femaleSurnames.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return femaleSurnames;
    }
}