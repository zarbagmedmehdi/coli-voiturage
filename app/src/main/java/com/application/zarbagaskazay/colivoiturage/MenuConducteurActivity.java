package com.application.zarbagaskazay.colivoiturage;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import WebService.util.UrlClass;

public class MenuConducteurActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView mail, tNtel, tNbTrajet, tCreation, tDernierTrajet, nom;
    String ntel, dateCreation, dernierTrajetText, email, nomText;
    int nombreTrajet, id;
    double rating;
    UrlClass url = new UrlClass();
    RatingBar ratingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_conducteur);

        //  Intent intent = getIntent();
        //id = intent.getIntExtra("id", 0);//if it's a string you stored.
        id = 1;
        tNtel = findViewById(R.id.nTelInput);
        tCreation = findViewById(R.id.creationInput);
        tNbTrajet = findViewById(R.id.nbTrajetInput);
        tDernierTrajet = findViewById(R.id.dernierTrajetInput);
        ratingbar = findViewById(R.id.rateBar);
        mail = findViewById(R.id.email777);
        nom = findViewById(R.id.nomComplet);

        getProfil();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
        getMenuInflater().inflate(R.menu.menu_conducteur, menu);
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

        if (id == R.id.lancer) {
//            Intent myIntent = new Intent(this, MainActivity.class);
//
//
//            startActivity(myIntent);        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void replaceParam() {

        mail.setText(email);
        tNtel.setText(ntel);
        tCreation.setText(dateCreation.substring(0, 10));
        tNbTrajet.setText("" + nombreTrajet);
        tDernierTrajet.setText("" + dernierTrajetText);
        nom.setText(nomText);

    }

    public void getProfil() {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url.urlConducteurProfile
                , null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ntel = response.getString("telephone");
                            email = response.getString("email");
                            nomText = response.getString("nomComplet");
                            nombreTrajet = (int) response.get("nbTrajet");
                            dateCreation = (String) response.get("dateCreation");
                            dernierTrajetText = (String) response.get("dateDernierTrajet");
                            rating = (double) response.get("rating");
                            float f = (float) rating;
                            ratingbar.setRating(f);
                            Toast.makeText(MenuConducteurActivity.this, dernierTrajetText + " " + email + " " + ntel + " " + nombreTrajet + " ", Toast.LENGTH_SHORT).show();

                            replaceParam();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("JSONException");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MenuConducteurActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println("onerrorMessage");
                    }
                });

        VolleySingleton.getInstance(MenuConducteurActivity.this).addToRequestQueue(jsonObjReq);

    }
}


