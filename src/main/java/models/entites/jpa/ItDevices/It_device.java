package models.entites.jpa.ItDevices;

import models.entites.jpa.Ticket;
import models.entites.jpa.User;
import models.entites.jpa.User_group;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name="it_device")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class It_device implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long device_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User owner;

    @OneToMany(mappedBy="it_device")
    List<Ticket> issues;



    private String mac_address;
    private String device_name;

    public Long getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Long device_id) {
        this.device_id = device_id;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public It_device(User owner, String mac_address, String device_name) {
        this.owner = owner;
        this.mac_address = mac_address;
        this.device_name = device_name;
    }
    public It_device(){}
}
