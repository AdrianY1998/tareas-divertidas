package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MiembroActivity extends AppCompatActivity {
    private static final String LOG = MiembroActivity.class.getName();
    private Context mContext;
    private List<Miembros> miembros = new ArrayList<>();
    private ImageView mNuevoMiembro;
    private ImageView mEliminarMiembro;
    private Button mIniciarSesionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.miembros);
        mContext = this;
        initViews();
        //addEvents();
    }

    private void initViews() {
        mNuevoMiembro = findViewById(R.id.nuevoMiembro);
        mNuevoMiembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CrearMiembro.class);
                startActivity(intent);
            }
        });
    }


    public void nuevoMiembroClick(View view) {
        Intent intent = new Intent(mContext, CrearMiembro.class);
        startActivityForResult(intent, Constants.KEY_MIEMBRO);
    }

    /*@Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, CrearMiembro.class);
        startActivity(intent);
    }*/
}