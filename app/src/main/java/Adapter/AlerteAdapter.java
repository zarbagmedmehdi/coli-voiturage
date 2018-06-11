package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.application.zarbagaskazay.colivoiturage.ChercherAlerteActivity;
import com.application.zarbagaskazay.colivoiturage.R;
import com.application.zarbagaskazay.colivoiturage.VolleySingleton;

import org.json.JSONObject;

import java.util.ArrayList;

import WebService.util.UrlClass;
import bean.Alerte;
import service.AlerteService;

public class AlerteAdapter   extends BaseAdapter {

    Context c;
    ArrayList<Alerte> alertes=new ArrayList();

    public AlerteAdapter() {
    }
    public void updateResults(ArrayList<Alerte> results) {
        this.alertes = results;

        notifyDataSetChanged();
    }

    public ArrayList<Alerte> getAlertes() {
        return alertes;
    }

    public void setAlertes(ArrayList<Alerte> alertes) {
        this.alertes = alertes;
    }

    public AlerteAdapter(Context c) {
        this.c = c;
    }

    public AlerteAdapter(Context c, ArrayList<Alerte> alertes) {
        this.c = c;
        this.alertes = alertes;
    }

    @Override
    public int getCount() {

        return alertes.size();
    }

    @Override
    public Object getItem(int position) {
        return alertes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alertes.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
Alerte alerte=alertes.get(position);
        if(view==null){

            view =inflater.inflate(R.layout.alerte_model,null);}
             int numero=position+1;
            TextView numerotation=view.findViewById(R.id.numeration);
        TextView  villeDepar= view.findViewById(R.id.villeDepartID);
        TextView  villeArr= view.findViewById(R.id.villeArriveeID);
        TextView  dateDepar= view.findViewById(R.id.dateDepartID);
//        TextView  prix= view.findViewById(R.id.prixID);
//        TextView  poids= view.findViewById(R.id.poidsID);
//        TextView  volume= view.findViewById(R.id.volumeID);
        numerotation.setText("alerte n° "+numero);
villeDepar.setText("ville départ : "+alerte.getVilleDep().toString());
villeArr.setText("ville arrivée : "+alerte.getVilleArr().toString());
if( alerte.getDateDep()!="null"){
dateDepar.setText("date départ : "+alerte.getDateDep().substring(0,11));}
//prix.setText("prix : "+alerte.getPrixPropose()+"DH");
//poids.setText("poids : "+alerte.getPoids()+"Kg");
//volume.setText("volume : "+alerte.getVolume()+"cm³");

return  view;
    }


}
