package Services;
/**
 * This file holds the EJB responsible for setup on startup i.e. db_init
 *
 *
 * Adriano UNI.lu 2017 011109344A
 */

import models.entites.jpa.ItDevices.Desktop;
import models.entites.jpa.ItDevices.Laptop;
import models.entites.jpa.ItDevices.Laptop_type;
import models.entites.jpa.ItDevices.Printer;
import models.entites.jpa.Ticket;
import models.entites.jpa.User;
import models.entites.jpa.User_group;
import org.mindrot.jbcrypt.BCrypt;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Singleton
@Startup
public class SetupBean {
    static Logger logger = Logger.getLogger("SetupBean");

    @PersistenceContext(unitName = "itPU")
    private EntityManager em;
    //Init method that allow to populate DB with some values

    @PostConstruct
    public void init(){
        logger.info("--------Inserting predefined users,user groups, device laptop types ...");



        //User groups init
        User_group ug1 = em.merge(new User_group(1L,"admin","administrators of the system"));
        User_group ug2 = em.merge(new User_group(2L,"itsupport","it support staff"));
        User_group ug3 = em.merge(new User_group(3L,"user","normal user"));
        logger.info("Groups inserted");

        //Users init
        User u1 = em.merge(new User("admin",BCrypt.hashpw("adminadmin",BCrypt.gensalt()),ug1));
        User u2 = em.merge(new User("itsupport",BCrypt.hashpw("testme",BCrypt.gensalt()),ug2));
        User u3 = em.merge(new User("user",BCrypt.hashpw("useruser",BCrypt.gensalt()),ug3));
        logger.info("Users inserted");

        //Laptop types init
        Laptop_type lt1 = em.merge(new Laptop_type("laptop","regular laptop"));
        Laptop_type lt2 = em.merge(new Laptop_type("tablet","a big smartphone"));
        Laptop_type lt3 = em.merge(new Laptop_type("smartphone","a small tablet"));
        logger.info("Laptop types inserted");


        //Device init
        Laptop l1 = em.merge(new Laptop(u3,"5E:FF:56:A2:AF:15","Macbookpro",2400.0,4,8,512,"Apple Co.",13,lt1));
        Laptop l2 = em.merge(new Laptop(u3,"CA:FF:29:AF:89:73","Galaxy tab S3",2400.0,4,8,512,"Samsung Ltd.",9,lt2));
        Desktop d1 = em.merge(new Desktop(u3,"5E:FF:56:A2:AF:15","Dell HP1",3400.0,4,16,1024,"Dell computers"));
        Printer p1 = em.merge(new Printer(u3,"5E:FF:56:A2:AF:15","Samsung MF2070W",false,"first floor"));
        logger.info("Devices inserted");


        //Issue init

        Ticket t1 = em.merge(new Ticket("Some strips are persistent on the screen","Mother board replaced",u3,l2,u2));
        Ticket t2 = em.merge(new Ticket("Update to MacOS 10.4.2.1 is not available","Formatting and SSD replaced, update done",u3,l1,u1));
        logger.info("Sample tickets inserted");


        logger.info("--------Database insertion finished");

//        Thread t = new Thread(() -> {
//            int i = 0;
//            while(true){
//                logger.info("Counter : "+i++);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        });
//        t.start();
    }


}
