package models.entites.jpa.ItDevices;

import models.entites.jpa.User;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "device_id")
public class Printer extends It_device {
    private boolean color;
    private String location;

    public Printer() {
        super();
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Printer(User owner, String mac_address, String device_name, boolean color, String location) {
        super(owner, mac_address, device_name);
        this.color = color;
        this.location = location;
    }

    public Printer(User owner, String mac_address, String device_name) {
        super(owner, mac_address, device_name);
    }


}
