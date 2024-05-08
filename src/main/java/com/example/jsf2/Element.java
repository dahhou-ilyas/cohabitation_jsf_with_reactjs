package com.example.jsf2;


import javax.json.bind.annotation.JsonbDateFormat;
import java.util.Date;

public class Element {

    private static int idCounter = 0;
    private int id;
    private String name;
    @JsonbDateFormat("yyyy-MM-dd")
    private Date date;
    private String description;

    public Element() {
        this.id = getNextId(); // Attribuer un nouvel ID à chaque création
    }
    public Element(int id, String name, Date date, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.description = description;
    }

    private static synchronized int getNextId() {
        return ++idCounter;
    }

    public int getId() {
        return id;
    }

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

    @Override
    public String toString() {
        return "Element{id=" + id + ", name='" + name + "', date='" + date + "', description='" + description + "'}";
    }


}
