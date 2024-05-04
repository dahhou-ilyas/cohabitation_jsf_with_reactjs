package com.example.jsf2;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Named("myBean")
@SessionScoped
public class MyBean implements Serializable {
    private List<Element> elements = new ArrayList<>();
    private Element newElement = new Element();

    public List<Element> getElements() {
        return elements;
    }

    public Element getNewElement() {
        return newElement;
    }

    public void addElement() {
        elements.add(newElement);
        newElement = new Element();
        System.out.println(newElement);// Réinitialiser le formulaire
    }

    public int getCount() {
        return elements.size();
    }
}