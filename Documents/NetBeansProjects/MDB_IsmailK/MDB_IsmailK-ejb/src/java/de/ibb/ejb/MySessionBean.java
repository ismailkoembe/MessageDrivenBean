/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.ibb.ejb;

import de.pizza.dao.DaoCustomer;
import de.pizza.dao.DaoMenu;
import de.pizza.dao.DaoOrder;
import de.pizza.model.Customer;
import de.pizza.model.Ordered;
import de.pizza.model.Pizza;
import de.pizza.model.Selection;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author ikoembe
 */
@Stateless(mappedName = "ejb/mySessionBean")
@Remote
public class MySessionBean implements MySessionBeanRemote {
   @EJB
   DaoOrder daoOrder;
   
   @EJB
   DaoMenu daoMenu;
   
   @EJB
   DaoCustomer daoCustomer;
   
   
   

   
//    @Override
//    public void storeOrder(Ordered o) {
//       daoOrder.storeOrder(o); 
//    }

    @Override
    public List<Pizza> getListPizza() {
        return daoMenu.getListPizza();
    }

    @Override
    public List<Pizza> fillMenu() {
        return daoMenu.getListPizza();
    }

//    @Override
//    public void storeMenu(List<Pizza> pizzas) {
//       daoMenu.storeMenu(pizzas);
//    }

//    @Override
//    public int storeCustomer(Customer customer) {
//        return daoCustomer.storeCustomer(customer);
//    }

   /* @Override
    public String storeOrder() {
        return daoOrder.storeOrder(o);
    }*/

//    @Override
//    public int storeSelection(Selection slc) {
//        return daoOrder.storeSelection(slc);
//    }

   


      

    
    
    
}
