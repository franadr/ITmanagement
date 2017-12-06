package controller;


import Services.Implementation.LoginService;
import models.NavLink;
import models.entites.jpa.User;
import models.entites.jpa.User_group;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
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
    private ArrayList<NavLink> navlink = new ArrayList<>();
    static Logger logger = Logger.getLogger("LoginController");
    @EJB
    private
    LoginService loginService;

    public LoginController(){
        logger.info("Navlink init, loginController constructor");
        navlink.add(new NavLink("home","home"));
        navlink.add(new NavLink("logout","logout"));
    }
    public String validateUser(User user){
        logger.info("Trying to login");
        User _user = loginService.getLogin(this.user);
        if(_user != null) {
            logger.info("Logged IN");
            this.isloogedIn=true;
            this.user=_user;
            return "/home.xhtml?faces-redirect=true";
        }else{
            logger.info("NOT Logged IN");
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
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

    @PostConstruct
    public void loginCheck(ComponentSystemEvent event){
        if(!this.isloogedIn){
            logger.info("Access denied");
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getApplication().getNavigationHandler().handleNavigation(fc, null, "login");

        }else
            logger.info("Access granted");

    }

    public String logout(){
        logger.info("user logout log out");
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                .getSession(true)).invalidate();
        return "/login.xhtml?faces-redirect=true";
    }


}
