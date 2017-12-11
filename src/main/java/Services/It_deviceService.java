package Services;

import models.entites.jpa.ItDevices.Desktop;
import models.entites.jpa.ItDevices.It_device;
import models.entites.jpa.ItDevices.Laptop;
import models.entites.jpa.ItDevices.Printer;
import models.entites.jpa.User;

import java.util.List;

public interface It_deviceService {

    void addUpdateDevice(It_device i);
    void removeDevice(It_device i);

    <T extends It_device> List getItdeviceByUser(Class<T> entityClass, User u);
    <T extends It_device> List getItdevice(Class<T> entityClass);
    List getLaptopType();
}
