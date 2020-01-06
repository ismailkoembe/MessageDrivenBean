package de.pizza.dao;

import de.pizza.model.Pizza;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.Transactional;


@Stateless
public class DaoMenu implements Serializable {

    @PersistenceUnit (unitName = "IKoembeEJB-ejb")
    private EntityManagerFactory emf;

    public List<Pizza> getListPizza() {

        List<Pizza> listPizzas = new ArrayList<>();
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT p FROM Pizza p");
        listPizzas = q.getResultList();
        if(listPizzas.isEmpty()){
            listPizzas = fillMenu();

        }
        return listPizzas;
    }

    /**
     * If there is no Pizza item, this method fills all pizza menu
     *
     * @return
     */
    public List<Pizza> fillMenu() {
        List<Pizza> spareListPizzas = new ArrayList<>();
        spareListPizzas.add(new Pizza(1, "Pizza Vegan", 4.0));
        spareListPizzas.add(new Pizza(2, "Pizza Margarita", 3.0));
        spareListPizzas.add(new Pizza(3, "Pizza Special", 2.5));
        spareListPizzas.add(new Pizza(4, "Pizza Salami", 6.4));
        spareListPizzas.add(new Pizza(5, "Pizza Hawai", 5.0));
        spareListPizzas.add(new Pizza(6, "Pizza Mediterrian", 4.2));
        spareListPizzas.add(new Pizza(7, "Spagetti", 5.8));
        storeMenu(spareListPizzas);
        return spareListPizzas;
    }

    @Transactional
    public void storeMenu(List<Pizza> pizzas) {
        EntityManager em = emf.createEntityManager();
        for (Pizza pizza : pizzas) {
            em.persist(pizza);
        }
        em.flush();
    }

   
}
