package Services.Implementation;

import models.entites.jpa.User;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Stateless
public class LoginService {
    //Should look in persistence context;
    @PersistenceContext(unitName = "itPU")
    private
    EntityManager em;

    static Logger logger = Logger.getLogger("LoginService");
    public User getLogin(User u){
        try{
            User user = (User) em
                    .createQuery(
                            "SELECT u from User u where u.username = :name ")
                    .setParameter("name", u.getUsername())
                    .getSingleResult();

            if(BCrypt.checkpw(u.getPassword(),user.getPassword()))
            return user;
            else
                throw  new NoResultException();
        }catch(NoResultException e){
            logger.warning(e.getLocalizedMessage());
            return null;

        }

    }
}
