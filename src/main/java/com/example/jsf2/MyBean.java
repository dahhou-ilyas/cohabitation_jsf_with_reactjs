package com.example.jsf2;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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