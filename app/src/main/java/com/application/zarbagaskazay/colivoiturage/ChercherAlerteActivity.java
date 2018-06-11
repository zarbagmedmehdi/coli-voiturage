package com.application.zarbagaskazay.colivoiturage;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

import Adapter.AlerteAdapter;
import WebService.util.RESTDateParam;
import WebService.util.UrlClass;
import bean.Alerte;
import service.AlerteService;

public class ChercherAlerteActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "CherhcerAlerteActivity";
    UrlClass url = new UrlClass();
    ListView lv;
    ActivityHelper helper = new ActivityHelper();
    Button datedepart, tempsdepart;
    Time timedepart;
    String timedepartString, villeDepartString, villeArrivéeString;
    EditText poidsMax, poidsMin, volumeMin, volumeMax;
    double pMin, pMax, vMin, vMax;
    Spinner villeDepart, villeArrivée;
    JSONObject jsonObject = new JSONObject();
    AlerteService alerteService = new AlerteService();
    ArrayList<Alerte> alertes;
    AlerteAdapter alerteAdapter;
    RESTDateParam departDate;

    private DatePickerDialog.OnDateSetListener mDateSetListenerDepart;


    // String laser="http://laserbeauty.be/api/api/instituts" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chercher_alerte);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        villeDepart = (Spinner) findViewById(R.id.villeDepartSpinner);
        villeArrivée = (Spinner) findViewById(R.id.villeDarriveeSpinner);
        poidsMin = findViewById(R.id.poidsMin);
        poidsMax = findViewById(R.id.poidsMax);
        volumeMax = findViewById(R.id.volumeMax);
        volumeMin = findViewById(R.id.volumeMin);
        lv = (ListView) findViewById(R.id.list_view);

       // populateListView(2);
        //if (alertes != null) {
      //      alerteAdapter = new AlerteAdapter(ChercherAlerteActivity.this, alertes);

     //   } else
           alerteAdapter = new AlerteAdapter(ChercherAlerteActivity.this);
        lv.setAdapter(alerteAdapter);
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {

                /** Instantiating TimeDailogFragment, which is a DialogFragment object */
                DialogAlerteSpecifiqueActiviy tFragment = new DialogAlerteSpecifiqueActiviy();

                /** Creating a bundle object to store the position of the selected country */
                Bundle b = new Bundle();

                /** Storing the position in the bundle object */
              b.putSerializable("alerte",alertes.get(position) );


                b.putInt("position",position);

                /** Setting the bundle object as an argument to the DialogFragment object */
                tFragment.setArguments(b);

                /** Getting FragmentManager object */
                FragmentManager fragmentManager = getFragmentManager();

                /** Starting a FragmentTransaction */
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                /** Getting the previously created fragment object from the fragment manager */
                DialogAlerteSpecifiqueActiviy tPrev =  (DialogAlerteSpecifiqueActiviy) fragmentManager.findFragmentByTag("time_dialog");

                /** If the previously created fragment object still exists, then that has to be removed */
                if(tPrev!=null)
                    fragmentTransaction.remove(tPrev);

                /** Opening the fragment object */
                tFragment.show(fragmentTransaction, "time_dialog");
            }
        };

        /** Setting an item click event handler for the list view */
        lv.setOnItemClickListener(listener);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /////////////// date picker dateDepart////////////
        datedepart = (Button) findViewById(R.id.datedepart);
        datedepart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDatePickerHelper(mDateSetListenerDepart);
            }
        });

        mDateSetListenerDepart = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                try {
                    departDate = new RESTDateParam(helper.onDateSetHelper(datePicker, year, month, day, datedepart, TAG));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        tempsdepart = (Button) findViewById(R.id.tempsdepart);
        tempsdepart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ChercherAlerteActivity.this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tempsdepart.setText(selectedHour + ":" + selectedMinute + "H");
                        timedepart = new Time(selectedHour, selectedMinute, 00);
                        timedepartString = selectedHour + ":" + selectedMinute + ":00";

                        System.out.println("timedepart" + timedepartString);

                    }
                }, hour, minute, true); //Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }


        });
        poidsMin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    poidsMin.setHint("");
                else
                    poidsMin.setHint("poids min");
            }
        });
        poidsMax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    poidsMax.setHint("");
                else
                    poidsMax.setHint("poids max");
            }
        });
        volumeMin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    volumeMin.setHint("");
                else
                    volumeMin.setHint("volum min");
            }
        });
        volumeMax.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    volumeMax.setHint("");
                else
                    volumeMax.setHint("volum max");
            }
        });
    }

    public void onClickDatePickerHelper(DatePickerDialog.OnDateSetListener mDateSetListener) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                ChercherAlerteActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.test);
        dialog.show();
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
        getMenuInflater().inflate(R.menu.main2, menu);
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


    public JSONObject collectData() {
        JSONObject jsonObject = new JSONObject();

        try {
            helper.enterData("poidsMin", jsonObject, poidsMin);
            helper.enterData("poidsMax", jsonObject, poidsMax);
            helper.enterData("volumeMin", jsonObject, volumeMin);
            helper.enterData("volumeMax", jsonObject, volumeMax);
            jsonObject.put("villeDep", helper.spinnerText(villeDepart));
            jsonObject.put("villeArr", helper.spinnerText(villeArrivée));
            if (departDate != null)
                jsonObject.put("date_dep", departDate.getDate().getTime() / 1000);
            if (timedepartString != null)
                jsonObject.put("heure_dep", timedepartString);
            System.out.println(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public void afficherResultat(View view) {
        System.out.println("afficherResultat");
        populateListView(1);
        if (alertes != null) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    alerteAdapter.updateResults(alertes);

                }
            }, 2000);   //5
        //    alerteAdapter.updateResults(alertes);
            Toast.makeText(ChercherAlerteActivity.this, "recherches  trouvées", Toast.LENGTH_SHORT).show();

        }
else {
            Toast.makeText(ChercherAlerteActivity.this, "recherches non trouvées", Toast.LENGTH_SHORT).show();

        }

    }

    public void afficherTout(View view) {
        System.out.println("afficherTout");
        populateListView(2);
        if (alertes != null) {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    alerteAdapter.updateResults(alertes);

                }
            }, 2000);   //5
            Toast.makeText(ChercherAlerteActivity.this, "find all", Toast.LENGTH_SHORT).show();

        } else {


        }
    }


    public void populateListView(int iDbutton) {

        String urlWanted = null;
        switch (iDbutton) {
            case 1:
                jsonObject = collectData();
                urlWanted = url.urlFindAlertes;


                break;
            case 2:
                jsonObject = null;
                urlWanted = url.urlAllAlerte;


                break;
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, urlWanted
                , jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.has("data") && !response.isNull("data")) {
                            alertes = alerteService.populate(response);

                        } else
                            Toast.makeText(ChercherAlerteActivity.this, "recherches non trouvées", Toast.LENGTH_SHORT).show();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ChercherAlerteActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println("onerrorMessage" + error.getMessage() + " <-");
                    }
                });

        VolleySingleton.getInstance(ChercherAlerteActivity.this).addToRequestQueue(jsonObjReq);


    }

}
