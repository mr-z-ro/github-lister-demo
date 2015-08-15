package com.mattgrasser.githubrepolister;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by matt on 8/15/15.
 */
public class GithubService {
    private static final String BASE_URL = "https://api.github.com";

    public static List<GithubRepo> listRepos(String username) {
        String url = BASE_URL + "/users/" + username + "/repos";
        JSONArray responseJsonArr = getJson(url);
        return GithubRepo.createList(responseJsonArr);
    }

    protected static JSONArray getJson(String requestURL)
    {
        JSONArray jsonResponse = null;

        try {
            HttpClient httpclient = new DefaultHttpClient();

            //Initialize HTTP response and string
            HttpResponse response = null;

            // Create a new HttpClient
            HttpGet request = new HttpGet(requestURL);
            // Set headers and entity
            request.setHeader("Accept", "application/json");
            request.setHeader("Content-type", "application/json");
            // Execute the request
            response = httpclient.execute(request);

            // Convert to string, then JSONObject
            String txtResult = resultToString(response);
            jsonResponse = new JSONArray(txtResult);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            Log.d("API", "printStackTrace error " + e);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("API", "IOException error " + e);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("API", "JSONException error " + e);
        }
        return jsonResponse;
    }

    public static String resultToString(HttpResponse response) {
        String result = "";
        try{
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();
        }catch(Exception ex){
            result = "Error";
        }
        return result;
    }
}
