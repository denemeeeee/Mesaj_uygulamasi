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

class ekle extends AsyncTask<String, String, String> {
    public JSONparcalama jsp = new JSONparcalama();
    protected String doInBackground(String... params) {
        List<NameValuePair> paramss = new ArrayList<>();
        paramss.add(new BasicNameValuePair("eklenecekVeri", params[1]));
        JSONObject myObject = jsp.makeHttpRequest(params[0], "POST", paramss);
        return "";
    }
}