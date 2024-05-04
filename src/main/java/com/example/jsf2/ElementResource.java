package com.example.jsf2;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/elements")  // URL du endpoint REST
public class ElementResource {

    @Inject
    private MyBean myBean;
    @GET
    @Produces(MediaType.TEXT_PLAIN)  // Spécifie le type de contenu retourné
    public String getAllElements() {
        return "szasa";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addElement(Element element) {
        try {
            // Assurez-vous que le format de date correspond au format attendu
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(element.getDate().toString());

            element.setDate(date);
            myBean.getElements().add(element);

            return Response.ok("Element added").build();
        } catch (ParseException e) {
            // En cas d'erreur de format, retourner un message d'erreur approprié
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid date format")
                    .build();
        }
    }
}