package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;

import bo.elite.tareasdivertidas.db.DatabaseHelper;
import bo.elite.tareasdivertidas.models.Tarea;

public class NuevaTarea extends AppCompatActivity {

    private Context mContext;

    private ImageView mBotonAtras;
    private ImageView mCrear;

    private EditText mTarea;
    private EditText mPuntaje;
    private ImageView mImagen;

    //d
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tareas_disponibles);
        mContext =this;

        initViews();
        addEvents();
    }

    private void initViews(){
        mBotonAtras = findViewById(R.id.botonAtras);
        mCrear = findViewById(R.id.crear);
        mTarea = findViewById(R.id.nombreTarea);
        mPuntaje = findViewById(R.id.Puntaje);
        mImagen = findViewById(R.id.imagen);
    }

    private void addEvents(){
        mBotonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TareasActivity.class);
                startActivity(intent);
            }
        });

        mCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tarea tarea = new Tarea();
                tarea.setPointTarea(Integer.parseInt(mPuntaje.getText().toString()));
                tarea.setNameTarea(mTarea.getText().toString());
                tarea.setImageTarea(R.drawable.tarea_predeterminada);

                DatabaseHelper dbHelper = new DatabaseHelper(mContext);
                dbHelper.insertTarea(tarea);

                String json = new Gson().toJson(tarea);
                Intent intent = new Intent();
                intent.putExtra(Constants.TAREA_NUEVA, json);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
