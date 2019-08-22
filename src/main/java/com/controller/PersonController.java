package com.controller;

import com.model.Person;
import com.service.PersonService;
import com.service.PersonServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/")
public class PersonController {

    private PersonService personService;

    public PersonController() throws ClassNotFoundException {
        this.personService = new PersonServiceImpl();
    }

    @GET
    @Path("/persons/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonById(@PathParam("id") int personId) {
        Person person = personService.getPersonById(personId);
        return Response.ok(person).build();
    }

    @GET
    @Path("/persons")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonAll() {
        ArrayList<Person> personList =personService.getPersonAll();
        return Response.ok(personList).build();
    }

    @POST
    @Path("/persons")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        personService.addPerson(person);
        return Response.status(201).entity(person).build();
    }

    @PUT
    @Path("/persons")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePerson(Person person) {
        personService.updatePerson(person);
        return Response.status(203).entity(person).build();
    }

    @DELETE
    @Path("/persons/{id}")
    public Response deletePerson(@PathParam("id") int personId) {
        personService.deletePerson(personId);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/persons")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePerson(Person person){
        personService.deletePerson(person);
        return Response.noContent().build();
    }
}

