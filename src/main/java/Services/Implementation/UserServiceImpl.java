package Services.Implementation;

import Services.UserService;
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
    public void addUpdateUser(User u) {

    }

    @Override
    public void removeUser(User u) {

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
}
