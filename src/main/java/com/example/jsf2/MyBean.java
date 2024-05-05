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

    public void addElement(Element element) {  // Accepter un élément en paramètre
        elements.add(element);  // Ajouter l'élément à la liste
        System.out.println("Added element: " + element);  // Afficher un message pour débogage
    }

    public int getCount() {
        return elements.size();
    }
}