/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.ibb.ejb;

import de.pizza.model.Customer;
import de.pizza.model.Ordered;
import de.pizza.model.Pizza;
import de.pizza.model.Selection;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author ikoembe
 */
@Remote

public interface MySessionBeanRemote {
//    public void storeOrder(Ordered o);
//    public void storeMenu(List<Pizza> pizzas);
//    public int storeCustomer(Customer customer);
//    public int storeSelection(Selection slc);
    public List<Pizza> getListPizza();
    public List<Pizza> fillMenu();

}
