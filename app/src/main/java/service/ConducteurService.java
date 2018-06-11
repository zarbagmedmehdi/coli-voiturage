package service;

import org.json.JSONObject;
import org.json.JSONStringer;

import bean.Conducteur;
import bean.User;
import bean.Vehicule;

public class ConducteurService {

    public void  creationConducteur  ( User user, Vehicule vehicule){
        Conducteur conducteur=new Conducteur();
        conducteur.setUser(user);
       conducteur.setVehicule(vehicule);

    }


}
