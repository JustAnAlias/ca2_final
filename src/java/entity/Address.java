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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author RL^
 */
@Entity
public class Address implements Serializable{
    @ManyToOne
    private CityInfo cityInfo;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String street, additionalInfo;
    @OneToMany(mappedBy = "address")
    private List<InfoEntity> residents;
    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<InfoEntity> getResidents() {
        return residents;
    }

    public void setResidents(List<InfoEntity> residents) {
        this.residents = residents;
    }

    @Override
    public String toString() {
        return "Address{" + "cityInfo=" + cityInfo + ", id=" + id + ", street=" + street + ", additionalInfo=" + additionalInfo + ", residents=" + residents + '}';
    }

    
}
