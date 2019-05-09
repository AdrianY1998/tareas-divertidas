package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.adapters.EvaluacionAdapter;

public class EvaluacionActivity extends AppCompatActivity {
    private List<MiembrosAEvaluar> miembrosAEvaluar = new ArrayList<>();
    private Context mContext;
    private ImageView mBotonAtras;
    private ListView miembrosAEvaluarLista;
    private EvaluacionAdapter evaluacionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion);
        mContext = this;
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
        miembrosAEvaluarLista=findViewById(R.id.miembrosAEvaluarLista);

        this.evaluacionAdapter= new EvaluacionAdapter(mContext,this.miembrosAEvaluar);
        miembrosAEvaluarLista.setAdapter(this.evaluacionAdapter);
        miembrosAEvaluarLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MiembrosAEvaluar miembro = miembrosAEvaluar.get(position);
                Intent intent = new Intent(mContext, FichaMiembroActivity.class);
                intent.putExtra(Constants.KEY_MIEMBRO_A_EVALUAR_SELECCIONADO, new Gson().toJson(miembro));
                startActivity(intent);
            }
        });




    }
}
