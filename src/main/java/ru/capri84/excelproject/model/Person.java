package ru.capri84.excelproject.model;

import java.time.LocalDate;

public class Person {
    private String name;
    private String surname;
    private String patronymic;
    private int age;
    private String gender;
    private LocalDate dateOfBirth;
    private long inn;
    private String postCode;
    private String country;
    private String region;
    private String city;
    private String street;
    private int houseNum;
    private int flatNum;

    public Person(String name, String surname, String patronymic, int age, String gender, LocalDate dateOfBirth,
                  long inn, String postCode, String country, String region, String city, String street, int houseNum,
                  int flatNum) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.inn = inn;
        this.postCode = postCode;
        this.country = country;
        this.region = region;
        this.city = city;
        this.street = street;
        this.houseNum = houseNum;
        this.flatNum = flatNum;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public long getInn() {
        return inn;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public int getFlatNum() {
        return flatNum;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", inn=" + inn +
                ", postCode=" + postCode +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNum=" + houseNum +
                ", flatNum=" + flatNum +
                '}';
    }
}
