package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.adapters.EvaluacionAdapter;
import bo.elite.tareasdivertidas.db.DatabaseHelper;

public class EvaluacionActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private List<Miembro> miembro = new ArrayList<>();
    private Context mContext;
    private ImageView mBotonAtras;
    private ListView miembrosLista;
    private EvaluacionAdapter evaluacionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion);
        mContext = this;

        dbHelper = new DatabaseHelper(mContext);
        this.miembro = dbHelper.getMiembros();
        initViews();
    }

    private void initViews() {
        mBotonAtras=findViewById(R.id.botonHome);
        mBotonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuActivity.class);
                startActivity(intent);
            }
        });
        miembrosLista =findViewById(R.id.miembrosAEvaluarLista);

        this.evaluacionAdapter= new EvaluacionAdapter(mContext,this.miembro);
        miembrosLista.setAdapter(this.evaluacionAdapter);
        miembrosLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Miembro miembro = EvaluacionActivity.this.miembro.get(position);
                miembro.setId(position);
                Intent intent = new Intent(mContext, EvaluacionDeMiembroActivity.class);
                intent.putExtra(Constants.KEY_MIEMBRO_SELECCIONADO, new Gson().toJson(miembro));
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Cada vez que retorna a la activity actualizar la lista de miembros y actualizar el adapter notifyDataSetChanged
        this.miembro.clear();
        this.miembro.addAll(dbHelper.getMiembros());
        this.evaluacionAdapter.notifyDataSetChanged();
        Log.e("MIEMBROS", ": " + new Gson().toJson(this.miembro));
    }
}
