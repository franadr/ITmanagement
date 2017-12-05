package models.entites.jpa.ItDevices;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "device_id")
public abstract class Computer extends It_device {

    private double CPU_speed;
    private int CPU_count;
    private int RAM;
    private int disk_space;
    private String manufacturer;

    public Computer(){
        super();
    }

    public double getCPU_speed() {
        return CPU_speed;
    }

    public void setCPU_speed(double CPU_speed) {
        this.CPU_speed = CPU_speed;
    }

    public int getCPU_count() {
        return CPU_count;
    }

    public void setCPU_count(int CPU_count) {
        this.CPU_count = CPU_count;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public int getDisk_space() {
        return disk_space;
    }

    public void setDisk_space(int disk_space) {
        this.disk_space = disk_space;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
