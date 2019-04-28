package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
public class PremiosActivity extends AppCompatActivity {

    private static final String LOG = MiembroActivity.class.getName();
    private Context mContext;
    private List<Miembro> Premios = new ArrayList<>();
    private ImageView mNuevo;
    private ImageView mEliminar;
    private ImageView mBotonInicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.miembro_layout);
        mContext = this;
        initViews();
    }

    private void initViews() {
        mNuevo = findViewById(R.id.crear);
        mBotonInicio = findViewById(R.id.inicio);
        mEliminar = findViewById(R.id.eliminar);
        mNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(mContext, NuevoPremioActivity.class);
                startActivity(intent);*/
            }
        });
        mBotonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
