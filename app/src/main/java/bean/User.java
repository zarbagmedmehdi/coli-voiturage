package bean;

import java.io.Serializable;

public class User  implements Serializable {
    private static final long serialVersionUID = 1L;
private Long id;
private String nom;
private String prenom;
private String email;
private String telephone;
private String carteBancaire;
private String password;

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(Long id, String nom, String prenom, String email, String telephone,  String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
    }

    public User( String nom, String prenom, String email, String telephone,  String password) {

        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.carteBancaire = carteBancaire;
        this.password = password;
    }


    public User(String nom, String prenom, String email, String telephone, Object o, String password) {
    }

    public long getId() {
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCarteBancaire() {
        return carteBancaire;
    }

    public void setCarteBancaire(String carteBancaire) {
        this.carteBancaire = carteBancaire;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", carteBancaire='" + carteBancaire + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
