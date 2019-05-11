package bo.elite.tareasdivertidas.Activitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import bo.elite.tareasdivertidas.R;

public class MenuActivity extends AppCompatActivity {
    private static final String LOG = MiembroActivity.class.getName();
    private Context mContext;
    private ImageView mBotonMiembros;
    private ImageView mBotonAsignacionTareas;
    private ImageView mBotonPremios;
    private ImageView mBotonEvaluacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
        mContext = this;
        initViews();
    }

    private void initViews() {
        mBotonMiembros = findViewById(R.id.botonMiembros);
        mBotonPremios = findViewById(R.id.botonPremios);
        mBotonAsignacionTareas= findViewById(R.id.botonAsignacionTareas);
        mBotonEvaluacion =findViewById(R.id.evaluacion);
        mBotonMiembros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MiembroActivity.class);
                startActivity(intent);
            }
        });
        mBotonPremios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PremiosActivity.class);
                startActivity(intent);
            }
        });
        mBotonAsignacionTareas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TareasActivity.class);
                startActivity(intent);
            }
        });
        mBotonEvaluacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EvaluacionActivity.class);
                startActivity(intent);
            }
        });
    }
}
