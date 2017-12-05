package models.entites.jpa;

import models.entites.jpa.ItDevices.Laptop;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User_group {

    @Id
    private
    Long user_group_id;
    private String user_group_name;
    private String description;

    @OneToMany(mappedBy="ug")
    private List<User> users;


    public Long getUser_group_id() {
        return user_group_id;
    }

    public void setUser_group_id(Long user_group_id) {
        this.user_group_id = user_group_id;
    }

    public String getUser_group_name() {
        return user_group_name;
    }

    public void setUser_group_name(String user_group_name) {
        this.user_group_name = user_group_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
