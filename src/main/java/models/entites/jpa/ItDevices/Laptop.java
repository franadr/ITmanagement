package models.entites.jpa.ItDevices;

import models.entites.jpa.User;

import javax.persistence.*;


@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "device_id")
public class Laptop extends Computer {
    private int screen_size;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "type_id")
    private Laptop_type type;
    @Transient
    @PersistenceContext(name = "ExamplePU")
    EntityManager em;

    public int getScreen_size() {
        return screen_size;
    }

    public void setScreen_size(int screen_size) {
        this.screen_size = screen_size;
    }

    public Laptop_type getType() {
        return type;
    }

    public void setType(Laptop_type type) {
        this.type = type;
    }

    public Laptop(User owner, String mac_address, String device_name, double CPU_speed, int CPU_count, int RAM, int disk_space, String manufacturer, int screen_size, Laptop_type type) {
        super(owner, mac_address, device_name, CPU_speed, CPU_count, RAM, disk_space, manufacturer);
        this.screen_size = screen_size;
        this.type = type;
    }

    public Laptop() {
        super();
    }
}
