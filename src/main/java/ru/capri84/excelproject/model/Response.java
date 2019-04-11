package ru.capri84.excelproject.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class Response {

    @SerializedName("results")
    private List<ResultsItem> results;

    public List<ResultsItem> getResults() {
        return results;
    }

    public void setResults(List<ResultsItem> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return
                "Response{" +
                        "results = '" + results + '\'' +
                        "}";
    }
}