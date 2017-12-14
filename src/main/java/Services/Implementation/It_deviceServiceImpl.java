package Services.Implementation;


import Services.It_deviceService;
import models.entites.jpa.ItDevices.*;
import models.entites.jpa.Ticket;
import models.entites.jpa.User;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@Remote(It_deviceService.class)
public class It_deviceServiceImpl implements It_deviceService {



    static Logger logger = Logger.getLogger("It_deviceServiceImpl");
    @PersistenceContext (unitName = "itPU")
    private EntityManager em;


    @Override
    public boolean addUpdateDevice(It_device i) {

        try{
            em.merge(i);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void removeDevice(It_device i) {
        It_device _i = em.merge(i);
        em.remove(_i);


    }

    @Override
    public void addLaptopType(Laptop_type lt) {
        em.merge(lt);
    }

    @Override
    public boolean addUpdateTicket(Ticket t) {
        try{
            em.merge(t);
            return true;
        }catch(Exception e){
            logger.warning(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public Laptop_type getALaptopType(String typeName) {
        try{
            return (Laptop_type)em.createQuery("select t from Laptop_type t where t.type_name = :typeName").setParameter("typeName",typeName).getSingleResult();

        }catch(Exception e){
            logger.warning(e.getLocalizedMessage());
            return null;
        }
    }


    @Override
    public <T extends It_device> List getItdeviceByUser(Class<T> entityClass, User u){
        try {
            Query query = em.createQuery("Select d from "+entityClass.getName()+" d where d.owner.user_id = :user_id")
                    .setParameter("user_id",u.getUser_id());
            return query.getResultList();

        }catch (NoResultException e){
            logger.warning(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public <T extends It_device> List getItdevice(Class<T> entityClass) {
        try {

            Query query = em.createQuery("Select d from "+entityClass.getName()+" d ");
            return query.getResultList();

        }catch (NoResultException e){
            logger.warning(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public List getLaptopType() {
        try{
            return em.createQuery("select t from Laptop_type t").getResultList();
        }catch (Exception e){
            logger.warning(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public List getTickets(It_device i) {
        try{
            return em.createQuery("select t from ticket2 t where t.it_device = :itdevice")
                    .setParameter("itdevice",i)
                    .getResultList();
        }catch (Exception e){
            logger.warning(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public List getTickets() {
        try{
            return em.createQuery("select t from ticket2 t ")
                    .getResultList();
        }catch (Exception e){
            logger.warning(e.getLocalizedMessage());
            return null;
        }

    }


}
