package com.example.atack08.ficheros;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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

        mostrarPanelError(Environment.getExternalStorageState());
        
    }

    //CREA Y ESCRIBE EN FICHERO - MEMORIA INTERNA
    public void  escribirEnFichero(View v){

        try {

            OutputStreamWriter salida = new OutputStreamWriter(openFileOutput("ejercicio1.txt",
                    Context.MODE_APPEND));

            String cadena = text.getText().toString() + "\n";

            salida.write(cadena);

            salida.close();
            
        } catch (FileNotFoundException e) {
           mostrarPanelError(e.getLocalizedMessage());
        } catch (IOException e) {
            mostrarPanelError(e.getLocalizedMessage());
        }

    }

    //LEE EL CONTENIDO DEL FICHERO EN MEMORIA INTERNA Y LO IMPRIME EN EL TEXTVIEW
    public void leerFichero(View v){

        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(
                    openFileInput("ejercicio1.txt")));

            consola.setText("");

            String linea;
            while ((linea = entrada.readLine()) != null){
                consola.append(linea + "\n");
            }

            entrada.close();

        } catch (FileNotFoundException e) {
            mostrarPanelError(e.getLocalizedMessage());
        } catch (IOException e) {
            mostrarPanelError(e.getLocalizedMessage());
        }
    }

    //CREA Y ESCRIBE FICHERO EN MEMORIA EXTERNA - SD
    public void escribirFicheroExterno(View v){

        //COMPROBAMOS EL ESTADO DE LA MEMORIA EXTERNA - SD
        String estado = Environment.getExternalStorageState();

        //DEPENDIENDO DEL ESTADO HACEMOS UNA COSA U OTRA
        switch (estado){

            case Environment.MEDIA_MOUNTED:

                try {

                    Context context = getApplicationContext();
                    File rutaFichero = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);

                    File file = new File(rutaFichero.getAbsolutePath(), "fichero1.txt");

                    String cadena = text.getText().toString() + "\n";

                    OutputStreamWriter salida = new OutputStreamWriter(new FileOutputStream(file,true));
                    salida.write(cadena);

                    salida.close();


                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            default:
                mostrarPanelError("No se puede escribir en la tarjeta SD");
                break;

        }

    }

    //LEE E IMPRIME EN TEXTVIEW EL CONTENIDO DE UN FICHERO EN MEMORIA EXTERNA
    public void leerFicheroExterno(View v){

        //COMPROBAMOS EL ESTADO DE LA MEMORIA EXTERNA - SD
        String estado = Environment.getExternalStorageState();

        if(estado.equals(Environment.MEDIA_MOUNTED) || estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {

            try {

                Context context = getApplicationContext();
                File rutaFichero = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                File file = new File(rutaFichero.getAbsolutePath(), "fichero1.txt");

                BufferedReader entrada = new BufferedReader( new FileReader(file));

                consola.setText("");

                String linea;
                while ((linea = entrada.readLine()) != null){
                    consola.append(linea + "\n");
                }

                entrada.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            mostrarPanelError("No se puede leer en la tarjeta SD");


    }

    //MÉTODO PARA BORRAR UN FICHERO DE LA MEMORIA EXTERNA - SD
    public void borrarFicheroExterno(View v){

        //COMPROBAMOS EL ESTADO DE LA MEMORIA EXTERNA - SD
        String estado = Environment.getExternalStorageState();
        if(estado.equals(Environment.MEDIA_MOUNTED) || estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {

            Context context = getApplicationContext();
            File rutaFichero = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
            File file = new File(rutaFichero.getAbsolutePath(), "fichero1.txt");

            if(file.delete())
                mostrarPanelError("Fichero borrado correctamente");
            else
                mostrarPanelError("No se pudo borrar el fichero");

        }

    }

    //MÉTODO PARA BORRAR UN FICHERO DE LA MEMORIA INTERNA
    public void borrarFicheroInterno(View v){

        Context context = getApplicationContext();

        if(context.deleteFile("ejercicio1.txt"))
            mostrarPanelError("Fichero borrado correctamente");
        else
            mostrarPanelError("No se pudo borrar el fichero");
    }


    public void mostrarPanelError(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
