/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import mapper.Mapper;
import entity.Address;
import entity.CityInfo;
import entity.Person;
import entity.Phone;
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
    //    ie.setEmail("michael@mydomain.com");
        ie.setFirstName("michael");
        ie.setLastName("rulle");
    //    Phone p = new Phone("23982308", "private cell");
    //    p.setOwner(ie);
    //    System.out.println(p.toString());
    //    ie.addPhone(p);
    //    Address a = new Address();
    //    a.setId(ie.getId());
    //    a.setStreet("lærkevej");
    //    ie.setAddress(a);

        m.addEntity(ie);

    }

}