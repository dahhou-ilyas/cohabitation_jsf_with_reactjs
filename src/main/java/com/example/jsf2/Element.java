package com.example.jsf2;


import javax.json.bind.annotation.JsonbDateFormat;
import java.util.Date;

public class Element {
    private String name;
    @JsonbDateFormat("yyyy-MM-dd")
    private Date date;
    private String description;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
