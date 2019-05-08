package bo.elite.tareasdivertidas;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class fichaPremioActivity extends AppCompatActivity {
    private Context mContext;
    private ImageView mAtras;
    private TextView mNombre;
    private TextView mPuntaje;
    private ImageView mEditar;
    private ImageView mEliminar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ficha_premio);
        mContext = this;

        initViews();
    }

    private void initViews() {
        mAtras = findViewById(R.id.botonAtras);

        mNombre = findViewById(R.id.nombrePremio);
        mPuntaje = findViewById(R.id.puntaje);
        mEditar = findViewById(R.id.editar);
        mEliminar = findViewById(R.id.eliminar);

    }
}
