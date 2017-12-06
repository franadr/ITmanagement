package controller;

import Services.Implementation.LoginService;
import Services.It_deviceService;
import Services.UserService;
import models.NavLink;
import models.entites.jpa.User;
import models.entites.jpa.User_group;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class UsersController {
    Logger logger = Logger.getLogger("UserController");

    private List<User> userList = new ArrayList<>();
    private User user_add = new User();
    private User user_mod = new User();
    private User user_del = new User();
    private List<User_group> userGroups = new ArrayList<>();



    @EJB(name="UserServiceImpl") private UserService userService;
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList(){
        this.userList = userService.getAllUsers();
        return userList;
    }

    public void removeUser(User u){
        userService.removeUser(u);
    }

    public void addUpdateUser(User u){
        userService.addUpdateUser(u);
    }

    public List<User_group> getUserGroups() {
        this.userGroups = userService.getUserGroups();
        logger.info("Usergroups size : "+this.userGroups.size());
        return userGroups;
    }

    public void setUserGroups(List<User_group> userGroups) {
        this.userGroups = userGroups;
    }

    public User getUser_add() {
        return user_add;
    }

    public void setUser_add(User user_add) {
        this.user_add = user_add;
    }

    public User getUser_mod() {
        return user_mod;
    }

    public void setUser_mod(User user_mod) {
        this.user_mod = user_mod;
    }

    public User getUser_del() {
        return user_del;
    }

    public void setUser_del(User user_del) {
        this.user_del = user_del;
    }
}
