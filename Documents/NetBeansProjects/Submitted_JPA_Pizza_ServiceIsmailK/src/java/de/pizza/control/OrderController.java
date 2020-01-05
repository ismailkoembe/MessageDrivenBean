/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.pizza.control;

import de.pizza.dao.DaoCustomer;
import de.pizza.dao.DaoOrder;
import de.pizza.model.Customer;
import de.pizza.model.Ordered;
import de.pizza.model.Pizza;
import de.pizza.model.Selection;
import de.pizza.model.SessionInfo;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ikoembe
 */
@Named
@SessionScoped
public class OrderController implements Serializable {

    private List<Pizza> selectionPizzas;
    private Customer customer;
    private SessionInfo sessionInfo;
    private Locale locale;
    private Date date;
    private double summ;
    private Ordered orders;
    private Selection slc;
    private boolean check;

    @Inject
    private MenuController menu;
    @Inject
    private DaoCustomer daoCustomer;
    @Inject
    private DaoOrder daoOrder;

    public OrderController() {
        selectionPizzas = new ArrayList<>();
        this.customer = new Customer();
        sessionInfo = new SessionInfo();
        locale = new Locale("de");
        this.orders = new Ordered();
        this.slc = new Selection();
        check = false;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public List<Pizza> getSelectionPizzas() {
        return selectionPizzas;
    }

    public void setSelectionPizzas(List<Pizza> selectionPizzas) {
        this.selectionPizzas = selectionPizzas;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public SessionInfo getSessionInfo() {
        return sessionInfo;
    }

    public void setSessionInfo(SessionInfo sessionInfo) {
        this.sessionInfo = sessionInfo;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public Ordered getOrders() {
        return orders;
    }

    public void setOrders(Ordered orders) {
        this.orders = orders;
    }

    public MenuController getMenu() {
        return menu;
    }

    public void setMenu(MenuController menu) {
        this.menu = menu;
    }

    public DaoCustomer getDaoCustomer() {
        return daoCustomer;
    }

    public void setDaoCustomer(DaoCustomer daoCustomer) {
        this.daoCustomer = daoCustomer;
    }

    public DaoOrder getDaoOrder() {
        return daoOrder;
    }

    public void setDaoOrder(DaoOrder daoOrder) {
        this.daoOrder = daoOrder;
    }

    public Selection getSlc() {
        return slc;
    }

    public void setSlc(Selection slc) {
        this.slc = slc;
    }

    //----------------------------------------------my methods
    public String resetSession() {
        orders = new Ordered();
        check = false;
        return "/index.xhtml";
    }

    public String addElements() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        sessionInfo.setIpAdress(request.getRemoteAddr());
        sessionInfo.setSessId(request.getSession().getId());
        for (Pizza p : menu.getMenu()) {
            if (p.getNumber() > 0) {
                selectionPizzas.add(p);
            }
        }
        orders.setSelection(selectionPizzas);
        orders.setCustomer(customer);
        orders.setOrderDate(new Date());
        orders.setSess_Id(sessionInfo.getSessId());
        return "orderConfirm.xhtml";
    }

    public void store() {

//            orders.setSess_Id(callRequest().getSession().getId());
        Date currentDateTime = new Date();
        orders.setOrderDate(currentDateTime);
        check = true;
        daoCustomer.storeCustomer(customer);

    }

    public void addElement(String pType, double price, int number) {
        selectionPizzas.add(new Pizza(pType, price, number));
    }

    public double calcPrice() {
    
        for (Pizza p : this.getSelectionPizzas()) {
            summ += (p.getNumber() * p.getPrice());
        }
        System.out.println("sum ::" + summ);
        return summ;
    }

    public void ipdAndSession(HttpServletRequest req) {
        sessionInfo.setIpAdress(req.getRemoteAddr());
        HttpSession sess = req.getSession();
        sessionInfo.setSessId(sess.getId());
        System.out.println("SessionId:::::::" + sessionInfo.getSessId());
    }

    public void respSenden(HttpServletResponse resp, String url) {
        try {
            resp.sendRedirect(url);
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String saveOrders() {
        daoCustomer.storeCustomer(customer);
        daoOrder.storeOrder(orders);
        daoOrder.storeSelection(slc);
        return "customer.xhtml";
    }

    public void creatpdf() {

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("generate/myrechnung.pdf");

        } catch (IOException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    /**
     * This method is trigerred by ordercormirmation page's button. It sends
     * selection object to daoBean storeorder() method
     *
     * @return browser goes to customer.xhtml page
     */
    public String storeSelection() {

        return "customer.xhtml";
    }

}
