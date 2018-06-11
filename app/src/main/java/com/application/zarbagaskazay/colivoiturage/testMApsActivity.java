package com.application.zarbagaskazay.colivoiturage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import WebService.util.UrlClass;

public class testMApsActivity extends AppCompatActivity {
EditText villeone;
    EditText villetwo;
    Button button ;
    UrlClass urlClass=new UrlClass();
    private final String  apiKeyDistance="AIzaSyDIlEpPQKYKVwjarpi_xJnEXNirg-qkSa4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_maps);
         villeone=(EditText)findViewById(R.id.ville1);
         villetwo=(EditText)findViewById(R.id.ville2);
         button =(Button)findViewById(R.id.button77);
    }


    public void afficher(View view) {
        String v1=villeone.getText().toString()+"+morocco";
        String v2=villetwo.getText().toString()+"+morocco";
String urlm=makeURL(v1,v2);
    //    JSONObject params = new JSONObject();

   //     try {

        //    params.put("origins", v1);
          //  params.put("destinations", v2);
         // params.put("key",apiKeyDistance);
          //  System.out.println(params);

    //    } catch (JSONException e) {
      //      e.printStackTrace();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, urlm
                , null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                    JSONArray rows = response.getJSONArray("rows");
                            JSONObject elements=rows.getJSONObject(0);
                      JSONObject distance=    elements.getJSONArray("elements").getJSONObject(0).getJSONObject("distance");
                                String text=distance.getString("text").substring(0,3);
Double distanceD=Double.parseDouble(text);
                            button.setText(distanceD.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }




                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(testMApsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println("onerrorMessage"+error.getMessage()+error.getLocalizedMessage());
                    }
                });

        VolleySingleton.getInstance(testMApsActivity.this).addToRequestQueue(jsonObjReq);
    }
    public String makeURL (String ville1, String  ville2 ){
        StringBuilder urlString = new StringBuilder();
        urlString.append("https://maps.googleapis.com/maps/api/distancematrix/json");
        urlString.append("?origins=");// from
        urlString.append(ville1);

        urlString.append("&destinations=");// to
        urlString.append(ville2);

       // urlString.append("&sensor=false&mode=driving&alternatives=true");
        urlString.append("&key="+apiKeyDistance);
        System.out.println(urlString.toString());
        return urlString.toString();

    }

}
