package com.radacode.petagrammascotas.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.radacode.petagrammascotas.bd.BaseDatos;
import com.radacode.petagrammascotas.pojo.Mascota;
import com.radacode.petagrammascotas.adapter.MascotaAdapter;
import com.radacode.petagrammascotas.R;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    ArrayList<Mascota> ultimasCincoMascotas = new ArrayList<>();

    private RecyclerView listaMascotas;
    private MascotaAdapter adapter;
    private BaseDatos bd = new BaseDatos(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar2);
        setSupportActionBar(miActionBar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //rvMascotas
        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);
        iniciarlizarListaMascotas();
        inicializarAdaptador();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void iniciarlizarListaMascotas(){
        mascotas = new ArrayList<>();

        if(ultimasCincoMascotas.isEmpty()) {
            mascotas.add(new Mascota(R.drawable.p4, "Chimuelo", 12));
            mascotas.add(new Mascota(R.drawable.p4, "Chimuelo", 12));
            mascotas.add(new Mascota(R.drawable.p4, "Chimuelo", 12));
            mascotas.add(new Mascota(R.drawable.p4, "Chimuelo", 12));
            mascotas.add(new Mascota(R.drawable.p4, "Chimuelo", 12));
        } else{
            // Obtener el índice desde el cual comenzar a obtener los últimos cinco elementos
            int startIndex = Math.max(ultimasCincoMascotas.size() - 5, 0);

            // Iterar desde el índice "startIndex" hasta el final del ArrayList "otrosRegistros"
            for (int i = startIndex; i < ultimasCincoMascotas.size(); i++) {
                Mascota mascota = ultimasCincoMascotas.get(i);
                mascotas.add(mascota);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        inicializarAdaptador();
    }

    public void inicializarAdaptador(){
        adapter = new MascotaAdapter(mascotas);
        listaMascotas.setAdapter(adapter);
    }

    public void agregarUltimasMascotas(Mascota mascota){
        ultimasCincoMascotas.add(mascota);
        iniciarlizarListaMascotas();
        if (adapter != null) {
            adapter.notifyDataSetChanged(); // Notificar al adaptador sobre los cambios en los datos
        }
    }
}