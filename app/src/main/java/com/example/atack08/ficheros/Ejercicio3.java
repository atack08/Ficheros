package com.example.atack08.ficheros;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class Ejercicio3 extends AppCompatActivity {

    private ProgressDialog progreso;
    private HashMap<InputStream,Long> mapaFile;
    private TextView consola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejercicio3);

        consola = (TextView) findViewById(R.id.consolaQuijote);

        mapaFile =  new HashMap<>();
        try {
            long t = getAssets().openFd("quijote.txt").getLength();

            InputStream entrada = getResources().openRawResource(R.raw.quijote);
            mapaFile.put(entrada,t);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void realizarTareaLectura(View v){

        progreso =  new ProgressDialog(this);
        progreso.setCancelable(false);
        progreso.setTitle("Leyendo Fichero...");
        progreso.setMessage("Iniciando");
        progreso.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progreso.setMax(100);
        progreso.setProgress(0);
        progreso.show();

        TaskLectura tarea = new TaskLectura(progreso,consola);
        tarea.execute(mapaFile);



    }
}
