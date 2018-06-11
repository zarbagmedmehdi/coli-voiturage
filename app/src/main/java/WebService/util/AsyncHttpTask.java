package WebService.util;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {
    private static final String TAG = "Http Connection";

    private ListView listView = null;

    private ArrayAdapter arrayAdapter = null;

    private String[] blogTitles;
    @Override
    protected Integer doInBackground(String... params) {
        InputStream inputStream = null;

        HttpURLConnection urlConnection = null;

        Integer result = 0;
        try {
            /* forming th java.net.URL object */
            URL url = new URL(params[0]);

            urlConnection = (HttpURLConnection) url.openConnection();

            /* optional request header */
            urlConnection.setRequestProperty("Content-Type", "application/json");

            /* optional request header */
            urlConnection.setRequestProperty("Accept", "application/json");

            /* for Get request */
            urlConnection.setRequestMethod("GET");

            int statusCode = urlConnection.getResponseCode();

            /* 200 represents HTTP OK */
            if (statusCode ==  200) {

                inputStream = new BufferedInputStream(urlConnection.getInputStream());

                String response = convertInputStreamToString(inputStream);

                parseResult(response);

                result = 1; // Successful

            }else{
                result = 0; //"Failed to fetch data!";
            }

        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }

        return result; //"Failed to fetch data!";
    }


    @Override
    protected void onPostExecute(Integer result) {
       Activity activity =new Activity();

        if(result == 1){

            arrayAdapter = new ArrayAdapter(activity, android.R.layout.simple_list_item_1, blogTitles);

            listView.setAdapter(arrayAdapter);
        }else{
            Log.e(TAG, "Failed to fetch data!");
        }
    }



    private String convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

        String line = "";
        String result = "";

        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

        /* Close Stream */
        if(null!=inputStream){
            inputStream.close();
        }

        return result;
    }
    private void parseResult(String result) {

        try{
            JSONObject response = new JSONObject(result);

            JSONArray posts = response.optJSONArray("posts");

            blogTitles = new String[posts.length()];

            for(int i=0; i< posts.length();i++ ){
                JSONObject post = posts.optJSONObject(i);
                String title = post.optString("title");

                blogTitles[i] = title;
            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }}