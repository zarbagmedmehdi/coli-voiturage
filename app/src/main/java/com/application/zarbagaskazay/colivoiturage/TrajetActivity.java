package com.application.zarbagaskazay.colivoiturage;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Util.DateUtil;
import WebService.util.RESTDateParam;
import WebService.util.UrlClass;
import bean.Adresse;
import bean.Trajet;
import service.VilleService;

public class TrajetActivity extends AppCompatActivity {
    private Button button_tracer;
    private Button button_lancer;
    List<String> villes;
    Spinner villeDarrivée;
    Spinner villeDepart;
    Spinner villeEtapes;
    Spinner spinner1;
    Spinner spinner2;
    EditText adresseDepart;
    EditText adresseArrivee;



    VilleService villeService=new VilleService();
    private DatePickerDialog.OnDateSetListener mDateSetListenerDepart;
    private DatePickerDialog.OnDateSetListener mDateSetListenerArrivee;
    private TimePickerDialog.OnTimeSetListener mDateSetListenerTime1;
    private TimePickerDialog.OnTimeSetListener mDateSetListenerTime2;
    private static final String TAG = "TrajetActivity";
    Button datedepart, dateDarrivee, tempsdepart, tempsDarrivee;
    Time timedepart, timeArrivee;
    String timedepartString, timeArriveeString;
    UrlClass url = new UrlClass();
    DateUtil dateUtil = new DateUtil();
    Trajet trajet;
    ActivityHelper activityHelper = new ActivityHelper();
    RESTDateParam departDate, arriveeDate;
    ArrayList<Adresse> list1;
    ArrayList<Adresse> list2;
    ArrayAdapter<Adresse> adp1,adp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trajet);
        villeDepart = (Spinner) findViewById(R.id.villeDepart);
        villeDarrivée = (Spinner) findViewById(R.id.villeDarrivée);
        villeEtapes = (Spinner) findViewById(R.id.villeDetapes);
//        adresseDepart = findViewById(R.id.adresseDepart);
//        adresseArrivee = findViewById(R.id.adresseArrivee);

        button_tracer = (Button) findViewById(R.id.btn_tracer);
        button_tracer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(TrajetActivity.this);
//                View mapView = getLayoutInflater().inflate(R.layout.activity_maps, null);
//                Button btn = (Button) mapView.findViewById(R.id.button);
//                btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        // openDialog();
//                    }
//                });
//                builder.setView(mapView);
//                AlertDialog dialog = builder.create();
//                dialog.show();
                System.out.println("cccccccc  zine ha adressses");
                getAdresse();
                System.out.println("cccccccc  zine ha adressses");
            }
        });
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
        tempsdepart = (Button) findViewById(R.id.tempsdepart);
        tempsdepart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(TrajetActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tempsdepart.setText(selectedHour + ":" + selectedMinute + "H");
                        timedepart = new Time(selectedHour, selectedMinute, 00);
                        timedepartString = selectedHour + ":" + selectedMinute + ":00";
                    }
                }, hour, minute, true);//Yes 24 hour time
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
                mTimePicker = new TimePickerDialog(TrajetActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        tempsDarrivee.setText(selectedHour + ":" + selectedMinute + "H");
                        timeArrivee = new Time(selectedHour, selectedMinute, 0);
                        timeArriveeString = selectedHour + ":" + selectedMinute + ":00";
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });








}




    public void onClickDatePickerHelper(DatePickerDialog.OnDateSetListener mDateSetListener) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                TrajetActivity.this,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year, month, day);
        dialog.show();

    }


    // public void openDialog(){
    //ExampleDialog exampleDialog = new ExampleDialog();
    //exampleDialog.show(getSupportFragmentManager(),"example dialog");
    // }









    public void getAdresse() {
        JSONObject jsonObject = new JSONObject();

       try {
//String urls=url.urlFindAdresse+"?nomVille="+activityHelper.spinnerText(villeDepart);
            jsonObject.put("nom" , activityHelper.spinnerText(villeDepart));


            System.out.println(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
            final JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url.urlFindAdresse
                    , jsonObject,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {


                            if (response.has("data") && !response.isNull("data")) {

                                ArrayList<Adresse>adresses=villeService.populate(response);
                                System.out.println(adresses);
                            } else {


                                System.out.println(" rah liste khawya");

                            }
                        }

                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("onerrorMessage" + error.getMessage() + " <-");
                            error.printStackTrace();
                            Log.e("ServiceCall", error.toString());
                            System.out.println();

                        }
                    });
        System.out.println(jsonObjReq.getBodyContentType());
            VolleySingleton.getInstance(TrajetActivity.this).addToRequestQueue(jsonObjReq);

        }

    public JSONObject createTrajet() {
        Trajet trajet = new Trajet();
        trajet.setVilleDep(activityHelper.spinnerText(villeDepart));
        trajet.setVilleArr(activityHelper.spinnerText(villeDarrivée));
//        trajet.setDate_dep(departDate.getDate());
//        trajet.setDate_arr(arriveeDate.getDate());
        //trajet.setHeure_dep(timedepart);
        // trajet.setHeure_arr(timeArrivee);

        JSONObject json = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String string = gson.toJson(trajet);
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

    public int checkDateParam() {
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

    public void lancerTrajet(View view) {
        if (checkDateParam() == 1) {
            JSONObject param = createTrajet();
            try {
                param.put("id_c", 1);

                param.put("date_dep", departDate.getDate().getTime() / 1000);
                param.put("date_arr", arriveeDate.getDate().getTime() / 1000);
                param.put("heure_dep", timedepartString);
                param.put("heure_arr", timeArriveeString);
                System.out.println("ha param json kfita " + param);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, url.urlAddNewTrajet
                    , param,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                int result = response.getInt("result");
                                if (result == 1) {
                                //    int idTrajet = response.getInt("id");

                                }

                                String message = response.getString("message");
                                Toast.makeText(TrajetActivity.this, message, Toast.LENGTH_SHORT).show();
                                //   Intent myIntent = new Intent(this, MenuExpedit.class);


                                //startActivity(myIntent);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            System.out.println("Well done");
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
                                Toast.makeText(TrajetActivity.this,
                                        "Oops. Timeout error!" + error.getMessage(),


                                        Toast.LENGTH_LONG).show();
                                System.out.println(error.getStackTrace() + "" + error.getMessage());

                            }
                        }
                    });

            jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                    100000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(TrajetActivity.this).addToRequestQueue(jsonObjReq);

        }
    }
}