package bo.elite.tareasdivertidas;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import bo.elite.tareasdivertidas.db.DatabaseHelper;

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
        mImage = findViewById(R.id.imagenPremio);
        mEditar = findViewById(R.id.editar);

        mEliminar = findViewById(R.id.eliminar);
        mEliminar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String json = intent.getStringExtra(Constants.KEY_PREMIO_SELECTED);
                Premio premio = new Gson().fromJson(json, Premio.class);
                confirmarEliminar(premio);
            }
        });

    }
    private void datos(){
        Intent intent = getIntent();
        String json = intent.getStringExtra(Constants.KEY_PREMIO_SELECTED);
        Premio premio = new Gson().fromJson(json, Premio.class);
        mNombre.setText(premio.getNombrePremio());
        mPuntaje.setText(""+premio.getPuntaje());
        Drawable image = getResources().getDrawable(premio.getImage());
        mImage.setImageDrawable(image);

    }

    private void confirmarEliminar(final Premio premio){
        final Dialog dialogo = new Dialog(mContext);
        dialogo.setContentView(R.layout.confirmar_eliminar_premio);

        Button eliminar = dialogo.findViewById(R.id.eliminar);
        Button cancelar = dialogo.findViewById(R.id.cancelar);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(mContext);
                db.eliminarP(premio.getId());
                Intent intent2 = new Intent(mContext, PremiosActivity.class);
               startActivity(intent2);
               finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogo.dismiss();
            }
        });
        dialogo.setCancelable(false);
        dialogo.show();
    }
}
