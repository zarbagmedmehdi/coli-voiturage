package helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;


public class Dispacher {

    public static void forward(Context context, Class distination) {
        Intent intent = new Intent(context,distination);
        context.startActivity(intent);
    }
    public String getString(String key, JSONObject jsonObject) {
        try {
            String value=  jsonObject.getString(key);
            return value ;
        } catch (JSONException e) {
            e.printStackTrace();
            return null ;
        }

    }
    public Double getDouble(String key, JSONObject json) {
        double w=-1;
        try {
            try {
                w = new Double(json.get(key).toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException e) {
            w = 0; // your default value
        }
        System.out.println(w);
        return  w;
    }





}
