package com.example.cefet_tour;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class MainActivity extends AppCompatActivity {

    private TextInputLayout UserLoginEmailInput;
    private TextInputLayout  UserLoginPasswordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button CreateAccount = (Button) findViewById(R.id.CreateAccount);
        Button AccessAccount = (Button) findViewById(R.id.Login);

        UserLoginEmailInput = findViewById(R.id.UserLoginEmailInput);
        UserLoginPasswordInput = findViewById(R.id.UserLoginPasswordInput);

        CreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreateAccount.class));
            }
        });

        EditText LoginEmailInput = (EditText) findViewById(R.id.LoginEmailInput);
        EditText LoginPasswordInput = (EditText) findViewById(R.id.LoginPasswordInput);
        int Input_Size = 1;

        AccessAccount.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {

                if (LoginEmailInput.getText().length() < Input_Size) {
                    LoginEmailInput.setError("E-mail inválido.");
                } else if (LoginPasswordInput.getText().length() < Input_Size) {
                    LoginPasswordInput.setError("Senha inválida.");
                } else {
                    //Login permitido
                    AccessAccount.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final String  Senha, email;
                            email = String.valueOf(UserLoginEmailInput.getEditText().getText());
                            Senha = String.valueOf(UserLoginPasswordInput.getEditText().getText());
                            if(!email.equals("") && !Senha.equals("")){
                                Handler handler = new Handler();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        String field[] = new String[2];
                                        field [0] = "email";
                                        field [1] = "Senha";
                                        String[] data = new String[2];
                                        data[0] = email;
                                        data[1] = Senha;
                                        PutData putData = new PutData("https://locauto.projetoscomputacao.com.br/loginAPI.php", "POST", field, data);
                                        if(putData.startPut()){
                                            if(putData.onComplete()) {
                                                String result = putData.getResult();
                                                if (result.equals("SUCESSO NO LOGIN!")) {
                                                    //Log.i("ENTROU1", "ENTROU1");
                                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(getApplicationContext(), EditProfile.class);
                                                    startActivity(intent);
                                                    finish();
                                                } else {
                                                    //Log.i("ENTROU2", "ENTROU2");
                                                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }
                                    }
                                });
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"FALHA NA VERIFICAÇÃO LOGIN", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}

