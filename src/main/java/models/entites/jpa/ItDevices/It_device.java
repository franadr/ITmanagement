package models.entites.jpa.ItDevices;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="it_device")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class It_device implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long device_id;

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
}
