/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author RL^
 */
@Entity
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    private String number, description;
    private String number;
    private String description;

    // One person(owner) can have many phone numbers
    @ManyToOne
//    @JoinTable(name = "ENTITY_PHONE",
//            joinColumns = @JoinColumn(name = "phone_id",
//                    referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "infoentity_id",
//                    referencedColumnName = "id"))

    private InfoEntity owner;
//    @ManyToMany(mappedBy = "phones")
//    private List<InfoEntity> infoEntitys;

    public Phone() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InfoEntity getOwner() {
        return owner;
    }

    public void setOwner(InfoEntity owner) {
        this.owner = owner;
    }
    
    public String toJson(){
        String result = "{\"id\" : \"" + id + "\", \"number\" : \"" + number + "\", \"description\" : \"" + description + "\", \"owner\" : \"" + owner.getId() + "\"}";
        return result;
    }

}
