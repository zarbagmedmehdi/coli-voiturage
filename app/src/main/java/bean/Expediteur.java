package bean;


import java.util.List;

public class Expediteur extends User {
    private User User ;
    private List<Alerte> alertes;

    public Expediteur() {
    }

    public List<Alerte> getAlerts() {
        return alertes;
    }

    public void setAlerts(List<Alerte> alertes) {
        this.alertes = alertes;
    }

    @Override
    public String toString() {
        return "Expediteur{" +
                "alertes=" + alertes +
                '}';
    }
}
