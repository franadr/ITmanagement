package controller;


import Services.It_deviceService;
import models.entites.jpa.ItDevices.Laptop;
import models.entites.jpa.User;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class LaptopController {

    static final Logger log = Logger.getLogger("LaptopController");
    private Laptop l = new Laptop();
    private List<Laptop> laptopList = new ArrayList<>();
    private boolean listNotEmpty = false;
    @EJB(name="It_deviceServiceImpl") private It_deviceService is; //Inject EJB It_deviceService with implementation It_deviceServiceImpl


    public boolean isListNotEmpty() {
        log.info("LaptopList size"+laptopList.size());
        return !laptopList.isEmpty();
    }



    public void saveDevice(User user){
        log.info("Saving Laptop");
            this.l.setOwner(user);
        try{
            is.addUpdateDevice(this.l);
            FacesContext.getCurrentInstance().addMessage("",new FacesMessage("device added"));
        }catch(Exception e){
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage("",new FacesMessage("device not added"));
        }
    }

    public Laptop getL() {
        return l;
    }

    public void setL(Laptop l) {
        this.l = l;
    }

    public void getTheLaptopList(User u){
        log.info("Getting laptop list");
        List<Laptop> lp = is.getLaptopByUser(u);
        log.info("is.getLaptopByUser(u).size : "+lp.size());
        this.laptopList = lp;
        this.laptopList.addAll(lp);
    }

    public List<Laptop> getLaptopList() {
        return laptopList;
    }

//    public void setLaptopList(List<Laptop> laptopList) {
//        this.laptopList = laptopList;
//    }
}
