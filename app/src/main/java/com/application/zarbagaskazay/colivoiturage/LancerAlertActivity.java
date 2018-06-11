package com.application.zarbagaskazay.colivoiturage;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import Util.DateUtil;
import Util.Util;
import WebService.util.RESTDateParam;
import WebService.util.UrlClass;
import bean.Alerte;


public class LancerAlertActivity extends AppCompatActivity {
    private static final String TAG = "LancerAlertActivity";
    ///////
    EditText poi, lar, lon, hau, descriptionInput, prixInput;
    List<String> villes;
    Spinner villeDarrivée, villeDepart, adressearriveSpinner;
    Button datedepart, dateDarrivee, tempsdepart, tempsDarrivee;
    Time timedepart, timeArrivee;
    String timedepartString, timeArriveeString;

    Alerte alerte;
    RESTDateParam departDate, arriveeDate;
    UrlClass url = new UrlClass();
    Util util = new Util();
    DateUtil dateUtil = new DateUtil();
    ActivityHelper activityHelper = new ActivityHelper();

    ///////////////////////
    private DatePickerDialog.OnDateSetListener mDateSetListenerDepart;
    private DatePickerDialog.OnDateSetListener mDateSetListenerArrivee;
    // private TimePickerDialog.OnTimeSetListener mDateSetListenerTime1;
    //private TimePickerDialog.OnTimeSetListener mDateSetListenerTime2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancer_alert);
        villes = Arrays.asList(getResources().getStringArray(R.array.ville_array));
        poi = findViewById(R.id.poids);
        lar = findViewById(R.id.largeur);
        lon = findViewById(R.id.longuer);
        hau = findViewById(R.id.hauteur);
        descriptionInput = findViewById(R.id.description);

        prixInput = findViewById(R.id.prixInput);
        villeDarrivée = (Spinner) findViewById(R.id.villeDarriveeSpinner);
        villeDarrivée = (Spinner) findViewById(R.id.villeDarriveeSpinner);
        villeDepart = (Spinner) findViewById(R.id.VilleDepartSpinner);


        poi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    poi.setHint("");
                else
                    poi.setHint("poids par Kg");
            }
        });
        hau.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    poi.setHint("");
                else
                    poi.setHint("haut : Kg");
            }
        });
        lon.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    lon.setHint("");
                else
                    lon.setHint("lon: cm³");
            }
        });
        lar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    lar.setHint("");
                else
                    lar.setHint("lar:cm³");
            }
        });


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
                    departDate = new RESTDateParam(activityHelper.onDateSetHelper(datePicker, year, month, day, datedepart, TAG));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        ////////////////////////////////////////////Date picker  arrivee ///////
        dateDarrivee = (Button) findViewById(R.id.dateDarrivee);
        dateDarrivee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDatePickerHelper(mDateSetListenerArrivee);
            }
        });

        mDateSetListenerArrivee = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                try {
                    arriveeDate = new RESTDateParam(activityHelper.onDateSetHelper(datePicker, year, month, day, dateDarrivee, TAG));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        ////////////////////////////////////////////////////////// ////////////


        tempsdepart = (Button) findViewById(R.id.tempsdepart);
        tempsdepart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(LancerAlertActivity.this, new TimePickerDialog.OnTimeSetListener() {

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
        tempsDarrivee = (Button) findViewById(R.id.tempsDarrivee);
        tempsDarrivee.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(LancerAlertActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tempsDarrivee.setText(selectedHour + ":" + selectedMinute + "H");
                        timeArrivee = new Time(selectedHour, selectedMinute, 0);
                        timeArriveeString = selectedHour + ":" + selectedMinute + ":00";

                        System.out.println("timearrivee" + timeArriveeString);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

    }


//////////////Date Helper .//////////////////


    public void onClickDatePickerHelper(DatePickerDialog.OnDateSetListener mDateSetListener) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                LancerAlertActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year, month, day);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.test);
        dialog.show();
    }


    public Double createVolume(EditText lar, EditText lon, EditText hau) {

        if (lar != null || lon != null || hau != null) {
            Double largeur = Double.parseDouble(activityHelper.getStr(lar));
            Double longeur = Double.parseDouble(activityHelper.getStr(lon));
            Double hauteur = Double.parseDouble(activityHelper.getStr(hau));

            double volume = largeur * longeur * hauteur;
            System.out.println(volume);
            return volume;
        } else {
            return 0.0;
        }

    }


    public JSONObject createAlerte() {
        Alerte alerte = new Alerte();
        alerte.setId(6514);
        alerte.setPrixPropose(Double.parseDouble(activityHelper.getStr(prixInput)));
        alerte.setContenuColis(activityHelper.getStr(descriptionInput));
        alerte.setPoids(Double.parseDouble(activityHelper.getStr(poi)));
        alerte.setVolume(createVolume(lar, lon, hau));
        //alerte.setHeure_dep(timedepart);
        //alerte.setHeure_arr(timeArrivee);
        alerte.setVilleDep(activityHelper.spinnerText(villeDepart));
        alerte.setVilleArr(activityHelper.spinnerText(villeDarrivée));


        JSONObject json = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String string = gson.toJson(alerte);
        try {
            json = new JSONObject(string);
            System.out.println("json est crée");
            return json;

        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("json n'est crée");

        }
        return json;

    }

    public int cheakDateParam() {
        int date = dateUtil.checkDate(departDate, arriveeDate);
        int time = dateUtil.checkTime(timedepartString, timeArriveeString, date);
        if (date == -1) {

            Toast.makeText(this, "la date d'arrivée est moins que la date de depart ", Toast.LENGTH_LONG).show();
            return -1;
        } else if (date != -1) {
            if (time == -1) {
                Toast.makeText(this, "erreur temps ", Toast.LENGTH_LONG).show();
                return -1;
            } else if (time == 1) {
                return 1;
            }

        }

        return -1;

    }


/////////////////////////////////////////////////


    public void lancerAlerte(View view) {
        if (cheakDateParam() == 1) {
            JSONObject param = createAlerte();

            try {
                param.put("date_dep", departDate.getDate().getTime() / 1000);
                param.put("date_arr", arriveeDate.getDate().getTime() / 1000);
                param.put("heure_dep", timedepartString);
                param.put("heure_arr", timeArriveeString);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            System.out.println(param);

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url.urlAddAlerte
                    , param,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int idE = 0;
                                int result = response.getInt("result");
                                if (result == 1) {
                                    idE = response.getInt("id_e");
                                    int idAlerte = response.getInt("id");

                                }

                                String message = response.getString("message");
                                Toast.makeText(LancerAlertActivity.this, message, Toast.LENGTH_SHORT).show();
                                System.out.println(message + "" + idE);
                                //   Intent myIntent = new Intent(this, MenuExpediteurActivity.class);

                                // myIntent.putExtra("idE", idE);

                                //startActivity(myIntent);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            System.out.println("rahhhh dkhlllllllllllllllllllttttttttttt");
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error instanceof NetworkError) {
                            } else if (error instanceof ServerError) {
                            } else if (error instanceof AuthFailureError) {
                            } else if (error instanceof ParseError) {
                            } else if (error instanceof NoConnectionError) {
                            } else if (error instanceof TimeoutError) {
                                Toast.makeText(LancerAlertActivity.this,
                                        "Oops. Timeout error!" + error.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });

            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                    10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(LancerAlertActivity.this).addToRequestQueue(jsonObjReq);

        }
    }

    public void showMap(View view) {
        Intent myIntent = new Intent(this, MapsActivity.class);
        startActivity(myIntent);
    }
}
//  }

