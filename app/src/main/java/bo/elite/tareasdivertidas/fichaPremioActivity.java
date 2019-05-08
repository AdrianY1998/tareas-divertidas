package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

public class fichaPremioActivity extends AppCompatActivity {
    private Context mContext;
    private ImageView mAtras;
    private TextView mNombre;
    private TextView mPuntaje;
    private ImageView mImage;
    private ImageView mEditar;
    private ImageView mEliminar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ficha_premio);
        mContext = this;

        initViews();
        datos();
    }

    private void initViews() {
        mAtras = findViewById(R.id.botonAtras);
        mAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PremiosActivity.class);
                startActivity(intent);
            }
        });
        mNombre = findViewById(R.id.nombrePremio);
        mPuntaje = findViewById(R.id.puntaje);
        mEditar = findViewById(R.id.editar);
        mEliminar = findViewById(R.id.eliminar);

    }
    private void datos(){
        Intent intent = getIntent();
        String json = intent.getStringExtra(Constants.KEY_MIEMBRO_SELECCIONADO);
        Premio premio = new Gson().fromJson(json, Premio.class);
        mNombre.setText(premio.getNombrePremio());
        mPuntaje.setText(""+premio.getPuntaje());

    }
}
