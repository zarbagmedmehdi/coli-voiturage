package com.application.zarbagaskazay.colivoiturage;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import WebService.util.UrlClass;
import helper.HashageUtil;

public class ConnexionActivity extends AppCompatActivity {
    EditText emailInput, passwordInput;
    Button login;
    String email, password;
    UrlClass url = new UrlClass();
    ActivityHelper activityHelper = new ActivityHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        emailInput = findViewById(R.id.input_email);
        passwordInput = findViewById(R.id.input_password);
        login = findViewById(R.id.btn_login);
    }


    public int collectData() {
        email = activityHelper.getStr(emailInput);


        password = activityHelper.getStr(passwordInput);
        if ((email != null && password != null) & (email != "" && password != "")) {

            return 1;
        } else return -1;


    }

    public void injectJson(JSONObject params) {
        try {
            params.put("email", email);
            params.put("password", HashageUtil.sha256(password));
            System.out.println(email + "injectJson" + password);
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println(" haaaaaa l'erreuur lwaaaaaaaaaaaaaaaaal ");

        }

    }

    public void verifier(View view) {
        if (collectData() == 1) {
            JSONObject params = new JSONObject();

            try {

                params.put("email", email);
                params.put("passowrd", password);
                System.out.println(email + "" + password);


            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url.urlConnexion
                    , params,
                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                int result = response.getInt("result");
                                String message = response.getString("message");
                                System.out.println(message + "" + result);
                                Toast.makeText(ConnexionActivity.this, "cc ha lmessage" + message, Toast.LENGTH_SHORT).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                                System.out.println("JSONException");
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ConnexionActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            System.out.println("onerrorMessage");
                        }
                    });

            VolleySingleton.getInstance(ConnexionActivity.this).addToRequestQueue(jsonObjReq);
        }

    }


}
