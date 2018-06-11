package bean;
import java.io.Serializable;

public final class Vehicule implements Serializable{
    private static final long serialVersionUID=1L;
    private  String  matricule;
    private String nomVoiture ;
    private String  couleur;
    private TypeVehicule typeVehicule;
    private Conducteur conducteur ;


    public Vehicule() {
    }

    public Vehicule(String matricule, String nomVoiture, String couleur, TypeVehicule typeVehicule) {
        this.matricule = matricule;
        this.nomVoiture = nomVoiture;
        this.couleur = couleur;
        this.typeVehicule = typeVehicule;

    }

    public Vehicule(String matricule) {
        this.matricule = matricule;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNomVoiture() {
        return nomVoiture;
    }

    public void setNomVoiture(String nomVoiture) {
        this.nomVoiture = nomVoiture;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public TypeVehicule getTypeVehicule() {
        return typeVehicule;
    }

    public void setTypeVehicule(TypeVehicule typeVehicule) {
        this.typeVehicule = typeVehicule;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "matricule='" + matricule + '\'' +
                ", nomVoiture='" + nomVoiture + '\'' +
                ", couleur='" + couleur + '\'' +
                ", typeVoiture=" + typeVehicule +

                '}';
    }
}
