package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.adapters.MiembrosAdapter;
import bo.elite.tareasdivertidas.db.DatabaseHelper;

public class AsignarMiembrosTareas extends AppCompatActivity {
    private Context mContext;
    private List<Miembro> miembros = new ArrayList<>();
    private DatabaseHelper dbHelper;

    private ListView mMiembrosLista;
    private MiembrosAdapter miembrosAdapter;
    private ImageView mBotonAtras;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccionar_miembros_tarea);
        mContext = this;

        dbHelper = new DatabaseHelper(mContext);
        this.miembros = dbHelper.getMiembros();

        initViews();
        addEvents();
    }

    private void initViews() {
        this.mBotonAtras = findViewById(R.id.botonAtras);
        mMiembrosLista = findViewById(R.id.miembrosListaA);

        this.miembrosAdapter = new MiembrosAdapter(mContext, this.miembros);
        mMiembrosLista.setAdapter(this.miembrosAdapter);
        mMiembrosLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Miembro miembro = miembros.get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.MIEMBRO_SELECCIONADO_TAREA, new Gson().toJson(miembro));
                setResult(RESULT_OK, intent);
                finish();
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
}
