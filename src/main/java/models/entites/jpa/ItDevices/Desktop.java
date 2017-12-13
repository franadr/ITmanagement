package models.entites.jpa.ItDevices;

import models.entites.jpa.User;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "device_id")
public class Desktop extends Computer {


    public Desktop(User owner, String mac_address, String device_name, double CPU_speed, int CPU_count, int RAM, int disk_space, String manufacturer) {
        super(owner, mac_address, device_name, CPU_speed, CPU_count, RAM, disk_space, manufacturer);
    }


    public Desktop() {
    }
}
