package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ville implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nom;
    private List<Adresse> adresses = new ArrayList();


    public Ville() {
    }

    public Ville(String nom, List<Adresse> adresses) {
        this.nom = nom;
        this.adresses = adresses;
    }

    public Ville(String nom) {

        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Adresse> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Adresse> adresses) {
        this.adresses = adresses;
    }
}