package com.example.jsf2;

import com.google.gson.Gson;

import javax.annotation.PostConstruct;
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

import org.primefaces.PrimeFaces;

@Named("myBean")
@SessionScoped
public class MyBean implements Serializable {
    private List<Element> elements = new ArrayList<>();





    private String searchQuery ="";

    private Element selectedElement;


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


    // Méthode pour obtenir les éléments sous forme de JSON
    public void getElementsAsJson() {

        Gson gson = new Gson();
        PrimeFaces.current().ajax().addCallbackParam("elements", gson.toJson(elements));
    }

    public void filterElements() {
        // Filtrer les éléments en fonction de la requête de recherche
        List<Element> filteredElements = elements.stream()
                .filter(e -> e.getName().toLowerCase().contains(searchQuery.toLowerCase()) ||
                        e.getDescription().toLowerCase().contains(searchQuery.toLowerCase()))
                .collect(Collectors.toList());

        // Envoyer les éléments filtrés au composant React
        Gson gson = new Gson();
        String jsonFilteredElements = gson.toJson(filteredElements);
        System.out.println(jsonFilteredElements);
        // Exécuter le script JavaScript pour appeler la fonction handleFilterResult
        PrimeFaces.current().executeScript("handleFilterResult(" + jsonFilteredElements + ")");
    }



    public void dragon() {
        String dragonValue = "saisjaosijas"; // La valeur à retourner
        PrimeFaces.current().ajax().addCallbackParam("dragonValue", dragonValue);
    }


}