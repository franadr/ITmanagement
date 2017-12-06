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
        em.merge(i);
    }

    @Override
    public void removeDevice(It_device i) {
        em.remove(i);
    }

    @Override
    public List<Laptop> getLaptopByUser(User u) {
        List<Laptop> res;


        try {   Query q =  em
                .createQuery(
                        "SELECT l from Laptop l where l.owner.user_id = :user_id ");

                 q.setParameter("user_id", u.getUser_id());
                res = (List<Laptop>) q.getResultList();

            res.forEach(r -> logger.info(r.getDevice_name()));


            return res;
        }catch (NoResultException e){
            logger.warning(e.getLocalizedMessage());
            return null;
        }

    }

    @Override
    public List<Desktop> getDesktopsByUser(User u) {
        try {

            return em
                    .createQuery(
                            "SELECT d from Desktop d where d.owner.user_id = :user_id ")
                    .setParameter("user_id", u.getUser_id())
                    .getResultList();
        }catch (NoResultException e){
            logger.warning(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public List<Printer> getPrinterByUser(User u) {
        try {

            return em
                    .createQuery(
                            "SELECT p from Printer p where p.owner.user_id = :user_id ")
                    .setParameter("user_id", u.getUser_id())
                    .getResultList();
        }catch (NoResultException e){
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


}
