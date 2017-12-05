package models.entites.jpa;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class User {

    @Id
    Long user_id;

    String username;

    String password;

    @ManyToOne
    @JoinColumn(name = "user_group_id")
    User_group ug;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public User_group getUg() {
        return ug;
    }

    public void setUg(User_group ug) {
        this.ug = ug;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
