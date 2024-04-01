package com.radacode.petagrammascotas.pojo;

public class Mascota {

    private int id;
    private int fotoMascota;
    private String nombreMascota;
    private int numeroLikes;

    public Mascota(int fotoMascota, String nombreMascota, int numeroLikes) {
        this.fotoMascota = fotoMascota;
        this.nombreMascota = nombreMascota;
        this.numeroLikes = numeroLikes;
    }

    public Mascota() {}

    public int getFotoMascota() {
        return fotoMascota;
    }

    public void setFotoMascota(int fotoMascota) {
        this.fotoMascota = fotoMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public int getNumeroLikes() {
        return numeroLikes;
    }

    public void setNumeroLikes(int numeroLikes) {
        this.numeroLikes = numeroLikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
