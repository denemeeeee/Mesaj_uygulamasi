package com.example.mesajlama_uygulamas;

import com.example.mesajlama_uygulamas.MainActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



class listeleme extends AsyncTask<String, String, List<String>> {

    static InputStream is = null;
    public JSONparcalama jsp = new JSONparcalama();
    static List<String> veriler = new ArrayList<String>();
    JSONArray ja = null;
    JSONObject js = null;



    protected List<String> doInBackground(String... params) {
        List<NameValuePair> sendParams = new ArrayList<NameValuePair>();
        JSONObject myObject = jsp.makeHttpRequest(params[0], "POST", sendParams);
        try {
            veriler.clear();
            JSONArray tempArray = myObject.getJSONArray("donenVeriler");
            for (int i = 0; i < tempArray.length(); i++) {
                veriler.add(tempArray.getJSONObject(i).getString("tag"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return veriler;
    }

    protected void onPostExecute(List<String> s) {

    }
}

class ekle extends AsyncTask<String, String, String> {
    ListView ls;
    static InputStream is = null;
    public JSONparcalama jsp = new JSONparcalama();
    List<String> veriler = new ArrayList<String>();
    JSONArray ja = null;
    JSONObject js = null;
    EditText ed;

    protected String doInBackground(String... params) {
        List<NameValuePair> paramss = new ArrayList<>();
        paramss.add(new BasicNameValuePair("eklenecekVeri", params[1]));
        JSONObject myObject = jsp.makeHttpRequest(params[0], "POST", paramss);
        return "";
    }
}