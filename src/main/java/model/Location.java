package model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Location {

    @SerializedName("city")
    private String city;

    @SerializedName("street")
    private String street;

    @SerializedName("postcode")
    private String postcode;

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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return
                "Location{" +
                        "city = '" + city + '\'' +
                        ",street = '" + street + '\'' +
                        ",postcode = '" + postcode + '\'' +
                        "}";
    }
}