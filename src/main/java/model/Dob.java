package model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Dob {

    @SerializedName("date")
    private String date;

    @SerializedName("age")
    private int age;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return
                "Dob{" +
                        "date = '" + date + '\'' +
                        ",age = '" + age + '\'' +
                        "}";
    }
}