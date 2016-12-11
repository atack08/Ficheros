package com.example.atack08.ficheros;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by atack08 on 11/12/16.
 */

public class TaskLectura extends AsyncTask <HashMap<InputStream,Long>, Integer, String>{

    private ProgressDialog pd;
    private TextView consola;
    private String resultado;

    public TaskLectura (ProgressDialog pd, TextView c){
        this.pd = pd;
        this.consola = c;
        this.resultado = "";
    }


    @Override
    protected String doInBackground(HashMap<InputStream, Long>... hashMaps) {
        //RECUPERAMOS EL HASMAP CON EL STREAM Y EL TAMAÑO
        HashMap<InputStream,Long> mapaStream = hashMaps[0];
        Iterator<InputStream> it = mapaStream.keySet().iterator();
        InputStream entrada =  null;
        long sizeBytes=0;

        while (it.hasNext()){
            entrada = it.next();
            sizeBytes = mapaStream.get(entrada).longValue();
        }

        BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
        String linea;

        float sizeLinea;
        float porcentaje=0f;
        float progreso;

        try {
            while((linea = buffer.readLine())!=null){
                sizeLinea = linea.getBytes().length;

                progreso = (sizeLinea * 100) / sizeBytes;

                System.out.println(progreso);

                porcentaje = progreso + porcentaje;
                System.out.println(porcentaje);


                this.publishProgress((int)porcentaje);

                resultado += linea;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        pd.setProgress(values[0]);

        if(values[0] == 100)
            pd.cancel();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();
        this.consola.setText(this.resultado);
    }
}
