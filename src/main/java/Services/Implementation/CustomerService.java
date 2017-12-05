package Services.Implementation;


import models.entites.jpa.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CustomerService {

    @PersistenceContext (unitName = "itPU")
    private EntityManager em;

    public void addCustomer(Customer c){
        em.persist(c);
    }
}
