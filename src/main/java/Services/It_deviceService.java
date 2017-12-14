package Services;

import models.entites.jpa.ItDevices.*;
import models.entites.jpa.Ticket;
import models.entites.jpa.User;

import java.util.List;

public interface It_deviceService {

    boolean addUpdateDevice(It_device i);
    void removeDevice(It_device i);
    void addLaptopType(Laptop_type lt);
    boolean addUpdateTicket(Ticket t);
    Laptop_type getALaptopType(String typeName);

    <T extends It_device> List getItdeviceByUser(Class<T> entityClass, User u);
    <T extends It_device> List getItdevice(Class<T> entityClass);
    List getLaptopType();
    List getTickets(It_device i);
    List getTickets();

}
