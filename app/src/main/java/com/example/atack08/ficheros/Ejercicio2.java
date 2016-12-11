package com.example.atack08.ficheros;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Ejercicio2 extends AppCompatActivity {

    private ArrayList<Provincia> listaProvincias;
    private Spinner comboProvincias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio2);

        listaProvincias =  crearListaProvincias();

        ArrayAdapter<Provincia> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, listaProvincias);

        comboProvincias = (Spinner)findViewById(R.id.comboP);
        comboProvincias.setAdapter(adaptador);


    }


    public ArrayList<Provincia> crearListaProvincias(){

        ArrayList<Provincia> listaP =  new ArrayList<>();

        //ABRIMOS EL STREAM CON EL FICHERO - RECURSO
        InputStream entrada = getResources().openRawResource(R.raw.provincias);
        BufferedReader reader = new BufferedReader(new InputStreamReader(entrada));

        //LEEMOS EL FICHERO

        try {
            String linea,nombre;
            double lat,lon;
            while((linea=reader.readLine())!=null){

                String[] valores = linea.split("_");
                nombre = valores[0].trim();

                System.out.println(linea.contains("_"));
                System.out.println(valores.length);
                System.out.println(valores[0]);
                System.out.println(valores[1]);


                String[] coordenadas = valores[1].split("/");
                lat = Double.parseDouble(coordenadas[0]);
                lon = Double.parseDouble(coordenadas[1]);

                listaP.add(new Provincia(nombre,lat,lon));
            }

            reader.close();
            entrada.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaP;
    }

    //MÉTODO QUE ABRE LA APLICACION GOOGLE MAPS Y NOS FIJA EN LA CAPITAL DE PROVINCIA
    public void irAlMapa(View v){

        Provincia p = (Provincia) comboProvincias.getSelectedItem();
        String lat = String.valueOf(p.getLatitud());
        String lon = String.valueOf(p.getLongitud());

        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse("geo:"+ lat + "," + lon + "?z=0&q=" + lat + "," + lon + "("+ p.getNombre() +")"));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
        startActivity(intent);


    }
}

