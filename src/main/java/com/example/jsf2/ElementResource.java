package com.example.jsf2;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/elements")  // URL du endpoint REST
public class ElementResource {

    @Inject
    private MyBean myBean;  // Injecter le bean JSF

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addElement(Map<String, String> data) {
        Element element = new Element();
        System.out.println(element.getId());
        System.out.println("zzzzzzzzzzzzzzzzzz");
        element.setName(data.get("name"));
        element.setDescription(data.get("description"));

        // Convertir la date de String en Date
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(data.get("date"));
            element.setDate(date);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid date format")
                    .build();
        }

        myBean.addElement(element);  // Ajoutez l'élément au bean
        System.out.println("Element added: " + element);  // Vérifiez si ceci est imprimé

        return Response.ok("Element added successfully").build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)  // Utilisez JSON pour obtenir les éléments
    public List<Element> getAllElements() {
        return myBean.getElements();
    }
}