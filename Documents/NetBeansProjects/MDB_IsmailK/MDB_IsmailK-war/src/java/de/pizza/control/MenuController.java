/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pizza.control;

import de.ibb.ejb.MySessionBeanRemote;
import de.pizza.model.Pizza;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author ikoembe
 */
@Named
@ApplicationScoped
public class MenuController implements Serializable{

//    MySessionBeanRemote mySessionBean = lookupMySessionBeanRemote();
    private List<Pizza> menu;
    

    
    /**
     * Method shows all pizza item in index.xhtml page
     * if there is no pizza item in menu arraylist,
     * it calls DaoMenu getListPizza() method and
     * gets all list.
     */
    public MenuController() {
        menu = new ArrayList<>();
    }

     public List<Pizza> getMenu() {
        if (menu.size() <= 0) {
          menu = lookupMySessionBeanRemote().getListPizza();
        }
        return menu;
    }

    public void setMenu(List<Pizza> menu) {
        this.menu = menu;
    }

    private MySessionBeanRemote lookupMySessionBeanRemote() {
        try {
            Context c = new InitialContext();
            return (MySessionBeanRemote) c.lookup("ejb/mySessionBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }




    
}

