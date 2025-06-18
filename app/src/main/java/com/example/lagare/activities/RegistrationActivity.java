package com.example.lagare.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.lagare.activities.WelcomeActivity;
import com.example.lagare.MainActivity;
import com.example.lagare.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    // Declarar variables para los EditText y el botón
    EditText name, paternalSurname, maternalSurname, email, password, phone, address, streetNumber, postalCode, references;
    // Declarar objetos de Firebase
    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Inicializar objetos de Firebase
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        // Obtener referencias a los EditText utilizando los IDs que asignaste en el XML
        name = findViewById(R.id.editText1); // Reemplaza con el ID correcto si no es editText_name
        paternalSurname = findViewById(R.id.editText_paternal_surname);
        maternalSurname = findViewById(R.id.editText_maternal_surname);
        email = findViewById(R.id.editText_email);
        password = findViewById(R.id.editText_password);
        phone = findViewById(R.id.editText_phone);
        address = findViewById(R.id.editText_address);
        streetNumber = findViewById(R.id.editText_street_number);
        postalCode = findViewById(R.id.editText_postal_code);
        references = findViewById(R.id.editText_references);
    }

    // Método llamado cuando se hace clic en el botón de registro (si tienes android:onClick="register" en tu botón)
    public void register(View view) {
        // Obtener los datos ingresados por el usuario
        String userName = name.getText().toString();
        String userPaternalSurname = paternalSurname.getText().toString();
        String userMaternalSurname = maternalSurname.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();
        String userPhone = phone.getText().toString();
        String userAddress = address.getText().toString();
        String userStreetNumber = streetNumber.getText().toString();
        String userPostalCode = postalCode.getText().toString();
        String userReferences = references.getText().toString();


        // Validar los datos (puedes agregar más validaciones aquí)
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Ingresa tu nombre!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Ingresa tu correo electrónico!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Ingresa tu contraseña!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (userPassword.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear usuario con Firebase Authentication
        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Si el usuario se crea correctamente, guardar información adicional en Realtime Database
                            String uid = auth.getCurrentUser().getUid();
                            HashMap<String, String> userData = new HashMap<>();
                            userData.put("name", userName);
                            userData.put("paternalSurname", userPaternalSurname);
                            userData.put("maternalSurname", userMaternalSurname);
                            userData.put("email", userEmail);
                            userData.put("phone", userPhone);
                            userData.put("address", userAddress);
                            userData.put("streetNumber", userStreetNumber);
                            userData.put("postalCode", userPostalCode);
                            userData.put("references", userReferences);

                            database.getReference().child("Users").child(uid).setValue(userData)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(RegistrationActivity.this, "Registro exitoso!", Toast.LENGTH_SHORT).show();
                                                // Navegar a la siguiente actividad (por ejemplo, la actividad principal)
                                                startActivity(new Intent(RegistrationActivity.this, WelcomeActivity.class));
                                                finish(); // Finalizar esta actividad para que el usuario no pueda regresar presionando "atrás"
                                            } else {
                                                Toast.makeText(RegistrationActivity.this, "Error al guardar datos de usuario: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        } else {
                            // Si ocurre un error al crear el usuario (ej. correo ya registrado)
                            Toast.makeText(RegistrationActivity.this, "Error al registrar usuario: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    // Método para ir a la actividad de inicio de sesión
    public void login(View view) {
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }

    // Eliminar o modificar este método si no lo necesitas para el botón de registro
    // Si quieres que el botón de registro llame al método 'register', debes cambiar android:onClick="mainActivity" a android:onClick="register" en activity_registration.xml
    public void mainActivity(View view) {
        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
    }
}