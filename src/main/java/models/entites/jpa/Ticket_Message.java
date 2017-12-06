package models.entites.jpa;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Ticket_Message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long message_id;

    private String message;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User message_origin;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    Ticket ticket;


    public Long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
