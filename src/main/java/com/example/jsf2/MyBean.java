package com.example.jsf2;

import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Named("myBean")
@SessionScoped
public class MyBean implements Serializable {
    private List<Element> elements = new ArrayList<>();
    private Element newElement = new Element();

    private int selectedElementId;

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

    public Element getElementById(int id) {
        return elements.stream()
                .filter(e -> e.getId() == id) // Filtrer par ID
                .findFirst() // Obtenir le premier élément correspondant
                .orElse(null); // Retourner null si non trouvé
    }


    public void setSelectedElementId(int id) {
        this.selectedElementId = id;
    }

    public int getSelectedElementId() {
        return selectedElementId;
    }

    public void redirectToDetailPage(int elementId) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            this.selectedElementId=elementId;
            ec.redirect("detail.xhtml?elementId=" + elementId);
            // Redirection avec paramètre
        } catch (IOException e) {
            e.printStackTrace(); // Traitez les exceptions
        }
    }
}