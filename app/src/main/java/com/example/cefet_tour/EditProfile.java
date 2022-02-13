package com.example.cefet_tour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class EditProfile extends AppCompatActivity {

    Button AvailabeTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        WebView webView = findViewById(R.id.WebView);
        webView.loadUrl("https://locauto.projetoscomputacao.com.br/EditProfileAPI.php");

        AvailabeTrips = findViewById(R.id.AvailabeTrips);
        AvailabeTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TravelsAvailable.class);
                startActivity(intent);
                finish();
            }
        });
    }
}