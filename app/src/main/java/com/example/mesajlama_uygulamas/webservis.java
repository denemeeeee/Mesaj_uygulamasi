package com.example.mesajlama_uygulamas;

import com.example.mesajlama_uygulamas.MainActivity;

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

public class webservis {
    ListView ls;
    static InputStream is = null;
    public JSONparcalama jsp = new JSONparcalama();
    List<String> veriler = new ArrayList<String>();
    JSONArray ja = null;
    JSONObject js = null;
    EditText ed;


}

class listeleme extends AsyncTask<String, String, JSONObject> {
    ListView ls;
    static InputStream is = null;
    public JSONparcalama jsp = new JSONparcalama();
    List<String> veriler = new ArrayList<String>();
    JSONArray ja = null;
    JSONObject js = null;
    EditText ed;

    protected JSONObject doInBackground(String... params) {
        List<NameValuePair> sendParams = new ArrayList<NameValuePair>();
        JSONObject myObject = jsp.makeHttpRequest(params[0], "POST", sendParams);
        return myObject;
    }

    protected void onPostExecute(JSONObject s) {
        try {
            try {

                veriler.clear();
                JSONArray tempArray = s.getJSONArray("donenVeriler");
                for (int i = 0; i < tempArray.length(); i++) {
                    veriler.add(tempArray.getJSONObject(i).getString("tag"));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.class,android.R.layout.simple_list_item_1,veriler);
                ls.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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