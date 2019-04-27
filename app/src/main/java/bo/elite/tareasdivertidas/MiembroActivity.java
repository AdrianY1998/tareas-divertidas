package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MiembroActivity extends AppCompatActivity {
    private static final String LOG = MiembroActivity.class.getName();
    private Context mContext;
    private List<Miembro> miembros = new ArrayList<>();
    private ImageView mNuevoMiembro;
    private ImageView mEliminarMiembro;
    private ImageView mBotonAtras;


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
                Intent intent = new Intent(mContext, CrearMiembroActivity.class);
                startActivity(intent);
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
    }
}