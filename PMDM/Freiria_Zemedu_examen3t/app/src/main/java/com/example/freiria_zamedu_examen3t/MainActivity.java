package com.example.freiria_zamedu_examen3t;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String CLAVE_MENSAJE = "mensaje";
    Spinner sp;
    private List<String> listaGenero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = findViewById(R.id.spinner);
        listaGenero = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.genero)));
        listaGenero.addAll(Arrays.asList(getResources().getStringArray(R.array.generos)));
        ArrayAdapter<String> a = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listaGenero);
        sp.setAdapter(a);
        //sp.setPrompt("Género: ");
        boolean[] isCheckedList = {true, false, true};
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String[] stringArray = getResources().getStringArray(R.array.listaGenero);

                if (i == 1 || i == 2 ||i == 3){
                    Intent intent = new Intent();
                    intent.putExtra(CLAVE_MENSAJE, i);
                    setResult(Activity.RESULT_OK, intent);
                    finish();

                }else if(i == 4 ){

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);//MainActivity.this
                    builder.setTitle("Elige géneros")
                            .setMultiChoiceItems(stringArray, isCheckedList,
                                    new DialogInterface.OnMultiChoiceClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                            isCheckedList[which] = isChecked;
                                        }
                                    })
                            .setPositiveButton("Mostrar Seleccion", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent();
                                    intent.putExtra(CLAVE_MENSAJE, stringArray[i]);
                                    setResult(Activity.RESULT_OK, intent);
                                    finish();

                                }
                            });
                    builder.show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Información!");
        dialog.setMessage("Proyecto de examen del 3er trimestre");
        dialog.setCancelable(false);

        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialog.create().show();
        return super.onOptionsItemSelected(item);
    }
}