package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import bo.elite.tareasdivertidas.models.Tarea;

public class EvaluacionDeMiembroActivity extends AppCompatActivity {
    private Context mContext;
    private ImageView mAtras;
    private TextView mNombre;
    private ImageView mImage;

    private Gson gson = new Gson();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ficha_evaluacion);
        mContext = this;

        initViews();
        Miembro miembro = this.gson.fromJson(getIntent().getStringExtra(Constants.KEY_MIEMBRO_SELECCIONADO), Miembro.class);


        datosMiembro(miembro);
    }
    private void initViews() {
        mAtras = findViewById(R.id.botonAtras);
        mAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, EvaluacionActivity.class);
                startActivity(intent);
            }
        });
        mNombre = findViewById(R.id.nombreUser);
        mImage = findViewById(R.id.imagen);

    }
    private void datosMiembro(Miembro miembro){
        mNombre.setText(miembro.getNombre());
        Drawable image = getResources().getDrawable(miembro.getIcono());
        mImage.setImageDrawable(image);

    }




}
