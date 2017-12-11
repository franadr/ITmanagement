package controller.managedBeans;


import Services.It_deviceService;
import models.entites.jpa.ItDevices.*;
import models.entites.jpa.User;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class ItdeviceController implements Serializable {

    private static final long serialVersionUID = 1L;

    static final Logger log = Logger.getLogger("ItDEviceController");

    @ManagedProperty(value = "#{loginController}")
    private
    LoginController loginController;



    private User loggedUser ;
    private Laptop l = new Laptop();
    private Laptop newLaptop = new Laptop();
    private Printer newPrinter = new Printer();
    private List<Laptop> laptopList = new ArrayList<>();
    private List<Printer> printerList = new ArrayList<>();
    private List<Desktop> desktopList = new ArrayList<>();
    private boolean listNotEmpty = false;
    private String itdeviceType = "none";
    private List<Laptop_type> laptopTypes = new ArrayList<>();

    @EJB(name="It_deviceServiceImpl") private It_deviceService is; //Inject EJB It_deviceService with implementation It_deviceServiceImpl


    public boolean isListNotEmpty() {
        log.info("Laptoplist size :"+laptopList.size());
        return !laptopList.isEmpty();
    }



    public void saveDevice(It_device itd){
        try{
            is.addUpdateDevice(itd);
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
        if(loginController.getUser().getUg().getUser_group_name().equals("user"))
            this.laptopList = is.getItdeviceByUser(Laptop.class,loginController.getUser());
        else
            this.laptopList = is.getItdevice(Laptop.class);
        return laptopList;
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

    public List<Printer> getPrinterList() {
        if(loginController.getUser().getUg().getUser_group_name().equals("user"))
            this.printerList = is.getItdeviceByUser(Printer.class,loginController.getUser());
        else
            this.printerList = is.getItdevice(Printer.class);
        return printerList;
    }

    public List<Desktop> getDesktopList() {
        if(loginController.getUser().getUg().getUser_group_name().equals("user"))
            this.desktopList = is.getItdeviceByUser(Laptop.class,loginController.getUser());
        else
            this.desktopList = is.getItdevice(Desktop.class);
        return desktopList;
    }

    public List<Laptop_type> getLaptopTypes() {
        this.laptopTypes = is.getLaptopType();
        return laptopTypes;
    }

    public Laptop getNewLaptop() {
        return newLaptop;
    }

    public void setNewLaptop(Laptop newLaptop) {
        this.newLaptop = newLaptop;
    }

    public Printer getNewPrinter() {
        return newPrinter;
    }

    public void setNewPrinter(Printer newPrinter) {
        this.newPrinter = newPrinter;
    }
}
