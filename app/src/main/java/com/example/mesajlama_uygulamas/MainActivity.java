package com.example.mesajlama_uygulamas;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    static ListView ls;
    static InputStream is = null;
    public JSONparcalama jsp = new JSONparcalama();
    public listeleme ws = new listeleme();
    List<String> veriler = new ArrayList<String>();
    JSONArray ja = null;
    JSONObject js = null;
    EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.yenile);
        ls = (ListView) findViewById(R.id.listem);
        ed = (EditText) findViewById(R.id.isim);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new listeleme().execute("http://sadakatsizcpre.tr.ht/getir.php");
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, com.example.mesajlama_uygulamas.listeleme.veriler);
                ls.setAdapter(adapter);

            }
        });
        Button b2 = (Button) findViewById(R.id.ekle);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String degisken = ed.getText().toString();
                new ekle().execute("http://sadakatsizcpre.tr.ht/ekle.php", degisken);
                Toast.makeText(MainActivity.this, ed.getText() + " eklendi.", Toast.LENGTH_SHORT).show();
                ed.setText("");

            }
        });
    }



}
