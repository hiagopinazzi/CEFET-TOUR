package com.example.cefet_tour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class TravelCreation extends AppCompatActivity {

    TextInputLayout TravelNameInput, TravelInitDateInput, TravelInitTimeInput,TravelInitLocalInput,TravelDestinationDateInput;
    TextInputLayout  TravelDestinationTimeInput, TravelDestinationInput,TravelRouteInput,TravelAccommodationPlaceInput;
    Button ConfirmTravelCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_create);

        TravelNameInput = findViewById(R.id.TravelNameInput);
        TravelInitDateInput = findViewById(R.id.TravelInitDateInput);
        TravelInitTimeInput = findViewById(R.id.TravelInitTimeInput);
        TravelInitLocalInput = findViewById(R.id.TravelInitLocalInput);
        TravelDestinationDateInput = findViewById(R.id.TravelDestinationDateInput);
        TravelDestinationTimeInput = findViewById(R.id.TravelDestinationTimeInput);
        TravelDestinationInput = findViewById(R.id.TravelDestinationInput);
        TravelRouteInput = findViewById(R.id.TravelRouteInput);
        /*RadioButton Yes = (RadioButton) findViewById(R.id.AccommodationYES);
        RadioButton No = (RadioButton) findViewById(R.id.AccomodationNO);
        RadioButton MenorSim = (RadioButton) findViewById(R.id.AllowMinorYES);
        RadioButton MenorNao = (RadioButton) findViewById(R.id.AllowMinorNO);*/
        TravelAccommodationPlaceInput = findViewById(R.id.TravelAccommodationPlaceInput);
        ConfirmTravelCreation = findViewById(R.id.ConfirmTravelCreation);

        ConfirmTravelCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String viagem, dataembarque, horarioembarque, localembarque, datadesembarque, horariodesembarque, destino,roteiro, hospedagemsim, hospedagemnao,localhospesagem,menoressim,menoresnao;
                viagem = String.valueOf(TravelNameInput.getEditText().getText());
                dataembarque = String.valueOf(TravelInitDateInput.getEditText().getText());
                horarioembarque = String.valueOf(TravelInitTimeInput.getEditText().getText());
                localembarque = String.valueOf(TravelInitLocalInput.getEditText().getText());
                datadesembarque = String.valueOf(TravelDestinationDateInput.getEditText().getText());
                horariodesembarque = String.valueOf(TravelDestinationTimeInput.getEditText().getText());
                destino = String.valueOf(TravelDestinationInput.getEditText().getText());
                roteiro = String.valueOf(TravelRouteInput.getEditText().getText());
                localhospesagem = String.valueOf(TravelAccommodationPlaceInput.getEditText().getText());

                if(!viagem.equals("") && !dataembarque.equals("") && !horarioembarque.equals("")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[9];
                            field[0] = "viagem";
                            field[1] = "dataembarque";
                            field[2] = "horarioembarque";
                            field[3] = "localembarque";
                            field[4] = "datadesembarque";
                            field[5] = "horariodesembarque";
                            field[6] = "destino";
                            field[7] = "roteiro";
                            field[8] = "localhospesagem";
                            //Creating array for data
                            String[] data = new String[9];
                            data[0] = viagem;
                            data[1] = dataembarque;
                            data[2] = horarioembarque;
                            data[3] = localembarque;
                            data[4] = datadesembarque;
                            data[5] = horariodesembarque;
                            data[6] = destino;
                            data[7] = roteiro;
                            data[8] = localhospesagem;

                            PutData putData = new PutData("https://locauto.projetoscomputacao.com.br/InsereViagem.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    if (result.equals("SUCESSO NO CADASTRO!")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),EditProfile.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"UM OU MAIS CAMPO EM BRANCO",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}