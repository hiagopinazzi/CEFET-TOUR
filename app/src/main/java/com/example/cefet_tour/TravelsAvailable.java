package com.example.cefet_tour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class TravelsAvailable extends AppCompatActivity {

    String urladdress="https://locauto.projetoscomputacao.com.br/ListaViagensAPI.php";
    String[] id;
    String[] viagem;

    ListView listView;
    BufferedInputStream is;
    String line=null;
    String result=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travels_available);

        ListView  listView = findViewById(R.id.listView);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collecData();
        CustomListView customListView=new CustomListView(this,id,viagem);
        listView.setAdapter(customListView);

    }
    private void collecData(){

        try{
            URL url = new URL(urladdress);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            is=new BufferedInputStream(conn.getInputStream());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        try{
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            while((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

        try{
            JSONArray ja=new JSONArray(result);
            JSONObject jo=null;
            id = new String[ja.length()];
            viagem = new String[ja.length()];

            for(int i=0;i<=ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                id[i] = jo.getString("id");
                viagem[i] = jo.getString("viagem");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}