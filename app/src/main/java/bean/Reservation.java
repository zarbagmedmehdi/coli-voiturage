package bean;

import java.io.Serializable;

public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

    private  Long id ;
    private float poids ;
    private float volume ;
    private Trajet trajet ;
    private Expediteur expediteur;


    public Reservation() {
    }

    public Reservation(Long id, float poids, float volume, Trajet trajet, Expediteur expediteur) {
        this.id = id;
        this.poids = poids;
        this.volume = volume;
        this.trajet = trajet;
        this.expediteur = expediteur;
    }

    public Reservation(Long id) {

        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPoids() {
        return poids;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public Expediteur getExpediteur() {
        return expediteur;
    }

    public void setExpediteur(Expediteur expediteur) {
        this.expediteur = expediteur;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", poids=" + poids +
                ", volume=" + volume +
                ", trajet=" + trajet +
                ", expediteur=" + expediteur +
                '}';
    }

}
