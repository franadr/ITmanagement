package controller.managedBeans;

import Services.Implementation.LoginService;
import Services.It_deviceService;
import Services.UserService;
import controller.validators.annotations.UserExists;
import models.NavLink;
import models.entites.jpa.User;
import models.entites.jpa.User_group;
import net.bootsfaces.utils.FacesMessages;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean
@RequestScoped
public class UsersController {
    Logger logger = Logger.getLogger("UserController");

    private List<User> userList = new ArrayList<>();
    private User user_add = new User();

    @NotNull(message = "Please select a user")
    private User user_mod ;
    @NotNull(message = "Please select a user")
    private User user_del ;
    private List<User_group> userGroups = new ArrayList<>();
    private String outMessage;
    private String modifiedUsername = "";
    private User_group modifiedUserGroup = new User_group();

    public UsersController() {
        this.user_mod = new User();
    }

    @NotNull(message = "Please provide a user group")
    private User_group userGroup;

    @NotNull(message = "Please provide a username")
    @Size(min = 4 , message = "Username should be at least 4 characters long")
    @UserExists(message = "Username already exists")
    private String newUsername;

    @NotNull(message = "Please provide a password")
    @Size(min = 6, message = "The password should contains at least 6 Characters")
    private String newPassword;


    @EJB(name="UserServiceImpl") private UserService userService;
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList(){
        this.userList = userService.getAllUsers();
        return userList;
    }

    public void removeUser(){
        if(userService.removeUser(this.user_del)){
            FacesMessages.warning("deluserForm","","User "+this.user_del.getUsername()+" Deleted");

        }else{
            FacesMessages.error("deluserForm","","User "+this.user_del.getUsername()+" not Deleted : internal error");
        }
    }

    public void modifyUser(){
        logger.info("modifying user : "+this.user_mod.getUsername());

            if (userService.addUpdateUser(this.user_mod)) {
                logger.info("modified");
                FacesMessages.warning("userModForm", "", "User " + this.user_mod.getUsername() + " updated");
            } else {
                logger.info("not modified");
                FacesMessages.error("userModForm", "", "User " + this.user_mod.getUsername() + " not updated : internal error");
            }




    }

    public void onChange(AjaxBehaviorEvent e){
        logger.info("User name has changed : "+this.user_mod.getUsername());
    }

    public void addUpdateUser(){
        this.user_add.setPassword(this.newPassword);
        this.user_add.setUsername(this.newUsername);
        this.user_add.setUg(this.userGroup);
        if(userService.addUpdateUser(this.user_add)){
            FacesMessages.info("userAddForm","","User "+this.user_add.getUsername()+" Saved");
        this.newPassword=null;
        this.newUsername=null;
        }else{
            FacesMessages.error("userAddForm","","User not Saved");
        }
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

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public User_group getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(User_group userGroup) {
        this.userGroup = userGroup;
    }

    public String getOutMessage() {
        return outMessage;
    }

    public void setOutMessage(String outMessage) {
        this.outMessage = outMessage;
    }

    public String getModifiedUsername() {
        return modifiedUsername;
    }

    public void setModifiedUsername(String modifiedUsername) {
        this.modifiedUsername = modifiedUsername;
    }

    public User_group getModifiedUserGroup() {
        return modifiedUserGroup;
    }

    public void setModifiedUserGroup(User_group modifiedUserGroup) {
        this.modifiedUserGroup = modifiedUserGroup;
    }
}
