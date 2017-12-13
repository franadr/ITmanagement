package controller.managedBeans;


import models.entites.jpa.User;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
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
