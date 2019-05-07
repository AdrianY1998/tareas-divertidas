package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import bo.elite.tareasdivertidas.models.Tarea;

public class TareasDetailsActivity extends AppCompatActivity {
    private Context mContext;

    private ImageView mBotonAtras;
    private ImageView imagenTarea;
    private TextView nombreTarea;
    private TextView puntajeTarea;

    private Gson gson = new Gson();
    //d

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantilla_tareas);
        mContext = this;
        initViews();
        addEvents();

        Tarea tarea = this.gson.fromJson(getIntent().getStringExtra(Constants.TAREA_SELECTED), Tarea.class);

        fillTareaData(tarea);
    }

    private void initViews() {
        mBotonAtras = findViewById(R.id.botonAtras);
        this.imagenTarea= findViewById(R.id.imagenTarea);
        this.nombreTarea = findViewById(R.id.nombrePremio);
        this.puntajeTarea = findViewById(R.id.puntajeTarea);

    }

    private void addEvents(){
        mBotonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TareasActivity.class);
                startActivity(intent);
            }
        });
    }

    private void fillTareaData(Tarea tarea) {
        this.imagenTarea.setImageResource(tarea.getImageTarea());
        this.nombreTarea.setText(tarea.getNameTarea());
        this.puntajeTarea.setText(String.valueOf(tarea.getPointTarea()));
    }
}
