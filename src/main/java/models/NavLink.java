package models;

public class NavLink {

    public NavLink(String outcome,String name) {
        this.name=name;
        this.outcome=outcome;
    }

    private String outcome;
    private String name;

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
