package model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Name {

    @SerializedName("last")
    private String last;

    @SerializedName("first")
    private String first;

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    @Override
    public String toString() {
        return
                "Name{" +
                        "last = '" + last + '\'' +
                        ",first = '" + first + '\'' +
                        "}";
    }
}