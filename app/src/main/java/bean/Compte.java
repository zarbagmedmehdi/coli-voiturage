package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yougata on 2/24/2018.
 */
public class Compte {
    private String rib;
    private double solde;
    private List<Operation> operations = new ArrayList<>();
    private User user ;

    public Compte() {
    }

    public Compte(String string, double aDouble) {

    }

    public Compte(String id) {
        this.rib = id;
    }

    public Compte(String rib, double solde, User user) {
        this.rib = rib;
        this.solde = solde;

        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String id) {
        this.rib = id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "rib='" + rib + '\'' +
                ", solde=" + solde +
                ", user=" + user +
                '}';
    }
}
