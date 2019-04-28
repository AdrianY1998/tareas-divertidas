package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.singleton.MiembrosSingleton;

public class MiembroActivity extends AppCompatActivity {
    private static final String LOG = MiembroActivity.class.getName();
    private Context mContext;
    private List<Miembro> miembros = MiembrosSingleton.getInstance().getMiembros();
    private ImageView mNuevoMiembro;
    private ImageView mEliminarMiembro;
    private ImageView mBotonAtras;
    private Miembro miembro;


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Cada vez que retorna a la activity actualizar la lista de miembros
        this.miembros = MiembrosSingleton.getInstance().getMiembros();
        Log.e("MIEMBROS", ": " + new Gson().toJson(this.miembros));
        //TODO Si se utiliza un adapter llamar adapter.notifyDatasetChanged() o iniciar de nuevo el adapter
    }
}