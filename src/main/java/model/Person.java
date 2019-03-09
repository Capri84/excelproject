package model;

import java.time.LocalDate;

public class Person {
    private String name;
    private String surname;
    private String patronymic;
    private int age;
    private String gender;
    private LocalDate dateOfBirth;
    private long inn;
    private String zipCode;
    private String country;
    private String region;
    private String city;
    private String street;
    private int houseNum;
    private int flatNum;

    public Person(String name, String surname, String patronymic, int age, String gender, LocalDate dateOfBirth,
                  long inn, String zipCode, String country, String region, String city, String street, int houseNum,
                  int flatNum) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.inn = inn;
        this.zipCode = zipCode;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getInn() {
        return inn;
    }

    public void setInn(long inn) {
        this.inn = inn;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(int houseNum) {
        this.houseNum = houseNum;
    }

    public int getFlatNum() {
        return flatNum;
    }

    public void setFlatNum(int flatNum) {
        this.flatNum = flatNum;
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
                ", zipCode=" + zipCode +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNum=" + houseNum +
                ", flatNum=" + flatNum +
                '}';
    }
}
