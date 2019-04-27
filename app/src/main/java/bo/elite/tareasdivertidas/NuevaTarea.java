package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.List;

import bo.elite.tareasdivertidas.Callback.TareaCallback;
import bo.elite.tareasdivertidas.adapters.tareaRecyclerViewAdapter;
import bo.elite.tareasdivertidas.models.Tarea;
import bo.elite.tareasdivertidas.utils.TareaU;

public class NuevaTarea extends AppCompatActivity {

    private Context mContext;

    private ImageView mBotonAtras;

    private RecyclerView recyclerView;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tareas_disponibles);
        mContext =this;

        initViews();
        addEvents();

        List<Tarea> tareaList = TareaU.getTareas();
        tareaRecyclerViewAdapter adapter = new tareaRecyclerViewAdapter(this, tareaList);
        adapter.setTareaCallback(new TareaCallback() {
            @Override
            public void onTareaClick(Tarea tarea) {
                Intent intent = new Intent(NuevaTarea.this, TareasDetailsActivity.class);
                intent.putExtra(Constants.TAREA_SELECTED, gson.toJson(tarea));
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        AsignacionTareas.getInstance().getTareas();
        //AsignacionTareas.getInstance().asignarTarea(new Tarea());
        AsignacionTareas.getInstance().completarTarea("cocinar");
    }

    private void initViews(){
        mBotonAtras = findViewById(R.id.botonAtras);
        recyclerView = findViewById(R.id.recyclerViewTareas);
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

    /*public void botonAtrasClick (View view){
        Intent intent = new Intent(mContext, TareasActivity.class);
        startActivity(intent);
    }*/
}
