/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import mapper.Mapper;
import entity.Address;
import entity.CityInfo;
import entity.Person;
import entity.Phone;
import exceptions.PersonNotFoundException;
import exceptions.PhoneDoesNotBelongToPersonException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Persistence;

/**
 *
 * @author Michael
 */
public class Tester {

    static Mapper m;

    public static void main(String[] args) {
        m = new Mapper();
        Persistence.generateSchema("CA2_FINALPU", null);
     //   addCityInfo();
     //   editCityInfo();
        addPerson();
        Person p = findPersonByPhoneNumber();
        
        JsonObject jo = new JsonObject();
        jo.addProperty("firstName", p.getFirstName());
        jo.addProperty("lastName", p.getLastName());
        jo.addProperty("email", p.getEmail());
        System.out.println("the json-string: " + p.toJson());

    }
    
    public static Person findPersonByPhoneNumber(){
        Person p = null;
        try {
            String phone = "23982308";
            p = m.getPersonByPhone(phone);
            System.out.println(p.getEmail() +""+ p.getPhones().get(0).getNumber());
        } catch (PhoneDoesNotBelongToPersonException ex) {
            System.out.println("phonenumber does not belong to a person");
        } catch (PersonNotFoundException ex) {
            System.out.println("no such person found");
        }
        return p;
    }

    public static void editCityInfo() {
        CityInfo ci = new CityInfo();
        ci.setCity("københavn");
        ci.setZip("1050");
        m.editEntity(ci);
    }

    public static void addCityInfo() {
        CityInfo ci = new CityInfo();
        ci.setCity("københavn k");
        ci.setZip("1050");
        CityInfo ti = new CityInfo();
        ti.setCity("frederikssund");
        ti.setZip("3600");
        m.addEntity(ti);
        m.addEntity(ci);

    }

    public static void addPerson() {
        Person ie = new Person();
    //    ie.setId(2);
        ie.setEmail("michael@mydomain.com");
        ie.setFirstName("michael");
        ie.setLastName("rulle");
        Phone p = new Phone();
        p.setNumber("23982308");
        p.setDescription("mobile");
        p.setOwner(ie);
        ie.addPhone(p);
    //    Address a = new Address();
    //    a.setId(ie.getId());
    //    a.setStreet("lærkevej");
    //    ie.setAddress(a);

        m.addEntity(ie);

    }

}