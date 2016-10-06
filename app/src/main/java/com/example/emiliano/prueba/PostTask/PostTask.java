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
    final String urlServer = "http://positivemediaweb.com/GM/v0.1/";


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


                //System.out.println(parametros);
                //try {
                //    httppost.setEntity(new UrlEncodedFormEntity(parametros));
                //} catch (UnsupportedEncodingException e) {
                //    e.printStackTrace();
                //}
                //Initial request without credentials returns "HTTP/1.1 401 Unauthorized"
                //HttpResponse response = null;
                //try {
                //    response = httpclient.execute(httppost);
                //} catch (IOException e) {
                //   e.printStackTrace();
                //}
                //assert response != null;
                //System.out.println(response.getStatusLine());

                //if (response.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
                //    Header authHeader = response.getFirstHeader(AUTH.WWW_AUTH);
                //    System.out.println("authHeader = " + authHeader);
                //    DigestScheme digestScheme = new DigestScheme();
                    //Parse realm, nonce sent by server.
                //    digestScheme.processChallenge(authHeader);
                //    UsernamePasswordCredentials creds = new UsernamePasswordCredentials("admin", "mypass");
                //    httppost.addHeader(digestScheme.authenticate(creds, httppost));
                //    ResponseHandler<String> responseHandler = new BasicResponseHandler();
                    // String responseBody = null;
                //    try {
                //        String responseBody = httpclient2.execute(httppost, responseHandler);
                //        try {
                //            respJSON = new JSONArray(responseBody);
                            //  Log.d("JSON", respJSON.toString());

                //        } catch (Throwable t) {
                //            Log.e("JSON", "Could not parse malformed JSON: \"" + respJSON + "\"");
                //        }
                //    } catch (IOException e) {
                //        e.printStackTrace();
                //    }
                //}

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