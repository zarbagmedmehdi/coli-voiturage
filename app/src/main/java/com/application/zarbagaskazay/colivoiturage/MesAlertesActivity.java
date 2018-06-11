package com.application.zarbagaskazay.colivoiturage;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.ArrayList;

import Adapter.AlerteAdapter;
import WebService.util.UrlClass;
import bean.Alerte;
import service.AlerteService;

public class MesAlertesActivity extends AppCompatActivity {
    AlerteService alerteService = new AlerteService();
    UrlClass url = new UrlClass();
    ListView lv;
    ActivityHelper helper = new ActivityHelper();
    JSONObject jsonObject = new JSONObject();

    ArrayList<Alerte> alertes = new ArrayList();
    AlerteAdapter alerteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mes_alertes);
        lv = findViewById(R.id.mesAlerteList);
        alerteAdapter = new AlerteAdapter(MesAlertesActivity.this);
        lv.setAdapter(alerteAdapter);
        populateListView();
        if (alertes != null) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    alerteAdapter.updateResults(alertes);
                }
            }, 2000);   //5

        } else {
            Toast.makeText(MesAlertesActivity.this, "data not found", Toast.LENGTH_SHORT).show();
        }
    }

    public void populateListView() {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url.urlMesAlerte
                , null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("data") && !response.isNull("data")) {

                            alertes = alerteService.populate(response);


                        } else
                            Toast.makeText(MesAlertesActivity.this, "recherches non trouvées", Toast.LENGTH_SHORT).show();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MesAlertesActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println("onerrorMessage" + error.getMessage() + " <-");
                    }
                });

        VolleySingleton.getInstance(MesAlertesActivity.this).addToRequestQueue(jsonObjReq);

    }


}