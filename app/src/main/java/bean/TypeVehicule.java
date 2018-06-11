package bean;

import java.io.Serializable;

public class TypeVehicule implements Serializable {
    private static final Long serialVersionUId  =1L;
    private long id ;
    private String nomType ;
    private float poidsMax ;
    private float volumeMax;

    public TypeVehicule() {
    }

    public TypeVehicule(long id, String nomType, float poidsMax, float volumeMax) {
        this.id = id;
        this.nomType = nomType;
        this.poidsMax = poidsMax;
        this.volumeMax = volumeMax;
    }

    public TypeVehicule(long id) {

        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public float getPoidsMax() {
        return poidsMax;
    }

    public void setPoidsMax(float poidsMax) {
        this.poidsMax = poidsMax;
    }

    public float getVolumeMax() {
        return volumeMax;
    }

    public void setVolumeMax(float volumeMax) {
        this.volumeMax = volumeMax;
    }

}
