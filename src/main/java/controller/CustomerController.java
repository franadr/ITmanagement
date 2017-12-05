package controller;


import Services.Implementation.CustomerService;
import models.entites.jpa.Customer;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class CustomerController {
    private Customer c = new Customer();

    @EJB private CustomerService cs;

    public void saveCust(Customer c){
        cs.addCustomer(c);
    }

    public Customer getC() {
        return c;
    }

    public void setC(Customer c) {
        this.c = c;
    }
}
