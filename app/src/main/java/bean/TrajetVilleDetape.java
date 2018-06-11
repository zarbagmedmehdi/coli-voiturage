package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrajetVilleDetape implements Serializable {
    private static final Long serialVersionUId  =1L;
  private  Long id ;
    private Trajet trajet ;
   private List<Ville> villes=new ArrayList();

    public TrajetVilleDetape() {
    }

    public TrajetVilleDetape(Trajet trajet) {
        this.trajet = trajet;
    }

    public TrajetVilleDetape(Trajet trajet, List<Ville> villes) {
        this.trajet = trajet;
        this.villes = villes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public List<Ville> getVilles() {
        return villes;
    }

    public void setVilles(List<Ville> villes) {
        this.villes = villes;
    }

    @Override
    public String toString() {
        return "TrajetVilleDetape{" +
                "id=" + id +
                ", trajet=" + trajet +
                ", villes=" + villes +
                '}';
    }
}
