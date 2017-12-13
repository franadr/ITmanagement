package controller.managedBeans;


import Services.It_deviceService;
import models.entites.jpa.ItDevices.*;
import models.entites.jpa.Ticket;
import models.entites.jpa.User;
import net.bootsfaces.utils.FacesMessages;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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

    private Desktop newDesktop = new Desktop();
    private Laptop newLaptop = new Laptop();
    private Printer newPrinter = new Printer();

    private Printer modPrinter = new Printer();
    private Desktop modDesktop = new Desktop();
    private Laptop modLaptop = new Laptop();

    private List<Laptop> laptopList = new ArrayList<>();
    private List<Printer> printerList = new ArrayList<>();
    private List<Desktop> desktopList = new ArrayList<>();
    private boolean listNotEmpty = false;
    private String itdeviceType = "none";
    private List<Laptop_type> laptopTypes = new ArrayList<>();
    private List<It_device> deviceList = new ArrayList<>();
    private List<Ticket> ticketListByDevice = new ArrayList<>();
    private List<Ticket> ticketList = new ArrayList<>();

    private Ticket newTicket = new Ticket();

    private It_device ticketDevice;

    @EJB(name="It_deviceServiceImpl") private It_deviceService is; //Inject EJB It_deviceService with implementation It_deviceServiceImpl


    public boolean isListNotEmpty() {
        log.info("Laptoplist size :"+laptopList.size());
        return !laptopList.isEmpty();
    }



    public void saveDevice(It_device itd){
        try{
            is.addUpdateDevice(itd);
            FacesMessages.info("mainform","","Device added/modified");
        }catch(Exception e){
            e.printStackTrace();
            FacesMessages.info("mainform","","Device not added/modified : "+e.getLocalizedMessage());
        }

        this.newPrinter = new Printer();
        this.newDesktop = new Desktop();
        this.newLaptop = new Laptop();

        this.modDesktop = new Desktop();
        this.modLaptop = new Laptop();
        this.modPrinter = new Printer();
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

    public List<Laptop> getLaptopList() {
        if(loginController.getUser().getUg().getUser_group_name().equals("user"))
            this.laptopList = is.getItdeviceByUser(Laptop.class,loginController.getUser());
        else
            this.laptopList = is.getItdevice(Laptop.class);
        return laptopList;
    }

    public void addTicket(){
        this.newTicket.setRequester(loginController.getUser());
        this.newTicket.setIt_device(ticketDevice);
        is.addUpdateTicket(newTicket);

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
        log.info("device type set "+itdeviceType);
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
            this.desktopList = is.getItdeviceByUser(Desktop.class,loginController.getUser());
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

    public Desktop getNewDesktop() {
        return newDesktop;
    }

    public void setNewDesktop(Desktop newDesktop) {
        this.newDesktop = newDesktop;
    }

    public Printer getModPrinter() {
        return modPrinter;
    }

    public void setModPrinter(Printer modPrinter) {
        this.modPrinter = modPrinter;
    }

    public Desktop getModDesktop() {
        return modDesktop;
    }

    public void setModDesktop(Desktop modDesktop) {
        this.modDesktop = modDesktop;
    }

    public Laptop getModLaptop() {
        return modLaptop;
    }

    public void setModLaptop(Laptop modLaptop) {
        this.modLaptop = modLaptop;
    }

    public List<It_device> getDeviceList() {
        if(loginController.getUser().getUg().getUser_group_name().equals("user"))
            this.deviceList = is.getItdeviceByUser(It_device.class,loginController.getUser());
        else
            this.deviceList = is.getItdevice(It_device.class);
        return deviceList;
    }

    public void setDeviceList(List<It_device> deviceList) {
        this.deviceList = deviceList;
    }

    public It_device getTicketDevice() {
        return ticketDevice;
    }

    public void setTicketDevice(It_device ticketDevice) {
        this.ticketDevice = ticketDevice;
    }

    public Ticket getNewTicket() {
        return newTicket;
    }

    public void setNewTicket(Ticket newTicket) {
        this.newTicket = newTicket;
    }

    public List<Ticket> getTicketListByDevice() {
        return is.getTickets(this.ticketDevice);
    }

    public void setTicketListByDevice(List<Ticket> ticketListByDevice) {
        this.ticketListByDevice = ticketListByDevice;
    }

    public List<Ticket> ticketListByDevice(){
        return is.getTickets(this.ticketDevice);
    }

    public List<Ticket> getTicketList() {
        return is.getTickets();
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
}
