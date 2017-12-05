package controller;


import Services.It_deviceService;
import models.entites.jpa.ItDevices.Laptop;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.logging.Logger;

@Named
@RequestScoped
public class LaptopController {

    static final Logger log = Logger.getLogger("LaptioController");
    private Laptop l = new Laptop();

    @EJB(name="It_deviceServiceImpl") private It_deviceService is; //Inject EJB It_deviceService with implementation It_deviceServiceImpl

    public void saveDevice(Laptop l){
        log.info("TEST LOG");

//        try{
//            is.addDevice(l);
//            FacesContext.getCurrentInstance().addMessage("",new FacesMessage("device added"));
//        }catch(Exception e){
//            e.printStackTrace();
//            FacesContext.getCurrentInstance().addMessage("",new FacesMessage("device not added"));
//        }
    }

    public Laptop getL() {
        return l;
    }

    public void setL(Laptop l) {
        this.l = l;
    }
}
