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

    String urladress = "http://locauto.projetoscomputacao.com.br/ListaViagensAPI.php";
    String[] id;
    String[] viagem;
    /*String[] dataembarque;
    String[] horarioembarque;
    String[] localembarque;
    String[] datadesembarque;
    String[] horariodesembarque;
    String[] destino;
    String[] roteiro;
    String[] localhospedagem;
    String[] hospedagembd;
    String[] menoresbd;*/

    ListView listView;
    BufferedInputStream is;
    String line=null;
    String result=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travels_available);

        listView=(ListView)findViewById(R.id.lview);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        collecData();
        CustomListView customListView=new CustomListView(this,id,viagem/*,dataembarque,horarioembarque,localembarque,datadesembarque,horariodesembarque,destino,roteiro,localhospedagem,hospedagembd,menoresbd*/);
        listView.setAdapter(customListView);

    }
    private void collecData(){

        try{
            URL url = new URL(urladress);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());
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
            /*dataembarque = new String[ja.length()];
            horarioembarque = new String[ja.length()];
            localembarque = new String[ja.length()];
            datadesembarque = new String[ja.length()];
            horariodesembarque = new String[ja.length()];
            destino = new String[ja.length()];
            roteiro = new String[ja.length()];
            localhospedagem = new String[ja.length()];
            hospedagembd = new String[ja.length()];
            menoresbd = new String[ja.length()];*/

            for(int i=0;i<=ja.length();i++)
            {
                jo=ja.getJSONObject(i);
                id[i] = jo.getString("id");
                viagem[i] = jo.getString("viagem");
                /*dataembarque[i] = jo.getString("dataembarque");
                horarioembarque[i] = jo.getString("horarioembarque");
                localembarque[i] = jo.getString("localembarque");
                datadesembarque[i] = jo.getString("datadesembarque");
                horariodesembarque[i] = jo.getString("horariodesembarque");
                destino[i] = jo.getString("destino");
                roteiro[i] = jo.getString("roteiro");
                localhospedagem[i] = jo.getString("localhospedagem");
                hospedagembd[i] = jo.getString("hospedagembd");
                menoresbd[i] = jo.getString("menoresbd");*/
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}