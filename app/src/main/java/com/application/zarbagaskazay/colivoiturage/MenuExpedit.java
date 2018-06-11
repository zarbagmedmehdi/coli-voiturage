package com.application.zarbagaskazay.colivoiturage;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import WebService.util.UrlClass;

public class MenuExpedit extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView tmail,tNtel,tNbAlert,tCreation ,tDernierAlert,tNom;
    String  ntel,dateCreation,dernierAlert,email,nomComplet;
    int nombreAlert,id;
    UrlClass url=new UrlClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_expedit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        tNtel=findViewById(R.id.telf);
        tCreation=findViewById(R.id.date_creation);
        tNbAlert=findViewById(R.id.nbr_alert) ;
        tDernierAlert=findViewById(R.id.date_dernier);
        tmail=findViewById(R.id.email);
        tNom=findViewById(R.id.nom_exp);
        getProfil();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_expedit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void    setParam( ){

        tmail.setText(email);
        tNtel.setText(ntel);
        if (dateCreation!="null"){
        tCreation.setText(dateCreation.substring(0,10));}
        tNbAlert.setText(""+nombreAlert);
        if (dateCreation!="null"){
            tDernierAlert.setText(dernierAlert.substring(0,10));}
        tNom.setText(nomComplet);

    }
    public void getProfil () {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,url.urlExpediteurProfile
                , null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse( JSONObject response) {
                        try {  ntel= response.getString("telephone");
                            email=  response.getString("email");
                            nombreAlert= (int) response.get("nbAlert");
                            dateCreation= (String) response.get("dateCreation");
                            dernierAlert= (String) response.get("dateDernierAlert");
                            nomComplet=(String) response.get("nomComplet");
                            Toast.makeText(MenuExpedit.this, dernierAlert+" "+email+" "+ntel+" "+nombreAlert+" ", Toast.LENGTH_SHORT).show();

                            setParam();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("JSONException");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MenuExpedit.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println("onerrorMessage"+error.getMessage());
                    }
                });

        VolleySingleton.getInstance(MenuExpedit.this).addToRequestQueue(jsonObjReq);

    }
}
