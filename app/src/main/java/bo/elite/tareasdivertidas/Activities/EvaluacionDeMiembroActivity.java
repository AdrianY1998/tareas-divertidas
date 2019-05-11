package bo.elite.tareasdivertidas.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.Callback.TareaCallback;
import bo.elite.tareasdivertidas.Constants;
import bo.elite.tareasdivertidas.adapters.tareaRecyclerViewAdapter2;
import bo.elite.tareasdivertidas.db.DatabaseHelper;
import bo.elite.tareasdivertidas.models.Miembro;
import bo.elite.tareasdivertidas.R;
import bo.elite.tareasdivertidas.models.Tarea;

public class EvaluacionDeMiembroActivity extends AppCompatActivity {
    private Context mContext;

    private List<Tarea> tarea = new ArrayList<>();
    private DatabaseHelper dbHelper;

    private ImageView mAtras;
    private TextView mNombre;
    private ImageView mImage;
    private ImageView mEvaluar;
    private Miembro miembro;
    private RecyclerView recyclerView;
    private tareaRecyclerViewAdapter2 tareaAdapter;

    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ficha_evaluacion);
        mContext = this;

        dbHelper = new DatabaseHelper(mContext);

        initViews();

        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_MIEMBRO_A_EVALUAR_SELECCIONADO)) {
            String json = intent.getStringExtra(Constants.KEY_MIEMBRO_A_EVALUAR_SELECCIONADO);
            miembro = new Gson().fromJson(json, Miembro.class);
            //datosMiembro(miembro);
            mNombre.setText(miembro.getNombre());
            Drawable image = getResources().getDrawable(miembro.getIcono());
            mImage.setImageDrawable(image);

            this.tarea = dbHelper.getTareaAsignadas(miembro.getId());
        }

        tareaAdapter = new tareaRecyclerViewAdapter2(mContext, tarea);
        tareaAdapter.setTareaCallback(new TareaCallback() {
            @Override
            public void onTareaClick(Tarea tarea) {
                final Tarea tarea2 = tarea;
                final Dialog dialogo = new Dialog(mContext);
                dialogo.setContentView(R.layout.confirmacion_tarea_cumplida);
                final Button aceptar = dialogo.findViewById(R.id.si);
                final Button cancelar = dialogo.findViewById(R.id.no);
                aceptar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent2 = new Intent(mContext, MiembroActivity.class);
                        miembro.setPuntaje(tarea2.getPointTarea());
                        dbHelper.updatePuntaje(miembro.getId(),miembro.getPuntaje());
                        dbHelper.eliminarTyM(miembro.getId(), tarea2.getId());
                        startActivity(intent2);
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

        recyclerView.setAdapter(tareaAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        //Miembro miembro = this.gson.fromJson(getIntent().getStringExtra(Constants.KEY_MIEMBRO_SELECCIONADO), Miembro.class);
        //Tarea tarea = this.gson.fromJson(getIntent().getStringExtra(Constants.KEY_MIEMBRO_A_EVALUAR_SELECCIONADO), Tarea.class);

        //datosTarea(tarea); (?
    }

    private void initViews() {
        mAtras = findViewById(R.id.botonAtras);
        mAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EvaluacionActivity.class);
                startActivity(intent);
            }
        });
        mNombre = findViewById(R.id.nombreUser);
        mImage = findViewById(R.id.imagen);
        recyclerView = findViewById(R.id.TareasM);

    }
    private void datosMiembro(Miembro miembro){
        mNombre.setText(miembro.getNombre());
        Drawable image = getResources().getDrawable(miembro.getIcono());
        mImage.setImageDrawable(image);

    }


}
