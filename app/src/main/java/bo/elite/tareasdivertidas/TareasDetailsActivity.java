package bo.elite.tareasdivertidas;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import bo.elite.tareasdivertidas.db.DatabaseHelper;
import bo.elite.tareasdivertidas.models.Tarea;

public class TareasDetailsActivity extends AppCompatActivity {
    private Context mContext;

    private ImageView mBotonAtras;
    private ImageView mEliminar;
    private ImageView imagenTarea;
    private TextView nombreTarea;
    private TextView puntajeTarea;

    private Gson gson = new Gson();

    private int confirm;
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
        deleteTarea(tarea);
    }

    private void initViews() {
        this.mBotonAtras = findViewById(R.id.botonAtras);
        this.imagenTarea= findViewById(R.id.imagenTarea);
        this.nombreTarea = findViewById(R.id.nombrePremio);
        this.puntajeTarea = findViewById(R.id.puntajeTarea);
        this.mEliminar = findViewById(R.id.eliminar);

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

    private void deleteTarea(final Tarea tarea){
        mEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmDelete(tarea);
            }
        });
    }

    private void confirmDelete(final Tarea tarea){
        final Dialog dialogo = new Dialog(mContext);
        dialogo.setContentView(R.layout.layout_confirmacion_borrar);

        Button eliminar = dialogo.findViewById(R.id.eliminarButton);
        Button cancelar = dialogo.findViewById(R.id.cancelarButton);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(mContext);
                dbHelper.deleteT(tarea.getId());

                String json = new Gson().toJson(tarea);
                Intent intent = new Intent();
                intent.putExtra(Constants.TAREA_ELIMINADA, json);
                setResult(RESULT_OK, intent);
                finish();
                /*
                confirm = 1;
                dialogo.dismiss();*/
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm = 0;
                dialogo.dismiss();
            }
        });
        dialogo.setCancelable(false);
        dialogo.show();
    }

    private void fillTareaData(Tarea tarea) {
        this.imagenTarea.setImageResource(tarea.getImageTarea());
        this.nombreTarea.setText(tarea.getNameTarea());
        this.puntajeTarea.setText(String.valueOf(tarea.getPointTarea()));
    }
}
