package controller.managedBeans;


import Services.It_deviceService;
import models.entites.jpa.ItDevices.Desktop;
import models.entites.jpa.ItDevices.It_device;
import models.entites.jpa.ItDevices.Laptop;
import models.entites.jpa.ItDevices.Printer;
import models.entites.jpa.User;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
public class ItdeviceController {

    static final Logger log = Logger.getLogger("ItDEviceController");

    @ManagedProperty(value = "#{loginController}")
    LoginController loginController;

    private User loggedUser ;
    private Laptop l = new Laptop();
    private List<Laptop> laptopList = new ArrayList<>();
    private List<Printer> printerList = new ArrayList<>();
    private List<Desktop> desktopList = new ArrayList<>();
    private boolean listNotEmpty = false;
    private String itdeviceType = "none";

    @EJB(name="It_deviceServiceImpl") private It_deviceService is; //Inject EJB It_deviceService with implementation It_deviceServiceImpl


    public boolean isListNotEmpty() {
        log.info("Laptoplist size :"+laptopList.size());
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

    public void removedevice(It_device i){
        log.info("Deleting device ");

        try{
            is.removeDevice(i);
            log.info("device : "+i.getDevice_id()+" deleted");
        }catch(Exception e){

            log.warning("Not deleted "+e.getMessage());
        }
    }


    public Laptop getL() {
        return l;
    }

    public void setL(Laptop l) {
        this.l = l;
    }



    public List<Laptop> getLaptopList() {
        this.laptopList = is.getItdeviceByUser(Laptop.class,loginController.getUser());
        return laptopList;
    }


    public void getTheLaptopList(User u){
        List<Laptop> lp = is.getItdeviceByUser(Laptop.class,u);
        laptopList = lp;
    }
    public void getTheLaptopList(){
        List<Laptop> lp = is.getItdevice(Laptop.class);
        laptopList = lp;
    }

    public void getThePrinterList(User u){
        List<Printer> lp = is.getItdeviceByUser(Printer.class,u);
        printerList = lp;
    }
    public void getThePrinterList(){
        List<Printer> lp = is.getItdevice(Printer.class);
        printerList = lp;
    }

    public void getTheDesktopList(User u){
        List<Desktop> lp = is.getItdeviceByUser(Desktop.class,u);
        desktopList = lp;
    }
    public void getTheDesktopList(){
        List<Desktop> lp = is.getItdevice(Desktop.class);
        desktopList = lp;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public String getItdeviceType() {
        return itdeviceType;
    }

    public void setItdeviceType(String itdeviceType) {
        this.itdeviceType = itdeviceType;
    }
}
