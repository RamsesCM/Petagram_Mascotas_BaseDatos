package com.radacode.petagrammascotas.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.radacode.petagrammascotas.pojo.Mascota;
import com.radacode.petagrammascotas.adapter.MascotaAdapterOurs;
import com.radacode.petagrammascotas.R;

import java.util.ArrayList;

public class FragmentMiMascota extends Fragment {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_mi_mascota, container, false);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMiMascota);

        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
        listaMascotas.setLayoutManager(glm);

        iniciarlizarListaMascotas();
        inicializarAdaptador();

        // Inflate the layout for this fragment
        return v;
    }

    public void iniciarlizarListaMascotas(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.p1, "Chimuelo",12));
        mascotas.add(new Mascota(R.drawable.p1, "Princeso",9));
        mascotas.add(new Mascota(R.drawable.p1, "Mateo",3));
        mascotas.add(new Mascota(R.drawable.p1, "Pablo",23));
        mascotas.add(new Mascota(R.drawable.p1, "Pugceso",5));
        mascotas.add(new Mascota(R.drawable.p1, "Pugceso",50));
        mascotas.add(new Mascota(R.drawable.p1, "Pablo",1));
        mascotas.add(new Mascota(R.drawable.p1, "Pugceso",0));
        mascotas.add(new Mascota(R.drawable.p1, "Pugceso",12));
    }

    public void inicializarAdaptador(){
        MascotaAdapterOurs adapter = new MascotaAdapterOurs(mascotas);
        listaMascotas.setAdapter(adapter);
    }
}