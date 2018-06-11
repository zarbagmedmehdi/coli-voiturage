package com.application.zarbagaskazay.colivoiturage;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import bean.Alerte;

public class DialogAlerteSpecifiqueActiviy extends DialogFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating layout for the dialog */
        View v = inflater.inflate(R.layout.dialog_alerte_specifique, null);

        /** Getting the arguments passed to this fragment. Here we expects the selected item's position as argument */
        Bundle b = getArguments();
        Alerte alerte = (Alerte) b.get("alerte");


        /** Setting the title for the dialog window */
        getDialog().setTitle("alerte selectionné");

        /** Getting the reference to the TextView object of the layout */
        TextView dateDepart = v.findViewById(R.id.sdatedepart);
        TextView dateDarrivee = v.findViewById(R.id.sdateArrivee);
        TextView tempsDepart = v.findViewById(R.id.stempsdepart);
        TextView tempsDarrivee = v.findViewById(R.id.stempsArrivee);
        TextView villedepart = v.findViewById(R.id.svilledepart);
        TextView villeDarrivee = v.findViewById(R.id.svillearrivee);
        TextView poids = v.findViewById(R.id.spoids);
        TextView volume = v.findViewById(R.id.svolume);
        TextView description = v.findViewById(R.id.sdescription);
        TextView prix = v.findViewById(R.id.sprix);
        dateDarrivee.setText(alerte.getDateArr().toString() + "");
        tempsDepart.setText(alerte.getHeure_dep().toString() + "");
        tempsDarrivee.setText(alerte.getHeure_arr().toString() + "");
        villedepart.setText(alerte.getVilleDep().toString() + "");
        villeDarrivee.setText(alerte.getVilleArr().toString() + "");
        volume.setText(alerte.getVolume().toString() + "");
        description.setText(alerte.getContenuColis().toString() + "");
        dateDepart.setText(alerte.getDate_dep().toString() + "");
        prix.setText(alerte.getPrixPropose() + "");
        poids.setText(alerte.getPoids() + "");


        Button call = (Button) v.findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0663495590"));
                startActivity(callIntent);
            }
        });

        /** Setting the current time to the TextView object of the layout */
        // tv.setText(b.get("cc")+" hahya position");

        /** Returns the View object */
        return v;
    }

    /**
     * @param position .The position of the selected list item
     * @return. Returns the current time corresponding to the selected country
     */
//    public String getCurTime(int position){
//        String curTime = "";
//
//        /** Creating TimeZone object corresponding to the selected country */
//        TimeZone timezone = TimeZone.getTimeZone(Country.tz[position]);
//
//        /** Creating a DateFormat object */
//        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.SHORT);
//
//        /** Setting the TimeZone for the DateFormat object */
//        df.setTimeZone(timezone);
//
//        /** Getting the current date */
//        Date d = new Date();
//
//        /** Formatting the current date with DateFormat object */
//        curTime = df.format(d);
//
//        /** Returns the formatted date corresponds to the selected country */
//        return curTime;
//    }
}