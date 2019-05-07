package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.adapters.MiembrosAdapter;

import bo.elite.tareasdivertidas.db.DatabaseHelper;
import bo.elite.tareasdivertidas.singleton.MiembrosSingleton;

public class MiembroActivity extends AppCompatActivity {
    private static final String LOG = MiembroActivity.class.getName();
    private Context mContext;
    //private List<Miembro> miembros = MiembrosSingleton.getInstance().getMiembros();

    private DatabaseHelper dbHelper;
    private List<Miembro> miembros = new ArrayList<>();

    //private List<Miembro> miembros = DatabaseHelper.getInstance().getMiembros();
    //private List<Miembro> miembros;
    private ImageView mNuevoMiembro;
    private ImageView mEliminarMiembro;
    private ImageView mBotonAtras;
    private Miembro miembro;
    private ListView miembrosLista;
    private MiembrosAdapter miembrosAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.miembro_layout);
        mContext = this;

        dbHelper = new DatabaseHelper(mContext);
        this.miembros = dbHelper.getMiembros();
        initViews();
        //returnMethod();
    }

    private void initViews() {
        mNuevoMiembro = findViewById(R.id.nuevoMiembro);
        mBotonAtras = findViewById(R.id.botonHome);
        mEliminarMiembro = findViewById(R.id.eliminar);
        mNuevoMiembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuevoMiembroClick(v);
                /*Intent intent = new Intent(mContext, CrearMiembroActivity.class);
                startActivity(intent);*/
            }
        });
        mBotonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuActivity.class);
                startActivity(intent);
            }
        });

        // item demo
        //DatabaseHelper.getInstance().addMiembro(miembro);

        miembrosLista = findViewById(R.id.miembrosLista);

        this.miembrosAdapter = new MiembrosAdapter(mContext, this.miembros);
        miembrosLista.setAdapter(this.miembrosAdapter);
    }

    /*private void returnMethod(){
        mBotonAtras = findViewById(R.id.botonAtras);
        mBotonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuActivity.class);
                startActivity(intent);
            }
        });
    }*/


    public void nuevoMiembroClick(View view) {
        Intent intent = new Intent(mContext, CrearMiembroActivity.class);
        startActivityForResult(intent, Constants.KEY_MIEMBRO);
        /*String miembroJson = intent.getExtras().getString(Constants.KEY_REGISTRAR_USUARIO);
        miembro = new Gson().fromJson(miembroJson, Miembro.class);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Cada vez que retorna a la activity actualizar la lista de miembros y actualizar el adapter notifyDataSetChanged
        this.miembros.clear();
        this.miembros.addAll(dbHelper.getMiembros());
        this.miembrosAdapter.notifyDataSetChanged();
        Log.e("MIEMBROS", ": " + new Gson().toJson(this.miembros));
    }
}