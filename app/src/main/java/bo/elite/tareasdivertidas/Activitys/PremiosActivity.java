package bo.elite.tareasdivertidas.Activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.Constants;
import bo.elite.tareasdivertidas.models.Premio;
import bo.elite.tareasdivertidas.R;
import bo.elite.tareasdivertidas.adapters.premiosAdapter;
import bo.elite.tareasdivertidas.db.DatabaseHelper;

public class PremiosActivity extends AppCompatActivity {

    private static final String LOG = PremiosActivity.class.getName();
    private Context mContext;

    private ImageView mNuevo;
    private ImageView mBotonInicio;
    private List<Premio> premios = new ArrayList<>();
    private Premio premio;
    private ListView premiosLista;
    private premiosAdapter premiosAdapter;

    private DatabaseHelper dbHelper;

    private Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_premios);
        mContext = this;

        dbHelper = new DatabaseHelper(mContext);
        this.premios = dbHelper.getPremios();

        initViews();

    }

    private void initViews() {
        mNuevo = findViewById(R.id.crear);
        mBotonInicio = findViewById(R.id.inicio);
        mNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCrearPremioClick(v);
            }
        });

        mBotonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuActivity.class);
                startActivity(intent);
            }
        });
        premiosLista = findViewById(R.id.premiosVista);

        premiosAdapter = new premiosAdapter(mContext, premios);
        premiosLista.setAdapter(premiosAdapter);

        premiosLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Premio premio = premios.get(position);
                Intent intent = new Intent(mContext, FichaPremioActivity.class);
                intent.putExtra(Constants.KEY_PREMIO_SELECTED, new Gson().toJson(premio));
                startActivity(intent);
            }
        });
    }

    public void onCrearPremioClick(View view) {
        Intent intent = new Intent(mContext, CrearPremioActivity.class);
        startActivityForResult(intent, Constants.KEY_PREMIO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.premios.clear();
        this.premios.addAll(dbHelper.getPremios());
        this.premiosAdapter.notifyDataSetChanged();
        Log.e("Permios", ": " + new Gson().toJson(this.premios));
    }
}
