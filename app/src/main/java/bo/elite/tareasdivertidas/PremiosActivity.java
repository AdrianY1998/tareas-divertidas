package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.adapters.premiosAdapter;
import bo.elite.tareasdivertidas.adapters.tareaRecyclerViewAdapter;
import bo.elite.tareasdivertidas.models.Tarea;

public class PremiosActivity extends AppCompatActivity {

    private static final String LOG = PremiosActivity.class.getName();
    private Context mContext;

    private ImageView mNuevo;
    private ImageView mBotonInicio;

    private RecyclerView recyclerView;
    private Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_premios);
        mContext = this;

        initViews();

        List<Tarea> premios = new ArrayList<>();
        premios.add(new Premio(150,"Ir al cine", R.drawable.ir_al_cine));

        premiosAdapter adapter = new premiosAdapter(this, premios);

    }

    private void initViews() {
        mNuevo = findViewById(R.id.crear);
        mBotonInicio = findViewById(R.id.inicio);
        recyclerView = findViewById(R.id.recyclerViewTareas);

        mBotonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
    /*public void onPremioClick(Tarea tarea) {
        Intent intent = new Intent(PremiosActivity.this, fichaPremioActivity.class);
        intent.putExtra(Constants.PREMIO_SELECTED, gson.toJson(Premio));
        startActivity(intent);
    }*/
}
