package com.example.atack08.ficheros;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText text;
    private TextView consola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText)findViewById(R.id.texto);
        consola = (TextView)findViewById(R.id.consola);
        
    }
    
    public void  escribirEnFichero(View v){

        try {

            OutputStreamWriter salida = new OutputStreamWriter(openFileOutput("ejercicio1.txt",
                    Context.MODE_APPEND));

            String cadena = "\n" + text.getText().toString();

            salida.write(cadena);

            salida.close();
            
        } catch (FileNotFoundException e) {
           mostrarPanelError(e.getLocalizedMessage());
        } catch (IOException e) {
            mostrarPanelError(e.getLocalizedMessage());
        }

    }

    public void leerFichero(View v){

        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(
                    openFileInput("ejercicio1.txt")));

            String linea = entrada.readLine();

            mostrarPanelError(linea);

            entrada.close();

        } catch (FileNotFoundException e) {
            mostrarPanelError(e.getLocalizedMessage());
        } catch (IOException e) {
            mostrarPanelError(e.getLocalizedMessage());
        }
    }
    
    public void mostrarPanelError(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
