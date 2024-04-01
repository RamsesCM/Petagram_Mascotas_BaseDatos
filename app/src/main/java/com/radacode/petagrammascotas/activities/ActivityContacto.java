package com.radacode.petagrammascotas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.radacode.petagrammascotas.R;

public class ActivityContacto extends AppCompatActivity {

    EditText nombre,correo,mensaje;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contacto);

        nombre = (EditText) findViewById(R.id.editNombre);
        correo = (EditText) findViewById(R.id.editCorreo);
        mensaje = (EditText) findViewById(R.id.editMensaje);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreE, correoE, mensajeE;
                nombreE = nombre.getText().toString();
                correoE = correo.getText().toString();
                mensajeE = mensaje.getText().toString();
                if(nombreE.isEmpty() || correoE.isEmpty() || mensajeE.isEmpty()){
                    Toast.makeText(ActivityContacto.this, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                }
                else{
                    sendEmail(nombreE, correoE, mensajeE);
                }
            }
        });

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar2);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void sendEmail(String nombre, String correo, String mensaje){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{correo});
        intent.putExtra(intent.EXTRA_SUBJECT, nombre);
        intent.putExtra(Intent.EXTRA_TEXT, mensaje);

        intent.setType("messaje/rfc822");
        startActivity(Intent.createChooser(intent, "Choose email client:"));
    }
}