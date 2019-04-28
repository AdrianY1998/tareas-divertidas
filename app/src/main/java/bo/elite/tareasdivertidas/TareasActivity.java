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

public class TareasActivity extends AppCompatActivity {

    private Context mContext;

    private ImageView mBotonAtras;
    private ImageView mNuevaTarea;
    private ImageView mEditarTarea;

    private RecyclerView recyclerView;
    private Gson gson = new Gson();
    //d

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.asignacion_tareas);
        mContext =this;

        initViews();
        addEveents();

        List<Tarea> tareaList = TareaU.getTareas();
        tareaRecyclerViewAdapter adapter = new tareaRecyclerViewAdapter(this, tareaList);
        adapter.setTareaCallback(new TareaCallback() {
            @Override
            public void onTareaClick(Tarea tarea) {
                Intent intent = new Intent(TareasActivity.this, TareasDetailsActivity.class);
                intent.putExtra(Constants.TAREA_SELECTED, gson.toJson(tarea));
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }

    private void initViews(){
        mBotonAtras = findViewById(R.id.botonAtras);
        mNuevaTarea = findViewById(R.id.nuevaTarea);
        mEditarTarea= findViewById(R.id.editar);
        recyclerView = findViewById(R.id.recyclerViewTareas);
    }

    private  void addEveents(){
        mBotonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuActivity.class);
                startActivity(intent);
            }
        });

        mNuevaTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NuevaTarea.class);
                startActivity(intent);
            }
        });

        /*mEditarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, //);
                startActivity(intent);
            }
        });*/
    }

    /*public void nuevaTareaClick (View view){
        Intent intent = new Intent(mContext, NuevaTarea.class);
        startActivity(intent);
    }*/
}
