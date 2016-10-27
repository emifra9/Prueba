package com.example.emiliano.prueba.PostTask;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by leo on 02/06/16.
 */
public class PostTask extends AsyncTask< String, String, JSONArray> {
    private Activity activity;
    private String url;
    private String parametros;
    private AsyncTaskListener callback;
    final String urlServer = "http://positivemediaweb.com/FutbolManager/v0.1/";


    public PostTask(Activity act, String url, String parametros, AsyncTaskListener callback) {
        this.activity = act;
        this.callback = callback;
        this.url = url;
        this.parametros = parametros;
    }

    protected void onPreExecute() {

            }


    @Override
    protected JSONArray doInBackground(String... params) {
        JSONArray respJSON = null;

        String surl = urlServer+url;


        if(!isCancelled() && isOnline()) {
            try {
                URL url = new URL(surl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);

                DataOutputStream stream = new DataOutputStream(urlConnection.getOutputStream());
                stream.writeBytes(parametros);
                stream.flush();
                stream.close();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }
                bufferedReader.close();

                respJSON = new JSONArray(builder.toString());

              } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            Log.w("NO HAY INTERNET","");
            cancel(true);

        }
        return respJSON;
    }


    protected void onPostExecute(final JSONArray resultado) {

            callback.onTaskComplete(resultado);

            }

    public boolean isOnline() {
            ConnectivityManager cm =
            (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
            }
}