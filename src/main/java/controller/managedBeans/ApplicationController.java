package controller.managedBeans;


import Services.It_deviceService;
import Services.UserService;
import models.entites.jpa.ItDevices.Desktop;
import models.entites.jpa.ItDevices.Laptop;
import models.entites.jpa.ItDevices.Laptop_type;
import models.entites.jpa.ItDevices.Printer;
import models.entites.jpa.User;
import models.entites.jpa.User_group;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ManagedBean(eager = true)
@ApplicationScoped
public class ApplicationController {
    private Set<User> connectedUser = new HashSet<>();

    @EJB(name = "UserServiceImpl")
    UserService userService;

    @EJB(name="It_deviceServiceImpl")
    It_deviceService it_deviceService;


    //Init method that allow to populate DB with some values
    @PostConstruct
    public void init(){
        //User groups init
        User_group ug1 = new User_group(1L,"admin","administrators of the system");
        User_group ug2 = new User_group(2L,"itsupport","it support staff");
        User_group ug3 = new User_group(3L,"user","normal user");
        userService.addUserGroup(ug1);
        userService.addUserGroup(ug2);
        userService.addUserGroup(ug3);

        //Users init
        User u1 = new User("admin","adminadmin",ug1);
        User u2 = new User("itsupport","testme",ug2);
        User u3 = new User("user","useruser",ug3);
        userService.addUpdateUser(u1);
        userService.addUpdateUser(u2);
        userService.addUpdateUser(u3);

        //Laptop types init
        Laptop_type lt1 = new Laptop_type("laptop","regular laptop");
        Laptop_type lt2 = new Laptop_type("tablet","a big smartphone");
        Laptop_type lt3 = new Laptop_type("smartphone","a small tablet");
        it_deviceService.addLaptopType(lt1);
        it_deviceService.addLaptopType(lt2);
        it_deviceService.addLaptopType(lt3);

        //Device init
        Laptop l1 = new Laptop(null,"5E:FF:56:A2:AF:15","Macbookpro",2400.0,4,8,512,"Apple Co.",13,null);
        Laptop l2 = new Laptop(null,"CA:FF:29:AF:89:73","Galaxy tab S3",2400.0,4,8,512,"Samsung Ltd.",9,null);
        Desktop d1 = new Desktop(null,"5E:FF:56:A2:AF:15","Dell HP1",3400.0,4,16,1024,"Dell computers");
        Printer p1 = new Printer(null,"5E:FF:56:A2:AF:15","Samsung MF2070W",false,"first floor");

        it_deviceService.addUpdateDevice(l1);
        it_deviceService.addUpdateDevice(l2);
        it_deviceService.addUpdateDevice(d1);
        it_deviceService.addUpdateDevice(p1);
    }

    public Set<User> getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(Set<User> connectedUser) {
        this.connectedUser = connectedUser;
    }

    public void addUser(User u) {
        this.connectedUser.add(u);
    }

    public void removeUser(User u){
        if(this.connectedUser.contains(u)){
            this.connectedUser.remove(u);
        }
    }

    public boolean alreadyConnected(User u){
        return this.connectedUser.contains(u);

    }



}
