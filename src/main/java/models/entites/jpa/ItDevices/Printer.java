package models.entites.jpa.ItDevices;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "device_id")
public class Printer extends It_device {
    private boolean color;
    private String location;




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
}
