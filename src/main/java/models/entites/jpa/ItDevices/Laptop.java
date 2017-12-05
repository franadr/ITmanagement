package models.entites.jpa.ItDevices;

import javax.persistence.*;


@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "device_id")
public class Laptop extends Computer {
    private int screen_size;

    @ManyToOne
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
}
