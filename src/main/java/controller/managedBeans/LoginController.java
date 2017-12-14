package controller.managedBeans;
/**
 * This file holds the Managed bean responsible for login related controls
 *
 * it holds the information of the user connected
 *
 * Made SessionScoped so the user can continue navigate using his credential without having to login again
 *
 * Adriano UNI.lu 2017 011109344A
 */


import Services.Implementation.LoginService;
import models.NavLink;
import models.entites.jpa.User;
import models.entites.jpa.User_group;
import net.bootsfaces.utils.FacesMessages;


import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
public class LoginController implements Serializable {
    private User user = new User();
    private User_group user_group;
    private boolean isloogedIn = false;
    private boolean isAdmin = false;
    private boolean isItsupport = false;
    private ArrayList<NavLink> navlink = new ArrayList<>();
    static Logger logger = Logger.getLogger("LoginController");


    @EJB private LoginService loginService;

    @ManagedProperty(value = "#{applicationController}")
    private
    ApplicationController applicationController;

    public LoginController(){
    }
    public String validateUser(User user){

        User _user = loginService.getLogin(this.user);
        if(_user != null) {

            this.isloogedIn=true;
            this.user=_user;
            this.isAdmin = (this.user.getUg().getUser_group_name().equals("admin"));
            this.isItsupport = (this.user.getUg().getUser_group_name().equals("itsupport"));
            if(!this.applicationController.alreadyConnected(this.user)) {
                this.applicationController.addUser(this.user);
                logger.info("Logged in : "+this.user.getUsername());
                return "/home.xhtml?faces-redirect=true";
            }else{
                FacesMessages.info("loginForm","","Already connected");
                return "/login.xhtml?faces-redirect=true";
            }
        }else{
            logger.info("NOT Logged IN");
            FacesMessages.error("loginForm","","Wrong user/password couple");
            this.isloogedIn=false;
            return "/login.xhtml?faces-redirect=true";
        }

    }



    public User_group getUser_group() {
        return user_group;
    }

    public void setUser_group(User_group user_group) {
        this.user_group = user_group;
    }

    public ArrayList<NavLink> getNavlink() {
        return navlink;
    }

    public void setNavlink(ArrayList<NavLink> navlink) {
        this.navlink = navlink;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public boolean isIsloogedIn() {
        return isloogedIn;
    }

    public void setIsloogedIn(boolean isloogedIn) {
        this.isloogedIn = isloogedIn;
    }

    public void loginCheck(){
        if(!this.isloogedIn){
            logger.info("Access denied");
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getApplication().getNavigationHandler().handleNavigation(fc, null, "login");
        }else
            logger.info("Access granted");

    }

    public void roleCheck(ArrayList roles){


        if(this.isloogedIn){
            if(!(roles.contains(this.user.getUg().getUser_group_name()))){
                logger.info("Access denied for {"+this.user.getUsername()+"} not correct role");
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.getApplication().getNavigationHandler().handleNavigation(fc, null, "denied");
            }
        }else{
            logger.info("Access denied , not legged in or session expired");
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getApplication().getNavigationHandler().handleNavigation(fc, null, "login");
        }




    }

    public String logout(){
        this.applicationController.removeUser(this.user);
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                .getSession(true)).invalidate();
        return "/login.xhtml?faces-redirect=true";
    }

    public ApplicationController getApplicationController() {
        return applicationController;
    }

    public void setApplicationController(ApplicationController applicationController) {
        this.applicationController = applicationController;
    }
}
