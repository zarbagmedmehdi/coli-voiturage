package com.application.zarbagaskazay.colivoiturage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import WebService.util.UrlClass;
import bean.User;
import helper.HashageUtil;
import service.UserService;


public class InscriptionActivity extends AppCompatActivity {

    private ImageButton button, conducteurButton, expediteurButton;
    private EditText telephone, password1, password2, email, prenom, nom;
    private TextView nameText;
    String urlInscription;
    Bundle myBundle = new Bundle();
    ActivityHelper activityHelper = new ActivityHelper();
    UrlClass urlClass = new UrlClass();
    private User user;
    UserService userService = new UserService();
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription1);
        prenom = findViewById(R.id.prenomInput);
        nom = findViewById(R.id.nomInput);
        prenom = findViewById(R.id.prenomInput);
        email = findViewById(R.id.emailInput);
        telephone = findViewById(R.id.telephoneInput);
        password1 = findViewById(R.id.passwordInput1);
        password2 = findViewById(R.id.passwordInput2);
        button = findViewById(R.id.inscription);


    }

    public void inscription(View v) {


        /*Json Request*/

        String url = "http://192.168.1.10/api/api/all/inscription";
        JSONObject params = new JSONObject();

        try {
            params.put("email", getStr(email));
            params.put("password", HashageUtil.sha256(getStr(password1)));
            params.put("telephone", getStr(nom));
            params.put("prenom", getStr(prenom));
            params.put("nom", getStr(nom));
            final int lop;
            switch (v.getId()) {
                case R.id.conducteurButton:
                    params.put("type", 1);
                    lop = 1;
                    break;
                case R.id.expediteurButton:
                    params.put("type", 0);
                    lop = 0;
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, urlClass.urlInscription
                , params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int result = response.getInt("result");
                            int type = response.getInt("type");
                            int id = response.getInt("id");
                            String message = response.getString("message");
                            System.out.println(message);
                            Toast.makeText(InscriptionActivity.this, message, Toast.LENGTH_SHORT).show();
                            if (result == 1) {
                                switch (type) {
                                    case 1:
                                        ConducteurInscriptionActivity activity = new ConducteurInscriptionActivity();
                                        startVoitureAjout(id);
                                        break;
                                    case 0:
                                        break;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("JSONException");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(InscriptionActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println("onerrorMessage");
                    }
                });

        VolleySingleton.getInstance(InscriptionActivity.this).addToRequestQueue(jsonObjReq);

    }

    private void replaceParam() {

        setStr(nom, user.getNom());
        setStr(prenom, user.getNom());
        setStr(email, user.getEmail());
        setStr(telephone, user.getEmail());
    }

    private void clear(int n) {
        if (n == -1) {
            nom.setText(null);
            prenom.setText(null);
            email.setText(null);
            telephone.setText(null);
            password1.setText(null);
            password2.setText(null);
        }
        if (n == -2) {
            password1.setText(null);
            password2.setText(null);

        }
        if (n == -3) {

            password1.setText(null);
            password2.setText(null);
        }
        if (n == -4) {
            telephone.setText(null);
        }

    }

    public String getStr(EditText editText) {
        String string = editText.getText().toString();
        return string;

    }

    public void setStr(EditText editText, String string) {
        editText.setText(string);


    }

    public void forward(View view) {
        if (verifyYourData() == 1) {
            setContentView(R.layout.inscription2);
            conducteurButton = findViewById(R.id.conducteurButton);
            expediteurButton = findViewById(R.id.expediteurButton);
        } else clear(verifyYourData());
    }


    public void back(View view) {

        setContentView(R.layout.inscription1);
        replaceParam();


    }


    public int verifyYourData() {
        Object[] object = userService.message(getStr(telephone), getStr(password1), getStr(password2), getStr(email));
        String message = (String) object[1];
        int result = (int) object[0];
        if (result != 1) {
            clear(result);
            Toast.makeText(InscriptionActivity.this, message, Toast.LENGTH_SHORT).show();

        }
        return result;
    }

    public void startVoitureAjout(int id) {
        Intent myIntent = new Intent(this, ConducteurInscriptionActivity.class);

        myIntent.putExtra("id", id);

        startActivity(myIntent);

    }
}

