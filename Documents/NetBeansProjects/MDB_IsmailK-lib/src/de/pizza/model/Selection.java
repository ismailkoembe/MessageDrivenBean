/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pizza.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ikoembe
 */
@Entity
public class Selection implements Serializable {

    @ManyToOne
    private Ordered ordered;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int selectionId;
    private int pizzaId;
    private int customerId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Selection() {
    }

    public Selection(Ordered ordered, int selectionId, int pizzaId, int customerId, Date date) {
        this.ordered = ordered;
        this.selectionId = selectionId;
        this.pizzaId = pizzaId;
        this.customerId = customerId;
        this.date = date;
    }

    public Ordered getOrdered() {
        return ordered;
    }

    public void setOrdered(Ordered ordered) {
        this.ordered = ordered;
    }

    public int getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(int selectionId) {
        this.selectionId = selectionId;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Selection{" + "ordered=" + ordered + ", selectionId=" + selectionId + ", pizzaId=" + pizzaId + ", customerId=" + customerId + ", date=" + date + '}';
    }

    
    
    
    
}
