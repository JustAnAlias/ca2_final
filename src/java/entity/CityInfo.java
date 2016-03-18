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
import javax.persistence.OneToMany;

/**
 *
 * @author RL^
 */
@Entity
public class CityInfo implements Serializable{
    private static final long serialVersionUID = 1L;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private String zip;
    private String city;
    @OneToMany(mappedBy = "cityInfo")
    private List<Address> cityAddresses;

    public CityInfo() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public List<Address> getCityAddresses() {
        return cityAddresses;
    }

    public void setCityAddresses(List<Address> cityAddresses) {
        this.cityAddresses = cityAddresses;
    }

    @Override
    public String toString() {
        return "CityInfo{" + "zip=" + zip + ", city=" + city + ", cityAddresses=" + cityAddresses + '}';
    }

   
}
