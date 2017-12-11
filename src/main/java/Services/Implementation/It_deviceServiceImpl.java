package Services.Implementation;


import Services.It_deviceService;
import models.entites.jpa.ItDevices.Desktop;
import models.entites.jpa.ItDevices.It_device;
import models.entites.jpa.ItDevices.Laptop;
import models.entites.jpa.ItDevices.Printer;
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
    public void addUpdateDevice(It_device i) {

        try{
            em.merge(i);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void removeDevice(It_device i) {
        It_device _i = em.merge(i);
        em.remove(_i);


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


}
