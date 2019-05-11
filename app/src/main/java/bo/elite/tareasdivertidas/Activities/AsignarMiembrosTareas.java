package bo.elite.tareasdivertidas.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.Constants;
import bo.elite.tareasdivertidas.R;
import bo.elite.tareasdivertidas.adapters.MiembrosAdapter;
import bo.elite.tareasdivertidas.db.DatabaseHelper;
import bo.elite.tareasdivertidas.models.Miembro;
import bo.elite.tareasdivertidas.models.Tarea;

public class AsignarMiembrosTareas extends AppCompatActivity {
    private Context mContext;
    private List<Miembro> miembros = new ArrayList<>();
    private DatabaseHelper dbHelper;
    private Tarea tarea;

    private ListView mMiembrosLista;
    private MiembrosAdapter miembrosAdapter;
    private ImageView mBotonAtras;

    private Gson gson = new Gson();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccionar_miembros_tarea);
        mContext = this;

        dbHelper = new DatabaseHelper(mContext);
        this.miembros = dbHelper.getMiembros();
        tarea = this.gson.fromJson(getIntent().getStringExtra(Constants.MIEMBRO_SELECCIONADO_TAREA), Tarea.class);

        initViews();
        addEvents();
    }

    private void initViews() {

        this.mBotonAtras = findViewById(R.id.botonAtras);
        mMiembrosLista = findViewById(R.id.miembrosListaA);

        this.miembrosAdapter = new MiembrosAdapter(mContext, this.miembros);
        mMiembrosLista.setAdapter(this.miembrosAdapter);
        mMiembrosLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private Gson gson = new Gson();
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, TareasActivity.class);
                Miembro miembro = miembros.get(position);
                //Intent intent = new Intent();
                //intent.putExtra(Constants.MIEMBRO_SELECCIONADO_TAREA, new Gson().toJson(miembro));


                //Miembro miembro = this.gson.fromJson(getIntent().getStringExtra(Constants.MIEMBRO_SELECCIONADO_TAREA_2), Miembro.class);
                dbHelper.insertTareaMiembro(tarea, miembro);
                Log.e("MIEMBROS", ": " + new Gson().toJson(miembro));
                startActivity(intent);
                //finish();
            }
        });
    }

    private void addEvents(){
        mBotonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /*protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }*/

}
