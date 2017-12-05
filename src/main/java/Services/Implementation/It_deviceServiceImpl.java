package Services.Implementation;


import Services.It_deviceService;
import models.entites.jpa.ItDevices.It_device;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote(It_deviceService.class)
public class It_deviceServiceImpl implements It_deviceService {

    @PersistenceContext (unitName = "itPU")
    private EntityManager em;

    public void addDevice(It_device i) {
        em.persist(i);
    }
}
