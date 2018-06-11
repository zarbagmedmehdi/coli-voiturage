package service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bean.Adresse;
import bean.Alerte;
import helper.Dispacher;

public class VilleService {


    public Adresse populateAdresseObject(JSONObject jsonObject){

        Dispacher helper=new Dispacher();
      Adresse adresse=new Adresse();
        try {
            adresse.setId((long) jsonObject.getInt("id"));
            System.out.println(adresse.getId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adresse.setNom(helper.getString("nom",jsonObject));
        System.out.println(adresse.getNom());


        return adresse ;
    }
    public ArrayList<Adresse> populate(JSONObject response){
        ArrayList<Adresse> adresses=new ArrayList();
        try{

            JSONArray jsonList=response.getJSONArray("data");
            System.out.println(jsonList);
            for(int i=0;i<jsonList.length();i++){

                JSONObject jsonObject=jsonList.getJSONObject(i);
          Adresse    adresse  =populateAdresseObject( jsonObject);
                adresses.add(adresse);
            }
            return adresses;
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("JSONException");
            return null ;
        }
    }
}
