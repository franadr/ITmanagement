package Services;

import models.entites.jpa.ItDevices.Desktop;
import models.entites.jpa.ItDevices.It_device;
import models.entites.jpa.ItDevices.Laptop;
import models.entites.jpa.ItDevices.Printer;
import models.entites.jpa.User;

import java.util.List;

public interface It_deviceService {

    public void addUpdateDevice(It_device i);
    public void removeDevice(It_device i);
    public List<Laptop> getLaptopByUser(User u);
    public List<Desktop> getDesktopsByUser(User u);
    public List<Printer> getPrinterByUser(User u);

    <T extends It_device> List getItdeviceByUser(Class<T> entityClass, User u);
}
