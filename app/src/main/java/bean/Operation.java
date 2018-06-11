package bean;

import java.io.Serializable;

/**
 * Created by Yougata on 2/24/2018.
 */
public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private double  montant;
    private String type;
    private Compte compte= new Compte();

    public Operation() {
    }

    public Operation(int id, double montant, String type, String ribCompte) {
        this.id = id;
        this.montant = montant;
        this.type = type;
        compte.setRib(ribCompte);
    }

    public Operation(int id, double montant, String type) {
        this.id = id;
        this.montant = montant;
        this.type = type;
    }

    public Operation(double montant, String type) {
        this.montant = montant;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", montant=" + montant +
                ", type='" + type + '\'' +
                '}';
    }
}
