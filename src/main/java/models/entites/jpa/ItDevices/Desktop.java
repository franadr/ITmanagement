package models.entites.jpa.ItDevices;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "device_id")
public class Desktop extends Computer {
}
