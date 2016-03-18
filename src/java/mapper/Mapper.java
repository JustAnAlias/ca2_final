/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapper;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.InfoEntity;
import entity.Person;
import entity.Phone;
import exceptions.AddressNotFoundException;
import exceptions.CompanyNotFoundException;
import exceptions.HobbyNotFoundException;
import exceptions.PersonNotFoundException;
import exceptions.PhoneDoesNotBelongToCompanyException;
import exceptions.PhoneDoesNotBelongToPersonException;
import exceptions.PhoneNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author RL^
 */
public class Mapper {

    private EntityManagerFactory emf;
    private EntityManager em;

    public Mapper() {
        emf = Persistence.createEntityManagerFactory("CA2_FINALPU", null);
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Address addAddress(Address a) {
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
            return a;
        } finally {
            em.close();
        }
    }

    public Address deleteAddress(int id) throws AddressNotFoundException {
        em = getEntityManager();
        try {
            Address a = em.find(Address.class, id);
            if (a == null) {
                throw new AddressNotFoundException();
            }
            em.getTransaction().begin();
            em.remove(a);
            em.getTransaction().commit();
            return a;
        } finally {
            em.close();
        }
    }

    public Address getAddress(int id) throws AddressNotFoundException {
        em = getEntityManager();
        try {
            Address a = em.find(Address.class, id);
            if (a == null) {
                throw new AddressNotFoundException();
            }
            return a;
        } finally {
            em.close();
        }
    }

    public Address editAddress(Address a) throws AddressNotFoundException {
        em = getEntityManager();
        try {
            Address edited = em.find(Address.class, a.getId());
            if (edited == null) {
                throw new AddressNotFoundException();
            }
            edited.setStreet(a.getStreet());
            edited.setAdditionalInfo(a.getAdditionalInfo());
            em.getTransaction().begin();
            em.merge(edited);
            em.getTransaction().commit();
            return edited;
        } finally {
            em.close();
        }
    }

    public CityInfo getCityInfo(int id) {
        em = getEntityManager();
        CityInfo cityInfo = null;
        try {
            cityInfo = em.find(CityInfo.class, "" + id);
        } finally {
            em.close();
        }
        return cityInfo;
    }

    public CityInfo addCityInfo(CityInfo cityInfo) {
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cityInfo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return cityInfo;
    }

    public CityInfo editCityInfo(CityInfo cityInfo) {
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cityInfo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return cityInfo;
    }

    public CityInfo deleteCityInfo(int id) {
        em = getEntityManager();
        CityInfo cityInfo = null;

        try {
            cityInfo = em.find(CityInfo.class, id);
            em.getTransaction().begin();
            em.remove(cityInfo);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return cityInfo;
    }

    public Hobby getHobby(int id) throws HobbyNotFoundException {
        em = getEntityManager();
        Hobby hobby = null;
        try {
            hobby = em.find(Hobby.class, id);
        } finally {
            em.close();
        }
        return hobby;
    }

    public Hobby addHobby(Hobby hobby) {
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return hobby;
    }

    public Hobby editHobby(Hobby hobby) throws HobbyNotFoundException {
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return hobby;
    }

    public Hobby deleteHobby(int id) throws HobbyNotFoundException {
        em = getEntityManager();
        Hobby hobby = null;
        try {
            hobby = em.find(Hobby.class, id);

            em.getTransaction().begin();
            em.remove(hobby);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return hobby;
    }

    public InfoEntity getInfoEntity(int id) {
        em = getEntityManager();
        InfoEntity infoEntity = null;
        try {
            infoEntity = em.find(InfoEntity.class, id);
            Query query = em.createQuery("SELECT p FROM Phone p WHERE p.owner = :owner");
            query.setParameter("owner", infoEntity);
            infoEntity.setPhones(query.getResultList());
        } finally {
            em.close();
        }
        return infoEntity;
    }

    public InfoEntity addInfoEntity(InfoEntity infoEntity) {
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(infoEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return infoEntity;
    }

    public InfoEntity editInfoEntity(InfoEntity infoEntity) {
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(infoEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return infoEntity;
    }

    public InfoEntity deleteInfoEntity(int id) {
        em = getEntityManager();
        InfoEntity infoEntity = null;
        try {
            infoEntity = em.find(InfoEntity.class, id);
            em.getTransaction().begin();
            em.remove(infoEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return infoEntity;
    }

    public List<Company> getCompanies(int numEmployees) {
        em = getEntityManager();
        List<Company> companies = new ArrayList<Company>();
        try {
            Query query = em.createQuery("SELECT c FROM Company c WHERE c.numEmployees > :num");
            query.setParameter("num", numEmployees);
            companies = query.getResultList();
        } finally {
            em.close();
        }
        return companies;
    }

    public Company getCompanyByPhone(String phone) throws PhoneDoesNotBelongToCompanyException, CompanyNotFoundException {
        em = getEntityManager();
        Company company = null;
        InfoEntity infoEntity = null;
        try {
            Query ownerQuery = em.createQuery("SELECT p.owner FROM Phone p WHERE p.number = :number");
            ownerQuery.setParameter("number", phone);
            infoEntity = (InfoEntity) ownerQuery.getSingleResult();
            if (infoEntity == null) {
                throw new CompanyNotFoundException();
            }
            if (infoEntity.getClass().equals(Company.class)) {
                company = (Company) infoEntity;
                Query companyQuery = em.createQuery("SELECT p FROM Phone p WHERE p.owner = :owner");
                companyQuery.setParameter("owner", company);
                company.setPhones(companyQuery.getResultList());
            } else {
                throw new PhoneDoesNotBelongToCompanyException();
            }
        } finally {
            em.close();
        }
        return company;
    }

    public Person getPersonByPhone(String phone) throws PhoneDoesNotBelongToPersonException, PersonNotFoundException {
        em = getEntityManager();
        Person person = null;
        try {
            Query query = em.createQuery("SELECT p.owner FROM Phone p WHERE p.number = :num");
            query.setParameter("num", phone);
            InfoEntity result = (InfoEntity) query.getSingleResult();
            if (result == null) {
                throw new PersonNotFoundException();
            }
            if (result.getClass().equals(Person.class)) {
                person = (Person) result;
                Query phonesQuery = em.createQuery("SELECT p FROM Phone p WHERE p.owner = :owner");
                phonesQuery.setParameter("owner", person);
                person.setPhones(phonesQuery.getResultList());
            } else {
                throw new PhoneDoesNotBelongToPersonException();
            }
        } finally {
            em.close();
        }
        return person;
    }

    public Person getPerson(int id) throws PersonNotFoundException {
        em = getEntityManager();
        InfoEntity ie = null;
        Person person = null;
        try {
            ie = em.find(InfoEntity.class, id);
            if (ie == null) {
                throw new PersonNotFoundException();
            }
            if (ie.getClass().equals(Person.class)) {
                person = (Person) ie;
                Query query = em.createQuery("SELECT p FROM Phone p WHERE p.owner = :owner");
                query.setParameter("owner", person);
                person.setPhones(query.getResultList());
            } else {
                throw new PersonNotFoundException();
            }
        } finally {
            em.close();
        }
        return person;
    }

    public List<Person> getPeopleWithHobby(String hobbyName) throws PersonNotFoundException {
        em = getEntityManager();
        List<Person> person = null;
        try {
            Query query = em.createQuery("SELECT h.id FROM Hobby h WHERE h.name = :name");
            query.setParameter("name", hobbyName);
            InfoEntity result = (InfoEntity) query.getSingleResult();
            Query query1 = em.createQuery("SELECT h.peopleEnjoying_Id FROM infoentity_hobby h Where h.id =:id");
            query1.setParameter("id", result);
            person = query.getResultList();

            if (result == null) {
                throw new PersonNotFoundException();
            }

        } finally {
            em.close();
        }
        return person;

    }

    public int getCountOfPeopleWithHobby(String hobbyName) throws PersonNotFoundException {
        em = getEntityManager();
        List<Person> person = null;
        try {
            Query query = em.createQuery("SELECT h.id FROM Hobby h WHERE h.name = :name");
            query.setParameter("name", hobbyName);
            int result = (int) query.getSingleResult();
            Query query1 = em.createQuery("SELECT h.peopleEnjoying_ID FROM infoentity_hobby h Where h.hobbies_id =:id");
            query1.setParameter("id", result);
            person = query.getResultList();
            if (person == null) {
                throw new PersonNotFoundException();
            }
        } finally {
            em.close();
        }
        return person.size();
    }

    public Person editPerson(Person p) throws PersonNotFoundException {
        em = getEntityManager();
        InfoEntity ie = null;
        try {
            ie = em.find(InfoEntity.class, p.getId());
            if (ie == null) {
                throw new PersonNotFoundException();
            }
            if (ie.getClass().equals(Person.class) == false) {
                throw new PersonNotFoundException();
            }
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return p;
    }

    public Person deletePerson(int id) throws PersonNotFoundException {
        em = getEntityManager();
        InfoEntity infoEntity = null;
        try {
            infoEntity = em.find(InfoEntity.class, id);
            if (infoEntity == null) {
                throw new PersonNotFoundException();
            }
            if (infoEntity.getClass().equals(Person.class) == false) {
                throw new PersonNotFoundException();
            }
            em.getTransaction().begin();
            em.remove(infoEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return (Person) infoEntity;
    }

    public Company getCompany(int id) throws CompanyNotFoundException {
        em = getEntityManager();
        InfoEntity ie = null;
        Company c = null;
        try {
            ie = em.find(InfoEntity.class, id);
            if (ie == null) {
                throw new CompanyNotFoundException();
            }
            if (ie.getClass().equals(Company.class)) {
                c = (Company) ie;
                Query query = em.createQuery("SELECT p FROM Phone p WHERE p.owner = :owner");
                query.setParameter("owner", c);
                c.setPhones(query.getResultList());
            } else {
                throw new CompanyNotFoundException();
            }
        } finally {
            em.close();
        }
        return c;
    }

    public Company editCompany(Company c) throws CompanyNotFoundException {
        em = getEntityManager();
        InfoEntity ie = null;
        try {
            ie = em.find(InfoEntity.class, c.getId());
            if (ie == null) {
                throw new CompanyNotFoundException();
            }
            if (ie.getClass().equals(Company.class) == false) {
                throw new CompanyNotFoundException();
            }
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return c;
    }

    public Company deleteCompany(int id) throws CompanyNotFoundException {
        em = getEntityManager();
        InfoEntity infoEntity = null;
        try {
            infoEntity = em.find(InfoEntity.class, id);
            if (infoEntity == null) {
                throw new CompanyNotFoundException();
            }
            if (infoEntity.getClass().equals(Company.class) == false) {
                throw new CompanyNotFoundException();
            }
            em.getTransaction().begin();
            em.remove(infoEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return (Company) infoEntity;
    }

    public List<Company> getAllCompanies() {
        em = getEntityManager();
        List<Company> companies = new ArrayList<Company>();
        try {
            Query query = em.createQuery("SELECT c FROM Company c");
            companies = query.getResultList();
        } finally {
            em.close();
        }
        return companies;
    }

    public List<Person> getAllPeople() {
        em = getEntityManager();
        List<Person> people = new ArrayList();
        try {
            Query query = em.createQuery("SELECT p FROM Person p");
            people = query.getResultList();
            System.out.println("people arraylist contains " + people.size() + " entities");
        } finally {
            em.close();
        }
        return people;
    }

    public Phone addPhone(Phone p) {
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    public Phone deletePhone(int id) throws PhoneNotFoundException {
        em = getEntityManager();
        try {
            Phone p = em.find(Phone.class, id);
            if (p == null) {
                throw new PhoneNotFoundException();
            }
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }

    public Phone getPhone(int id) throws PhoneNotFoundException {
        em = getEntityManager();
        try {
            Phone p = em.find(Phone.class, id);
            if (p == null) {
                throw new PhoneNotFoundException();
            }
            return p;
        } finally {
            em.close();
        }
    }

    public Phone editPhone(Phone p) throws PhoneNotFoundException {
        em = getEntityManager();
        try {
            if (p == null) {
                throw new PhoneNotFoundException();
            }
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }
    }
    
    public <T> void addEntity(T ie){
        System.out.println("persisting stuff of type: " + ie.getClass().getCanonicalName());
        em = emf.createEntityManager();
        try{
        em.getTransaction().begin();
        em.persist(ie);
        em.getTransaction().commit();
        }
        catch(Exception e){
            // throw some exception
        }
        finally{
            em.close();
        }
        
        
        System.out.println("persisted the entity");
    }
    
    public <T> void editEntity(T ie){
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(ie);
            em.getTransaction().commit();
        }
        catch(Exception e){
            // throw some exception
        }

            finally {
            em.close();
        }
    }
    
    public <T> void deleteEntity(T ie){
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(ie);
            em.getTransaction().commit();
        }
        catch (Exception e){
            // throw some exception?
        }
        finally {
            em.close();
        }
    }

}
