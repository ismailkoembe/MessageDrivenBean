package de.pizza.dao;


import de.pizza.model.Ordered;
import de.pizza.model.Selection;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;


@Stateless
public class DaoOrder implements Serializable {

    @PersistenceUnit(unitName = "IKoembeEJB-ejb")
    private EntityManagerFactory emf;
    

    public void storeOrder(Ordered o) {

        EntityManager em = emf.createEntityManager();
        em.persist(o);
        em.flush();

    }

    public List<Ordered> getOrdersList() {

        List<Ordered> orders = new ArrayList();
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Ordered.SelectAll");
        orders = q.getResultList();
        return orders;
    }
     public int storeSelection(Selection slc) {

        EntityManager em = emf.createEntityManager();
        em.persist(slc);
        em.flush();
        return slc.getSelectionId();
    }

}
