package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.adapters.MiembrosAdapter;

public class MiembroActivity extends AppCompatActivity {
    private static final String LOG = MiembroActivity.class.getName();
    private Context mContext;
    private List<Miembro> miembros = new ArrayList<>();
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
        Miembro miembro = new Miembro();
        miembro.setNombre("Luciana");
        miembro.setEdad(19);
        miembro.setCorreoElectronico("luciananunezl99@gmail.com");
        miembro.setIcono(R.drawable.user);
        miembros.add(miembro);

        miembrosLista = findViewById(R.id.miembrosLista);

        miembrosAdapter = new MiembrosAdapter(mContext, miembros);
        miembrosLista.setAdapter(miembrosAdapter);
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
        miembro = new Gson().fromJson(miembroJson, Miembro.class);
        Toast.makeText(mContext, miembro.getNombre(), Toast.LENGTH_LONG);*/
    }
}