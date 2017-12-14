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
import javax.ejb.Singleton;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ManagedBean
@ApplicationScoped
public class ApplicationController {
    private Set<User> connectedUser = new HashSet<>();
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
