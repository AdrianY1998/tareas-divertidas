package bo.elite.tareasdivertidas;

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
import android.widget.TextView;

import com.google.gson.Gson;

import bo.elite.tareasdivertidas.db.DatabaseHelper;

public class FichaMiembroActivity extends AppCompatActivity {
    private Context mContext;
    private ImageView retornar;
    private ImageView fotoPerfil;
    private TextView nombre;
    private TextView edad;
    private int puntaje;
    private TextView puntajeMostrado;
    private ImageView imagenPremio;
    private TextView nombrePremio;
    private TextView puntajePremio;
    private ImageView editarMiembro;
    private ImageView cambiarObjetivo;
    private ImageView eliminar;
    private Miembro miembro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ficha_miembro);
        mContext = this;
        initViews();
        receiveData();
        addEvents();
    }

    private void initViews() {
        retornar = findViewById(R.id.botonAtras);
        fotoPerfil = findViewById(R.id.fotoPerfil);
        nombre = findViewById(R.id.nombre);
        edad = findViewById(R.id.edad);
        puntajeMostrado = findViewById(R.id.puntaje);
        imagenPremio = findViewById(R.id.imagenPremio);
        nombrePremio = findViewById(R.id.nombrePremio);
        puntajePremio = findViewById(R.id.puntajePremio);
        editarMiembro = findViewById(R.id.editarMiembro);
        cambiarObjetivo = findViewById(R.id.cambiarObjetivo);
        eliminar = findViewById(R.id.eliminar);
    }

    private void addEvents() {
        retornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MiembroActivity.class);
                startActivity(intent);
            }
        });

        editarMiembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogo = new Dialog(mContext);
                dialogo.setContentView(R.layout.ficha_editar_miembro);

                final EditText newName = dialogo.findViewById(R.id.nombre);
                final EditText newEdad = dialogo.findViewById(R.id.edad);
                ImageView modificar = dialogo.findViewById(R.id.Modificar);
                ImageView cancelar = dialogo.findViewById(R.id.cancelar);
                modificar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, MiembroActivity.class);
                        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
                        dbHelper.modifyMiembro(miembro.getId(), newName.getText().toString(), Integer.parseInt(newEdad.getText().toString()));
                        dialogo.dismiss();
                        startActivity(intent);
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
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String json = intent.getStringExtra(Constants.KEY_MIEMBRO_SELECCIONADO);
                Miembro miembro = new Gson().fromJson(json, Miembro.class);
                confirmarEliminar(miembro);
            }
        });

        cambiarObjetivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PremiosModificarActivity.class);
                startActivityForResult(intent, Constants.PREMIO_SELECCIONADO);
            }
        });

    }

    private void confirmarEliminar(final Miembro miembro) {
        final Dialog dialogo = new Dialog(mContext);
        dialogo.setContentView(R.layout.borrar_miembros);

        Button eliminar = dialogo.findViewById(R.id.eliminarButton);
        Button cancelar = dialogo.findViewById(R.id.cancelarButton);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(mContext);
                db.eliminarMiembro(miembro.getId());
                Intent intent2 = new Intent(mContext, MiembroActivity.class);
                startActivity(intent2);
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


    private void receiveData() {
        Intent intent = getIntent();
        if(intent.hasExtra(Constants.KEY_MIEMBRO_SELECCIONADO)){
            String json = intent.getStringExtra(Constants.KEY_MIEMBRO_SELECCIONADO);
            miembro = new Gson().fromJson(json, Miembro.class);
            nombre.setText(miembro.getNombre());
            edad.setText("" + miembro.getEdad());
            puntaje = miembro.getPuntaje();
            Log.e("Database", "" + puntaje);
            puntajeMostrado.setText("" + miembro.getPuntaje());
            nombrePremio.setText(miembro.getPremioObjetivo());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Premio premio = new Gson().fromJson(data.getStringExtra(Constants.KEY_PREMIO_SELECTED), Premio.class);
        miembro.setPremioObjetivo(nombrePremio.toString());
        nombrePremio.setText(premio.getNombrePremio());
        puntajePremio.setText(""+premio.getPuntaje());
        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        dbHelper.añadirTareaMiembro(miembro.getId(), premio.getNombrePremio());
    }
}