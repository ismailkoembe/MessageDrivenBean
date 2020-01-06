/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.mdb;

import de.ibb.ejb.MySessionBeanRemote;
import de.pizza.dao.DaoCustomer;
import de.pizza.dao.DaoMenu;
import de.pizza.dao.DaoOrder;
import de.pizza.model.Customer;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 *
 * @author ismailkoembe
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/myQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MyMessageDrivenBean implements MessageListener {
    
    public MyMessageDrivenBean() {
    }
    
//    @EJB
//    MySessionBeanRemote mySessionBeanRemote;
    public Customer customer;
    
    @Override
    public void onMessage(Message message) {
        
        try {
            ObjectMessage objectMessage = (ObjectMessage) message;
            DaoCustomer daoCustomer = (DaoCustomer) objectMessage.getObject();
            daoCustomer.storeCustomer(customer);
      
            
            
        } catch (JMSException | ClassCastException e) {
            e.printStackTrace();
                     
        } 
        
    }
    
}
