package com.example.shopfromhome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopfromhome.model.RegisterResponse;
import com.example.shopfromhome.network.ApiClient;
import com.example.shopfromhome.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName, editTextSurname, editTextNickname, editTextEmail, editTextPassword;
    private Button buttonRegister;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Collegamento con i campi di input
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextNickname = findViewById(R.id.editTextNickname);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Ottieni l'istanza di ApiService
        apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

        // Gestione del click sul pulsante di registrazione
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String name = editTextName.getText().toString();
        String surname = editTextSurname.getText().toString();
        String nickname = editTextNickname.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        // Verifica che i campi non siano vuoti
        if (name.isEmpty() || surname.isEmpty() || nickname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Compila tutti i campi", Toast.LENGTH_SHORT).show();
            return;
        }

        // Effettua la chiamata API per la registrazione
        Call<RegisterResponse> call = apiService.registerUser(name, surname, nickname, email, password);
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse != null && registerResponse.getSuccess()) {
                        // Registrazione avvenuta con successo, passa alla LoginActivity
                        Toast.makeText(RegisterActivity.this, "Registrazione avvenuta con successo", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registrazione fallita", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Errore nel server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                Log.e("RegisterActivity", "Errore: " + t.getMessage());
                Toast.makeText(RegisterActivity.this, "Errore di rete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
