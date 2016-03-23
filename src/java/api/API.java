/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.graph.GraphAdapterBuilder;
import entity.CityInfo;
import entity.Company;
import entity.InfoEntity;
import entity.Person;

import entity.Phone;
import exceptions.CityNotFoundException;
import exceptions.PersonNotFoundException;
import exceptions.PhoneDoesNotBelongToPersonException;
import exceptions.PhoneNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import mapper.Mapper;

/**
 *
 * @author RL^
 */
@Path("search")
public class API {

    @Context
    private UriInfo context;

    private Mapper mp;
    private Gson gsonOut;
    private Gson gsonIn;

    public API() {
        mp = new Mapper();
        gsonIn = new GsonBuilder().setPrettyPrinting().create();
        GsonBuilder gsonBuilder = new GsonBuilder();
        new GraphAdapterBuilder()
                .addType(Person.class)
                .registerOn(gsonBuilder);
        gsonOut = gsonBuilder.setPrettyPrinting().create();
    }

    // Helping methods
    public static JsonObject ziptoJson(entity.CityInfo c) {
        JsonObject jsonA = new JsonObject();
        if (c != null) {
            jsonA.addProperty("zip", c.getZip());
            jsonA.addProperty("city", c.getCity());
        }
        return jsonA;
    }

    // Fuckin api below
    // Person
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createPerson(String personJson) {
//        JsonObject jsonIn = new JsonParser().parse(personJson).getAsJsonObject();
        Person person = gsonIn.fromJson(personJson, Person.class);
        System.out.println("this is the jsonString: " + personJson);
        if (!person.getPhones().isEmpty()) {
            for (Phone p : person.getPhones()) {
                p.setOwner(person);
            }
        }

//        JsonObject jsonPhone = jsonIn.get("phones").getAsJsonObject();
//        System.out.println("this is the phone string: " + jsonPhone);
        mp.addEntity(person);
//        System.out.println(personJson);
//            
//            JsonObject jsonIn = new JsonParser().parse(personJson).getAsJsonObject();
//
//            String firstname = jsonIn.get("firstName").getAsString();
//            String lastname = jsonIn.get("lastName").getAsString();
//            String email = jsonIn.get("email").getAsString();
//            JsonObject jsonPhone = jsonIn.get("phone").getAsJsonObject();
//            String number = jsonPhone.get("phoneNumber").getAsString();
//            String description = jsonPhone.get("phoneDescription").getAsString();
//            
//            Phone phone = new Phone(number, description);
//
////public Person(Integer id, String firstName, String lastName, String email, Phone phone)
//            Person newPerson = new Person(firstname, lastname, email, phone);
//            newPerson.addPhone(phone);
//             
//        mp.addInfoEntity(newPerson);
    }

    @GET
    @Path("person/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonById(@PathParam("id") Integer id) throws PersonNotFoundException, PhoneNotFoundException {
//        return gsonOut.toJson(mp.getPersonById(id));
        return mp.getPersonById(id).toJson();
    }

    @GET
    @Path("personbyphone/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPersonByPhone(@PathParam("number") String number) throws PersonNotFoundException, PhoneDoesNotBelongToPersonException {
        System.out.println("the number parsed from the uri: " + number);
        System.out.println("the number is of class: " + number.getClass().getCanonicalName());
        Person p = mp.getPersonByPhone(number);
        return gsonOut.toJson(p);
    }

    @GET
    @Path("person/all")
    @Produces(MediaType.APPLICATION_JSON)

    public String getAllPersons() {

        List<Person> receivedList = mp.getAllPeople();
        System.out.println("received list contains " + receivedList.size() + " entities");
        JsonArray jA = new JsonArray();
        for (Person per : receivedList) {
            JsonObject jO = new JsonObject();
            jO.addProperty("firstName", per.getFirstName());
            jO.addProperty("lastName", per.getLastName());
            jO.addProperty("id", per.getId());
            jA.add(jO);
        }

        return gsonOut.toJson(jA);

    }

    @GET
    @Produces("application/json")
    @Path("zip/{id}")
    public Response getCity(
            @PathParam("id") String id)
            throws CityNotFoundException {

        CityInfo a = mp.getCityInfo(Integer.parseInt(id));
        String json = ziptoJson(a).toString();
//        String json = mp.getCityInfo(Integer.parseInt(id)).toString();
        return Response.status(Response.Status.OK).entity(json).build();
    }
    
    @GET
    @Path("personbypart/{partial}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getEntitySuggestions(@PathParam("partial") String partial) throws PersonNotFoundException, PhoneDoesNotBelongToPersonException {
        System.out.println("the input parsed from the uri: " + partial);
        System.out.println("the input is of class: " + partial.getClass().getCanonicalName());
        List<InfoEntity> results = mp.getEntitiesByPartial(partial);
        String result = "";
//        for (int i = 0; i < results.size(); i++) {
//            result += gsonOut.toJson(results.get(i));
//        }
//        return result;
        JsonArray jA = new JsonArray();
        
        for (InfoEntity ie : results) {
            JsonObject jO = new JsonObject();
            jO.addProperty("id", ie.getId());
            try{
                Person p = (Person) ie;
                jO.addProperty("name", p.getFirstName() + " " + p.getLastName());
            }
            catch(Exception e){
                Company c = (Company) ie;
                jO.addProperty("name", c.getName());
        }
            if (!jA.contains(jO)){
                jA.add(jO);
            }
            
        }

        return gsonOut.toJson(jA);
        
        
        
//        return gsonOut.toJson(results);
    }
    

    @PUT
    @Path("addperson/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editPerson(String personJson) throws PersonNotFoundException {
        mp.editPerson(gsonIn.fromJson(personJson, Person.class));
        
//        JsonObject jsonIn = new JsonParser().parse(personJson).getAsJsonObject();
//        Person person = new Person();
//        person.setEmail(jsonIn.get("email").getAsString());
//        person.setFirstName(jsonIn.get("firstName").getAsString());
//        person.setLastName(jsonIn.get("lastName").getAsString());
//        List<Phone> pList = new ArrayList();
//        JsonArray jsonPhones = jsonIn.get("phones").getAsJsonArray();
//        if (!person.getPhones().isEmpty()) {
//            for (int i = 0; i < jsonPhones.size(); i++) {
//                Phone p = new Phone();
//                JsonObject j = jsonPhones.get(i).getAsJsonObject();
//                p.setId(j.get("id").getAsInt());
//                p.setDescription(j.get("description").getAsString());
//                p.setNumber(j.get("number").getAsString());
//                p.setOwner(person);
//                person.addPhone(p);
//            }
//        }
//        System.out.println("trying to modify person...");
//        mp.editPerson(person);
    }
}
