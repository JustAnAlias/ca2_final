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
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import entity.CityInfo;
import entity.Hobby;
import entity.InfoEntity;
import entity.Person;
import entity.Phone;
import exceptions.CityNotFoundException;
import exceptions.PersonNotFoundException;
import exceptions.PhoneDoesNotBelongToPersonException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import static javax.ws.rs.HttpMethod.POST;
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
    private Gson gson;
    
    public API() {
        mp = new Mapper();
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Helping methods
    
    public static JsonObject ziptoJson(entity.CityInfo c){
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
        Person person = gson.fromJson(personJson, Person.class);
        System.out.println("this is the jsonString: " + personJson);
        if(!person.getPhones().isEmpty()){
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("xxxxxxxxxxxxxxxx           " + person.getPhones().get(0) + "           xxxxxxxx");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }
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
    public String getPersonById(@PathParam("id") Integer id) throws PersonNotFoundException
    {
        return gson.toJson(mp.getPerson(id));
    }
    
    @GET
    @Path("person/complete")
    @Produces(MediaType.APPLICATION_JSON)

    public String getAllPersons() {

        List<Person> receivedList = mp.getAllPeople();
        System.out.println("received list contains " + receivedList.size() + " entities");
        JsonArray jA = new JsonArray();
        for (Person per : receivedList) {
            JsonObject jO = new JsonObject();
            jO.addProperty("firstName", per.getFirstName());
            jO.addProperty("lastName", per.getLastName());
            jA.add(jO);
        }

        return gson.toJson(jA);

    }
 
    
    @GET
    @Produces("application/json")
    @Path("zip/{id}")
    public Response getCity(
            @PathParam("id") String id) 
            throws CityNotFoundException{

        
        CityInfo a = mp.getCityInfo(Integer.parseInt(id));
        String json = ziptoJson(a).toString();
//        String json = mp.getCityInfo(Integer.parseInt(id)).toString();
        return Response.status(Response.Status.OK).entity(json).build();
    }
    
    @PUT
    @Path("person/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editPerson(String personJson) {
        Person person = gson.fromJson(personJson, Person.class);
        if(!person.getPhones().isEmpty()){
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("xxxxxxxxxxxxxxxx           " + person.getPhones().get(0) + "           xxxxxxxx");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }
        mp.editEntity(mp);
    }
}