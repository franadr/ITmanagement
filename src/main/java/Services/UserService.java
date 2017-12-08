package Services;

import models.entites.jpa.User;
import models.entites.jpa.User_group;

import java.util.List;

public interface UserService {

    boolean addUpdateUser(User u);
    boolean removeUser(User u);

    List<User> getAllUsers();
    List<User_group> getUserGroups();
    User getSpecificUser(User u);
    User getSpecificUser(String username);
}
