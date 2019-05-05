package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.adapters.premiosAdapter;
import bo.elite.tareasdivertidas.adapters.tareaRecyclerViewAdapter;
import bo.elite.tareasdivertidas.models.Tarea;
import bo.elite.tareasdivertidas.singleton.MiembrosSingleton;
import bo.elite.tareasdivertidas.singleton.PremioSingleton;

public class PremiosActivity extends AppCompatActivity {

    private static final String LOG = PremiosActivity.class.getName();
    private Context mContext;

    private ImageView mNuevo;
    private ImageView mBotonInicio;
    private List<Premio> premios = PremioSingleton.getInstance().getPremios();
    private Premio premio;
    private ListView premiosLista;
    private premiosAdapter adapter;

    private Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_premios);
        mContext = this;

        initViews();

        premios.add(new Premio(150,"Ir al cine", R.drawable.ir_al_cine, premios.size() + 1));
        premios.add(new Premio(150,"Nuevo Videojuego", R.drawable.nuevo_libro, premios.size() + 1));

    }

    private void initViews() {
        mNuevo = findViewById(R.id.crear);
        mBotonInicio = findViewById(R.id.inicio);

        mBotonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuActivity.class);
                startActivity(intent);
            }
        });
        premiosLista = findViewById(R.id.miembrosLista);

        adapter = new premiosAdapter(mContext, premios);
        premiosLista.setAdapter(adapter);
    }
}
