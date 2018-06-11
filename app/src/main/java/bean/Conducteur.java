package bean;

import java.io.Serializable;

public class Conducteur implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private double rating;
    private Vehicule vehicule;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Conducteur() {
    }

    public Conducteur(int id) {

        this.id = id;
    }

    public Conducteur(int id, double rating) {
        this.id = id;
        this.rating = rating;
    }

    public Conducteur(Vehicule vehicule, User user) {
        this.vehicule = vehicule;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    @Override
    public String toString() {
        return "Conducteur{" +
                "id=" + id +
                ", rating=" + rating +
                ", vehicule=" + vehicule +
                ", user=" + user +
                '}';
    }
}
