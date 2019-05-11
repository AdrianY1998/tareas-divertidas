package bo.elite.tareasdivertidas.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.Constants;
import bo.elite.tareasdivertidas.models.Miembro;
import bo.elite.tareasdivertidas.R;
import bo.elite.tareasdivertidas.adapters.MiembrosAdapter;
import bo.elite.tareasdivertidas.db.DatabaseHelper;
import bo.elite.tareasdivertidas.models.Tarea;

public class TareasDetailsActivity extends AppCompatActivity {
    private Context mContext;
    private Tarea tarea;
    private ImageView mBotonAtras;
    private ImageView mEliminar;
    private ImageView mEditar;
    private ImageView mNuevoMiembro;
    private ImageView imagenTarea;
    private TextView nombreTarea;
    private TextView puntajeTarea;

    private ListView mMiembrosListaView;
    private List<Miembro> mMiembrosLista = new ArrayList<>();
    private DatabaseHelper dbHelper;
    private MiembrosAdapter miembrosAdapter;

    private Gson gson = new Gson();

    private int confirm;
    //d

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantilla_tareas);
        mContext = this;
        initViews();
        addEvents();

        tarea = this.gson.fromJson(getIntent().getStringExtra(Constants.TAREA_SELECTED), Tarea.class);
        dbHelper = new DatabaseHelper(mContext);

        mMiembrosLista = dbHelper.getMiembrosAsignados(tarea.getId());
        this.miembrosAdapter = new MiembrosAdapter(mContext, this.mMiembrosLista);
        mMiembrosListaView.setAdapter(this.miembrosAdapter);


        //this.miembros = dbHelper.getMiembrosTareas(tarea.getId());


        fillTareaData(tarea);
        deleteTarea(tarea);
        editTarea(tarea);
        nuevoMiebro();
        //fillListMiembros();
    }

    /*@Override
    protected void onRestart() {
        super.onRestart();
        //dbHelper = new DatabaseHelper(mContext);
        //mMiembrosLista = dbHelper.getMiembrosAsignados(tarea.getId());
        ///////////////
        this.miembrosAdapter = new MiembrosAdapter(mContext, this.mMiembrosLista);
        mMiembrosListaView.setAdapter(this.miembrosAdapter);

    }*/


    private void initViews() {
        this.mBotonAtras = findViewById(R.id.botonAtras);
        this.imagenTarea= findViewById(R.id.imagenTarea);
        this.nombreTarea = findViewById(R.id.nombreT);
        this.puntajeTarea = findViewById(R.id.puntajeTarea);
        this.mEliminar = findViewById(R.id.eliminar);
        this.mEditar = findViewById(R.id.editar);
        this.mNuevoMiembro = findViewById(R.id.nuevoMiembro);

        mMiembrosListaView = findViewById(R.id.listaMiembros);

        this.miembrosAdapter = new MiembrosAdapter(mContext, this.mMiembrosLista);
        mMiembrosListaView.setAdapter(this.miembrosAdapter);


    }

    private void addEvents(){
        mBotonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TareasActivity.class);
                startActivity(intent);
            }
        });
    }

    /*private void fillListMiembros(){
        mMiembrosLista = dbHelper.getMiembrosAsignados(tarea.getId());
        ///////////////
        this.miembrosAdapter = new MiembrosAdapter(mContext, this.mMiembrosLista);
        mMiembrosListaView.setAdapter(this.miembrosAdapter);
    }*/

    private void nuevoMiebro(){
        mNuevoMiembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AsignarMiembrosTareas.class);
                intent.putExtra(Constants.MIEMBRO_SELECCIONADO_TAREA, new Gson().toJson(tarea));
                setResult(RESULT_OK, intent);
                startActivity(intent);

                //Intent intent = new Intent(mContext, AsignarMiembrosTareas.class);
                //startActivity(intent);
            }
        });
    }

    private void editTarea(final Tarea tarea){
        mEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogo = new Dialog(mContext);
                dialogo.setContentView(R.layout.layout_editar_tarea);

                final EditText mNuevoNombreT = dialogo.findViewById(R.id.nuevoN);
                final EditText mNuevoPuntajeT = dialogo.findViewById(R.id.nuevoP);

                Button aceptar = dialogo.findViewById(R.id.guardar);
                Button cancelar = dialogo.findViewById(R.id.cancelar);

                aceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
                        if (mNuevoNombreT.getText().toString().isEmpty()) {
                            Toast.makeText(mContext, "Ingrese el nuevo nombre por favor", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (mNuevoPuntajeT.getText().toString().isEmpty()) {
                            Toast.makeText(mContext, "Ingrese el puntaje por favor", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        dbHelper.updateT(tarea.getId(), mNuevoNombreT.getText().toString(), Integer.parseInt(mNuevoPuntajeT.getText().toString()));
                        finish();
                    }
                });

                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogo.dismiss();
                    }
                });
                dialogo.setCancelable(false);
                dialogo.show();
            }
        });
    }

    private void deleteTarea(final Tarea tarea){
        mEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    confirmDelete(tarea);
            }
        });
    }

    private void confirmDelete(final Tarea tarea){
        final Dialog dialogo = new Dialog(mContext);
        dialogo.setContentView(R.layout.layout_confirmacion_borrar);

        Button eliminar = dialogo.findViewById(R.id.eliminar);
        Button cancelar = dialogo.findViewById(R.id.cancelar);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(mContext);
                dbHelper.deleteT(tarea.getId());

                String json = new Gson().toJson(tarea);
                Intent intent = new Intent();
                intent.putExtra(Constants.TAREA_ELIMINADA, json);
                setResult(RESULT_OK, intent);
                finish();
                /*
                confirm = 1;
                dialogo.dismiss();*/
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm = 0;
                dialogo.dismiss();
            }
        });
        dialogo.setCancelable(false);
        dialogo.show();
    }


    private void fillTareaData(Tarea tarea) {
        this.imagenTarea.setImageResource(tarea.getImageTarea());
        this.nombreTarea.setText(tarea.getNameTarea());
        this.puntajeTarea.setText(String.valueOf(tarea.getPointTarea()));
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Miembro miembro = this.gson.fromJson(getIntent().getStringExtra(Constants.MIEMBRO_SELECCIONADO_TAREA), Miembro.class);
        dbHelper.insertTareaMiembro(tarea, miembro);
        Log.e("MIEMBROS", ": " + new Gson().toJson(miembro));

        //Cada vez que retorna a la activity actualizar la lista de miembros y actualizar el adapter notifyDataSetChanged
        /*this.miembros.clear();
        this.miembros.addAll(dbHelper.getMiembrosTareas(tarea.getId()));
        this.miembrosAdapter.notifyDataSetChanged();
        Log.e("MIEMBROS", ": " + new Gson().toJson(this.miembros));*/
    }
}
