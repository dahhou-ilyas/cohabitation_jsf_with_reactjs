package com.example.jsf2;

import com.google.gson.Gson;

import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Named("myBean")
@SessionScoped
public class MyBean implements Serializable {
    private List<Element> elements = new ArrayList<>();





    private Element newElement = new Element();
    private String searchQuery = "";

    private Element selectedElement;



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


    public void setSelectedElementId(Element element) {
        this.selectedElement = element;
    }

    public Element getSelectedElementId() {
        return selectedElement;
    }

    public void redirectToDetailPage(Element element) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            this.selectedElement=element;
            ec.redirect("detail.xhtml?id=" + element.getId()+"&name="+element.getName()+"&description="+element.getDescription()+"&date="+element.getDate());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Element> getFilteredElements() {
        return elements.stream()
                .filter(e -> (e.getName().toLowerCase().contains(searchQuery.toLowerCase()) ||
                        e.getDescription().toLowerCase().contains(searchQuery.toLowerCase())))
                .collect(Collectors.toList());
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public List<Element> getElements() {
        return elements;
    }


    public void addElementFromReact() {
        String jsonElement = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("newElement");
        Gson gson = new Gson();
        Element element = gson.fromJson(jsonElement, Element.class);
        elements.add(element);
        System.out.println("Added element from React: " + element);
        System.out.println("ssssssssssiiiiiizr   "+elements.size());
    }

}