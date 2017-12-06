package models.entites.jpa;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticket_id;
    private String status;
    private String category;

    @ManyToOne
    @JoinColumn(name="requester_id")
    private User requester;
    @ManyToOne
    @JoinColumn(name="supportStaff_id")
    private User supportStaff;

    @OneToMany(mappedBy="ticket")
    private List<Ticket_Message> ticketMessages;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Ticket_Message> getTicketMessages() {
        return ticketMessages;
    }

    public void setTicketMessages(List<Ticket_Message> ticketMessages) {
        this.ticketMessages = ticketMessages;
    }

    public Long getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(Long ticket_id) {
        this.ticket_id = ticket_id;
    }
}
