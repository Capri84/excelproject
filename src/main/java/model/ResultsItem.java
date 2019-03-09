package model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ResultsItem {

    @SerializedName("gender")
    private String gender;

    @SerializedName("dob")
    private Dob dob;

    @SerializedName("name")
    private Name name;

    @SerializedName("location")
    private Location location;

    @SerializedName("nat")
    private String nat;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Dob getDob() {
        return dob;
    }

    public void setDob(Dob dob) {
        this.dob = dob;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    @Override
    public String toString() {
        return "ResultsItem{" +
                "gender='" + gender + '\'' +
                ", dob=" + dob +
                ", name=" + name +
                ", location=" + location +
                ", nat='" + nat + '\'' +
                '}';
    }
}