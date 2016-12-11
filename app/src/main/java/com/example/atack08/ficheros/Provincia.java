package com.example.atack08.ficheros;

/**
 * Created by atack08 on 11/12/16.
 */


public class Provincia {

    private String nombre;
    private double latitud,longitud;

    public Provincia(String n, double lat, double lon){

        this.nombre =  n;
        this.latitud =  lat;
        this.longitud =  lon;

    }

    public double getLongitud() {
        return longitud;
    }

    public String getNombre() {
        return nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
