package com.radacode.petagrammascotas.fragment;

import com.radacode.petagrammascotas.adapter.MascotaAdapter;
import com.radacode.petagrammascotas.pojo.Mascota;

import java.util.ArrayList;

public interface iFragmentPrincipalView {

    public void generarLinearLayoutVertical();

    public MascotaAdapter crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdapter adaptador);

}
