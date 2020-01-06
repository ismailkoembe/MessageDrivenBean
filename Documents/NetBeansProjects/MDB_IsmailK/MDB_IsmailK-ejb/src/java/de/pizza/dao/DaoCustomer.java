/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pizza.dao;

import de.pizza.model.Customer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

/**
 *
 * @author ikoembe
 */

@Stateless
public class DaoCustomer implements Serializable{
    
    @PersistenceUnit (unitName = "IKoembeEJB-ejb")
    private EntityManagerFactory emf;
    

    public int storeCustomer(Customer customer) {
        EntityManager em = emf.createEntityManager();
        em.persist(customer);
        em.flush();
        return customer.getCustId();
    }

    
    public List<Customer> getList() {

        List<Customer> customers = new ArrayList();
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Kunde.SelectAll");
        customers = q.getResultList();
        return customers;
    }
    
}
