package com.radacode.petagrammascotas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.radacode.petagrammascotas.activities.MainActivity2;
import com.radacode.petagrammascotas.bd.ConstructorMascotas;
import com.radacode.petagrammascotas.pojo.Mascota;
import com.radacode.petagrammascotas.R;

import java.util.ArrayList;

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder>{

    ArrayList<Mascota> mascotas;
    MainActivity2 mainActivity2 = new MainActivity2();

    public MascotaAdapter(ArrayList<Mascota> mascotas){
        this.mascotas = mascotas;
    }

    @NonNull
    @Override // Inflar el layout y lo que pasara al viewholder para que obtenga los views
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override // Asocia cada elementos de la lista con cada view
    public void onBindViewHolder(@NonNull MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgMascota.setImageResource(mascota.getFotoMascota());
        mascotaViewHolder.tvNombreCV.setText(mascota.getNombreMascota());
        mascotaViewHolder.tvLikesCV.setText(String.valueOf(mascota.getNumeroLikes()));

        mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Diste like a " + mascota.getNombreMascota(), Toast.LENGTH_SHORT).show();

                ConstructorMascotas constructorMascotas = new ConstructorMascotas(v.getContext());
                mainActivity2.agregarUltimasMascotas(mascota);

                constructorMascotas.darLikeMascota(mascota);
                mascotaViewHolder.tvLikesCV.setText(String.valueOf(constructorMascotas.obtenerLikesMascota(mascota)));
            }
        });
    }
    @Override
    public int getItemCount() { // Cantidad de elementos que contiene la lista
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgMascota;
        private TextView tvNombreCV;
        private TextView tvLikesCV;
        private ImageButton btnLike;

        public MascotaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMascota = (ImageView) itemView.findViewById(R.id.imgMascota);
            tvNombreCV = (TextView) itemView.findViewById(R.id.tvNombre);
            tvLikesCV = (TextView) itemView.findViewById(R.id.tvLikes);
            btnLike = (ImageButton) itemView.findViewById(R.id.btn_like);
        }
    }
}
