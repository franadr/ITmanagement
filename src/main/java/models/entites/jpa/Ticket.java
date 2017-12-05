package models.entites.jpa;

public class Ticket {
    Long ticket_id;
    String status;
    String category;
    String communication;

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

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }
}
