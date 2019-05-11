package bo.elite.tareasdivertidas.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import bo.elite.tareasdivertidas.Constants;
import bo.elite.tareasdivertidas.models.Miembro;
import bo.elite.tareasdivertidas.R;

public class EvaluacionDeMiembroActivity extends AppCompatActivity {
    private Context mContext;
    private ImageView mAtras;
    private TextView mNombre;
    private ImageView mImage;
    private ImageView mEvaluar;

    private Gson gson = new Gson();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ficha_premio);
        mContext = this;

        initViews();


        Miembro miembro = this.gson.fromJson(getIntent().getStringExtra(Constants.KEY_MIEMBRO_A_EVALUAR_SELECCIONADO), Miembro.class);
        //Tarea tarea = this.gson.fromJson(getIntent().getStringExtra(Constants.KEY_MIEMBRO_A_EVALUAR_SELECCIONADO), Tarea.class);

        datosMiembro(miembro);
        //datosTarea(tarea); (?
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
        mEvaluar = findViewById(R.id.evaluar);
        mNombre = findViewById(R.id.nombreUser);
        mImage = findViewById(R.id.imagen);

    }
    private void datosMiembro(Miembro miembro){
        mNombre.setText(miembro.getNombre());
        Drawable image = getResources().getDrawable(miembro.getIcono());
        mImage.setImageDrawable(image);

    }


}
