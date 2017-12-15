package models.entites.jpa;

import models.entites.jpa.ItDevices.It_device;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "ticket2")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_id;
    private String status;
    private String initialIssue;
    private String supportMessage;

    private boolean editable;

    @ManyToOne
    @JoinColumn(name="requester_id")
    private User requester;
    @ManyToOne
    @JoinColumn(name="supportStaff_id")
    private User supportStaff;

    @ManyToOne
    @JoinColumn(name="device_id")
    private It_device it_device;

    public Ticket() {
    }

    public Ticket(String initialIssue, String supportMessage, User requester, It_device it_device,User supportStaff) {
        this.supportStaff = supportStaff;
        this.initialIssue = initialIssue;
        this.supportMessage = supportMessage;
        this.requester = requester;
        this.it_device = it_device;
        this.editable = false;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Long ticket_id) {
        this.ticket_id = ticket_id;
    }

    public String getInitialIssue() {
        return initialIssue;
    }

    public void setInitialIssue(String initialIssue) {
        this.initialIssue = initialIssue;
    }

    public String getSupportMessage() {
        return supportMessage;
    }

    public void setSupportMessage(String supportMessage) {
        this.supportMessage = supportMessage;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getSupportStaff() {
        return supportStaff;
    }

    public void setSupportStaff(User supportStaff) {
        this.supportStaff = supportStaff;
    }

    public It_device getIt_device() {
        return it_device;
    }

    public void setIt_device(It_device it_device) {
        this.it_device = it_device;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
