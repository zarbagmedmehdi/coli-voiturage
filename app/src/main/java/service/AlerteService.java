package service;

import android.widget.ListView;

import com.application.zarbagaskazay.colivoiturage.ActivityHelper;
import com.application.zarbagaskazay.colivoiturage.ChercherAlerteActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import Adapter.AlerteAdapter;
import WebService.util.UrlClass;
import bean.Alerte;
import helper.Dispacher;

public class AlerteService {

    public Alerte populateAlerteObject(JSONObject jsonObject){
        ArrayList<Alerte> alertes=new  ArrayList();
        Dispacher helper=new Dispacher();
        Alerte alerte = new Alerte();
        alerte.setDateDep(helper.getString("date_dep",jsonObject));
        alerte.setVilleDep(helper.getString("villeDep",jsonObject));
        alerte.setVilleArr(helper.getString("villeArr",jsonObject));
        alerte.setVolume(helper.getDouble("volume", jsonObject));
        alerte.setPoids(helper.getDouble("poids", jsonObject));
        alerte.setPrixPropose(helper.getDouble("prixPropose", jsonObject));


        return alerte ;
    }
    public void ListViewPopulate(AlerteAdapter alerteAdapter,  ListView lv){
            lv.setAdapter(alerteAdapter);
    }

    public ArrayList<Alerte> cheakNull(ArrayList<Alerte>  alertes){
        if(alertes==null || alertes.isEmpty())
           return new ArrayList();
        else

       return alertes ;
    }


    public ArrayList<Alerte> populate( JSONObject response){
       ArrayList<Alerte> alertes=new ArrayList();
        try{

        JSONArray jsonList=response.getJSONArray("data");

        for(int i=0;i<jsonList.length();i++){
            Alerte alerte=new Alerte();
            JSONObject jsonObject=jsonList.getJSONObject(i);
            alerte=populateAlerteObject( jsonObject);
            alertes.add(alerte);
        }
       return alertes;
    } catch (JSONException e) {
        e.printStackTrace();
        System.out.println("JSONException");
        return null ;
    }
    }



}
