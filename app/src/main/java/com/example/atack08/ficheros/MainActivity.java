package com.example.atack08.ficheros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void linkActividades(View v){

        Intent intent=null;
        int idBoton = v.getId();
        switch (idBoton){
            case R.id.botonEjercicio1:
                intent = new Intent(this,Ejercicio1.class);
                break;
            case R.id.botonEjercicio2:
                //intent = new Intent(this,Ejercicio2.class);
                break;
            case R.id.botonEjercicio3:
                // intent = new Intent(this,Ejercicio3.class);
                break;

        }

        startActivity(intent);

    }

}
