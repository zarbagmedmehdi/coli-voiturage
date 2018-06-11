package bean;

import android.provider.ContactsContract;

import java.io.Serializable;
import java.util.Date;
import java.sql.Time;
import java.util.Objects;

public class Alerte implements Serializable {
    private static final Long serialVersionUID= 1L;
    private int id;
    private java.sql.Date date_dep;
    private java.sql.Date date_arr;
    private Time heure_dep;
    private Time  heure_arr;
    private String contenuColis;
    private Double poids;
    private Double volume;
    private String villeDep;
    private  String villeArr;
    private int id_e ;
    private double prixPropose;
    private   String  dateDep;
    private String dateArr ;

    public Alerte() {

    }

    public String getDateDep() {
        return dateDep;
    }

    public void setDateDep(String dateDep) {
        this.dateDep = dateDep;
    }

    public String getDateArr() {
        return dateArr;
    }

    public void setDateArr(String dateArr) {
        this.dateArr = dateArr;
    }

    public Alerte(int id, Double prixPropos, java.sql.Date date_dep, java.sql.Date date_arr, Time heure_dep, Time heure_arr, String contenuColis, Double poids, Double volume, String villeDep, String villeArr, int id_e) {
        this.id = id;
        this.date_dep = date_dep;
        this.date_arr = date_arr;
        this.heure_dep = heure_dep;
        this.contenuColis = contenuColis;
        this.poids = poids;
        this.volume = volume;
        this.villeDep = villeDep;
        this.villeArr = villeArr;
        this.id_e = id_e;
        this.prixPropose=prixPropose;
        this.heure_arr=heure_arr;
    }

    public Alerte(int id) {
        this.id = id;
    }

    public Alerte(int id, int id_e) {
        this.id = id;
        this.id_e = id_e;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getHeure_arr() {
        return heure_arr;
    }

    public void setHeure_arr(Time heure_arr) {
        this.heure_arr = heure_arr;
    }

    public double getPrixPropose() {
        return prixPropose;
    }

    public void setPrixPropose(double prixPropose) {
        this.prixPropose = prixPropose;
    }

    public java.sql.Date getDate_dep() {
        return date_dep;
    }

    public void setDate_dep(java.sql.Date date_dep) {
        this.date_dep = date_dep;
    }

    public java.sql.Date getDate_arr() {
        return date_arr;
    }

    public void setDate_arr(java.sql.Date date_arr) {
        this.date_arr = date_arr;
    }

    public Time getHeure_dep() {
        return heure_dep;
    }

    public void setHeure_dep(Time heure_dep) {
        this.heure_dep = heure_dep;
    }

    public String getContenuColis() {
        return contenuColis;
    }

    public void setContenuColis(String contenuColis) {
        this.contenuColis = contenuColis;
    }

    public Double getPoids() {
        return poids;
    }

    public void setPoids(Double poids) {
        this.poids = poids;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
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

    public int getId_e() {
        return id_e;
    }

    public void setId_e(int id_e) {
        this.id_e = id_e;
    }


}

