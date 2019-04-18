package com.example.mesajlama_uygulamas;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ListView ls;
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

    class listeleme extends AsyncTask<String, String, List<String>> {
        public JSONparcalama jsp = new JSONparcalama();
        List<String> veriler = new ArrayList<String>();


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
            ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, s);
            ls.setAdapter(adapter);
        }
    }


}
