package bo.elite.tareasdivertidas.Avctivitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.Callback.TareaCallback;
import bo.elite.tareasdivertidas.Constants;
import bo.elite.tareasdivertidas.R;
import bo.elite.tareasdivertidas.adapters.tareaRecyclerViewAdapter;
import bo.elite.tareasdivertidas.db.DatabaseHelper;
import bo.elite.tareasdivertidas.models.Tarea;

public class TareasActivity extends AppCompatActivity {

    private Context mContext;

    private List<Tarea> tarea = new ArrayList<>();
    private DatabaseHelper dbHelper;

    private ImageView mBotonAtras;
    private ImageView mNuevaTarea;

    private RecyclerView recyclerView;
    private tareaRecyclerViewAdapter tareaAdapter;
    private Gson gson = new Gson();
    //d

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.asignacion_tareas);
        mContext =this;

        dbHelper = new DatabaseHelper(mContext);
        this.tarea = dbHelper.getTareas();

        initViews();
        addEveents();

        List<Tarea> tareaList = tarea;
        tareaAdapter = new tareaRecyclerViewAdapter(this, tareaList);
        tareaAdapter.setTareaCallback(new TareaCallback() {
            @Override
            public void onTareaClick(Tarea tarea) {
                Intent intent = new Intent(TareasActivity.this, TareasDetailsActivity.class);
                intent.putExtra(Constants.TAREA_SELECTED, gson.toJson(tarea));
                startActivityForResult(intent,111);
            }
        });

        recyclerView.setAdapter(tareaAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

    }

    private void initViews(){
        mBotonAtras = findViewById(R.id.botonAtras);
        mNuevaTarea = findViewById(R.id.nuevaTarea);
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
                startActivityForResult(intent,999);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        this.tarea.clear();
        this.tarea.addAll(dbHelper.getTareas());
        this.tareaAdapter.notifyDataSetChanged();
    }
}
