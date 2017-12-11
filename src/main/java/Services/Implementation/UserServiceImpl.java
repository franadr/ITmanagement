package Services.Implementation;

import Services.UserService;
import models.entites.jpa.ItDevices.It_device;
import models.entites.jpa.User;
import models.entites.jpa.User_group;
import models.entites.jpa.User_group_;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@Remote(UserService.class)
public class UserServiceImpl implements UserService{
    static Logger logger = Logger.getLogger("UserServiceImpl");

    @PersistenceContext(unitName = "itPU")
    private EntityManager em;


    @Override
    public boolean addUpdateUser(User u) {
        try{
            logger.info("User nma of user : "+u.getUsername());
            em.merge(u);
            return true;
        }catch (Exception e){
            logger.warning(e.getMessage());
            return false;
        }

    }

    @Override
    public boolean removeUser(User u) {
        try{
            logger.info("Trying to remove "+u.getUser_id());
            User _u = em.merge(u);


            //This is to implement the ON delete set NULL logic
            em.createQuery("select dev from it_device dev where dev.owner.user_id = :ownerid")
                    .setParameter("ownerid",_u.getUser_id())
                    .getResultList()
                    .forEach( i ->{
                        ((It_device) i ).setOwner(null);
                        em.merge(i);
                    });
            em.remove(_u);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            logger.warning(e.getMessage());
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("select u from User u").getResultList();
    }

    @Override
    public List<User_group> getUserGroups() {
        try {
            Query query = em.createQuery("select u from User_group u");
            List<User_group> ugl = query.getResultList();

            ugl.forEach(ug -> logger.info(ug.getUser_group_name()));

            return query.getResultList();

        }catch (NoResultException e){
            logger.warning(e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public User getSpecificUser(User u) {
        return null;
    }

    @Override
    public User getSpecificUser(String username) {

        try{
            Query query = em.createQuery("select u from User u where u.username = :uname").setParameter("uname",username);

            return (User) query.getSingleResult();
        }catch(Exception e){
            return null;
        }

    }
}
