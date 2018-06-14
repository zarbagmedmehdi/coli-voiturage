package bean;

import java.io.Serializable;
import java.sql.Time;

public class Trajet implements Serializable {
    private static final long serialVersionUID  =1L;
    private int id;
    private Conducteur conducteur;
    private String date_dep;
    private String date_arr;
    private Time heure_dep;
    private Time heure_arr;
    private String villeDep;
    private String villeArr;
    private Vehicule vehicule;
    private String description;
    private float poidsRest;
    private float volumeRest;
    private TrajetVilleDetape trajetVilleDetape;

    public Trajet() {
    }

    public float getPoidsRest() {
        return poidsRest;
    }

    public TrajetVilleDetape getTrajetVilleDetape() {
        return trajetVilleDetape;
    }

    public void setTrajetVilleDetape(TrajetVilleDetape trajetVilleDetape) {
        this.trajetVilleDetape = trajetVilleDetape;
    }

    public Trajet(int id, Vehicule vehicule, String description, float poidsRest, float volumeRest) {
        this.id = id;
        this.vehicule = vehicule;
        this.description = description;
        this.poidsRest = poidsRest;
        this.volumeRest = volumeRest;
    }

    public void setPoidsRest(float poidsRest) {
        this.poidsRest = poidsRest;
    }

    public float getVolumeRest() {
        return volumeRest;
    }

    public void setVolumeRest(float volumeRest) {
        this.volumeRest = volumeRest;
    }

    public Trajet(int id) {
        this.id = id;
    }

    public Trajet(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Conducteur getConducteur() {
        return conducteur;
    }

    public void setConducteur(Conducteur conducteur) {
        this.conducteur = conducteur;
    }

    public String getDate_dep() {
        return date_dep;
    }

    public void setDate_dep(String date_dep) {
        this.date_dep = date_dep;
    }

    public String getDate_arr() {
        return date_arr;
    }

    public void setDate_arr(String date_arr) {
        this.date_arr = date_arr;
    }

    public Time getHeure_dep() {
        return heure_dep;
    }

    public void setHeure_dep(Time heure_dep) {
        this.heure_dep = heure_dep;
    }

    public Time getHeure_arr() {
        return heure_arr;
    }

    public void setHeure_arr(Time heure_arr) {
        this.heure_arr = heure_arr;
    }

    public String getVilleDep() {
        return villeDep;
    }

    public void setVilleDep(String villeDep) {
        this.villeDep = villeDep;
    }

    public String getVilleArr() {
        return villeArr;
    }

    public void setVilleArr(String villeArr) {
        this.villeArr = villeArr;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Trajet{" +
                "id=" + id +
                ", villeDep='" + villeDep + '\'' +
                ", villeArr='" + villeArr + '\'' +
                ", description='" + description + '\'' +
                ", poidsRest=" + poidsRest +
                ", volumeRest=" + volumeRest +
                '}';
    }
}
