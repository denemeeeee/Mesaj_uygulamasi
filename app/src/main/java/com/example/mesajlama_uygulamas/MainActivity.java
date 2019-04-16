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
    ListView ls;static InputStream is = null;
    List<String> veriler = new ArrayList<String>();
    JSONArray ja=null;JSONObject js=null;
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(R.id.yenile);
        ls =(ListView) findViewById(R.id.listem) ;
        ed = (EditText) findViewById(R.id.isim);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new listeleme().execute("http://sadakatsizcpre.tr.ht/getir.php");
            }
        });
        Button b2 = (Button) findViewById(R.id.ekle);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String degisken = ed.getText().toString();
                new ekle().execute("http://sadakatsizcpre.tr.ht/ekle.php",degisken);
                ed.setText("");


            }
        });
    }
    class listeleme extends AsyncTask<String,String,String>{
        protected String doInBackground(String ... params) {
        HttpURLConnection connection = null;
        String dosya = "";
        BufferedReader br = null;
        try {
            URL url = new URL(params[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            String satir;
            while ((satir = br.readLine()) != null) {
                Log.d("satır", satir);
                dosya += satir;
            }
            return dosya;


        } catch (Exception e) {
            e.printStackTrace();
            return dosya;
        }
        }
        protected void onPostExecute(String s){
            Log.d("postextengelenmesaj",s);
            try{
                veriler.clear();
                js = new JSONObject(s);
                ja = js.getJSONArray("donenVeriler");

                for(int i=0;i<ja.length();i++){
                    veriler.add(ja.getJSONObject(i).getString("tag"));
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,veriler);
                ls.setAdapter(adapter);
            }catch (Exception e){e.printStackTrace();}
        }
    }


    class ekle extends AsyncTask<String,String,String>{
        protected String doInBackground(String ... params){
            List<NameValuePair> paramss = new ArrayList<>();
            paramss.add(new BasicNameValuePair("eklenecekVeri", params[1]));
            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(params[0]);
                httpPost.setEntity(new UrlEncodedFormEntity(paramss));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                is = httpEntity.getContent();
            }catch (Exception e){e.printStackTrace();}
            return"";
        }
    }


}
