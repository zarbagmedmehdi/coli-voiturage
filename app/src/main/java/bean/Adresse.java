package bean;

import android.provider.SearchRecentSuggestions;

import java.io.Serializable;
import java.util.Objects;

public class Adresse implements Serializable {
    private static final Long serialVersionUID=1L;
    private Long id;
    private String nom;


    public Adresse() {
    }

    public Adresse(Long id) {
        this.id = id;
    }

    public Adresse(Long id, String nom) {
        this.id = id;
        this.nom = nom;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    @Override
    public String toString() {
        return "Adresse{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
