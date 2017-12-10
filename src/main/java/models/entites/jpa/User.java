package models.entites.jpa;


import models.entites.jpa.ItDevices.It_device;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
@Entity

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger("User");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long user_id;

    String username;

    String password;

    @OneToMany(mappedBy = "owner")
    List<It_device> it_devices;

    @OneToMany(mappedBy="requester")
    List<Ticket> requestedTickets;

    @OneToMany(mappedBy="supportStaff")
    List<Ticket> handlingTickets;

    @OneToMany(mappedBy = "message_origin")
    List<Ticket_Message> ticket_Ticket_messages;

    @ManyToOne
    @JoinColumn(name = "user_group_id")
    User_group ug;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public User_group getUg() {
        return ug;
    }

    public void setUg(User_group ug) {
        this.ug = ug;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        logger.info("Name property changed "+ userName);
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
