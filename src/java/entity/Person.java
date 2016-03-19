/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

/**
 *
 * @author RL^
 */
@Entity
public class Person extends InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    private String firstName;
    private String lastName;

    // People can have multiple hobbies, and a hobby can be enjoyed by many people
//    @ElementCollection
    @ManyToMany()
    @JoinColumn
    private List<Hobby> hobbies;

    public Person() {
        super.phones = new ArrayList();
    }

    public Person(String firstName, String lastName) {
        super.phones = new ArrayList();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, String email, Phone phone) {
        super.phones = new ArrayList();
        this.firstName = firstName;
        this.lastName = lastName;
        super.setEmail(email);
        phones.add(phone);
    }

    public Person(Integer id, String firstName, String lastName, List<Hobby> hobbies) {
        super(id);
        //  super.phones = new ArrayList();

        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + ", firstName=" + firstName + ", lastName=" + lastName + ", hobbies=" + hobbies + '}';
    }

    public String toJson() {
        String result = "{\"id\" : \"" + super.getId() + "\", \"firstName\" : \"" + firstName + "\", \"lastName\" : \"" + lastName + "\"";
        if (!phones.isEmpty()) {
            result += ", \"phones\" : [";
            result += phones.get(0).toJson();
            if (phones.size() > 1) {
                for (int i = 1; i < phones.size(); i++) {
                    result += ", " + phones.get(i).toJson();
                }
            }
            result += "]";
        }
        result += "}";
        return result;
    }
}