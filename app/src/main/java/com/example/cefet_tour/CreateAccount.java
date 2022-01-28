package com.example.cefet_tour;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.cefet_tour.databinding.ActivityCreateAccountBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class CreateAccount extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityCreateAccountBinding binding;

    TextInputLayout  UserNameInput, UserCPFInput, UserPhoneInput,UserBirthdayInput,UserPasswordConfirmationInput;
    TextInputLayout  UserEmailInput, UserRegistrationInput,UserAddressInput,UserPasswordInput;
    Button ConfirmAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        UserNameInput = findViewById(R.id.UserNameInput);
        UserCPFInput = findViewById(R.id.UserCPFInput);
        UserPhoneInput = findViewById(R.id.UserPhoneInput);
        UserPasswordConfirmationInput = findViewById(R.id.UserPasswordConfirmationInput);
        UserEmailInput = findViewById(R.id.UserEmailInput);
        UserRegistrationInput = findViewById(R.id.UserRegistrationInput);
        UserAddressInput = findViewById(R.id.UserAddressInput);
        UserBirthdayInput = findViewById(R.id.UserBirthdayInput);
        UserPasswordInput = findViewById(R.id.UserPasswordInput);
        ConfirmAccount = findViewById(R.id.ConfirmAccount);

        ConfirmAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname, email, ehOrganizador, Cpf, DataNasc, Senha, Resenha, Endereco, Telefone, Matricula;
                fullname = String.valueOf(UserNameInput.getEditText().getText());
                //Log.i("TECNOLOGIA","TESTER"+fullname);
                email = String.valueOf(UserEmailInput.getEditText().getText());
                ehOrganizador = String.valueOf(0);
                Cpf = String.valueOf(UserCPFInput.getEditText().getText());
                DataNasc = String.valueOf(UserBirthdayInput.getEditText().getText());
                Senha = String.valueOf(UserPasswordInput.getEditText().getText());
                Resenha = String.valueOf(UserPasswordConfirmationInput.getEditText().getText());
                Endereco = String.valueOf(UserAddressInput.getEditText().getText());
                Telefone = String.valueOf(UserPhoneInput.getEditText().getText());
                Matricula = String.valueOf(UserRegistrationInput.getEditText().getText());

                if(!fullname.equals("") && !email.equals("") && !Senha.equals("")) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[10];
                            field[0] = "fullname";
                            field[1] = "ehOrganizador";
                            field[2] = "Resenha";
                            field[3] = "Endereco";
                            field[4] = "Telefone";
                            field[5] = "Cpf";
                            field[6] = "DataNasc";
                            field[7] = "Matricula";
                            field[8] = "email";
                            field[9] = "Senha";
                            //Creating array for data
                            String[] data = new String[10];
                            data[0] = fullname;
                            data[1] = ehOrganizador;
                            data[2] = Resenha;
                            data[3] = Endereco;
                            data[4] = Telefone;
                            data[5] = Cpf;
                            data[6] = DataNasc;
                            data[7] = Matricula;
                            data[8] = email;
                            data[9] = Senha;
                            PutData putData = new PutData("https://locauto.projetoscomputacao.com.br/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    if (result.equals("SUCESSO!")) {
                                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
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

        final RadioButton User_Radio_Option = (RadioButton)findViewById(R.id.UserType2Input);
        final RadioButton Organizer_Radio_Option = (RadioButton)findViewById(R.id.UserType1Input);
        Button Exit_Account_Creation = (Button)findViewById(R.id.Exit_Account_Creation);

        User_Radio_Option.setChecked(true);

        User_Radio_Option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Organizer_Radio_Option.setChecked(false);
                Log.i("TEC","OK");
            }
        });

        Organizer_Radio_Option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User_Radio_Option.setChecked(false);
            }
        });

        Exit_Account_Creation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateAccount.this, MainActivity.class));
            }
        });
    }
}