package models.entites.jpa.ItDevices;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Laptop_type implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long type_id;

    private String type_name;
    private String description;

    @OneToMany(mappedBy="type")
    private List<Laptop> laptops;

    public Laptop_type(String type_name, String description) {
        this.type_name = type_name;
        this.description = description;
    }

    public Laptop_type() {
    }


    public long getType_id() {
        return type_id;
    }

    public void setType_id(long type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
