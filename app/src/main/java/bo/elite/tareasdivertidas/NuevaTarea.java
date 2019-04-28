package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class NuevaTarea extends AppCompatActivity {

    private Context mContext;

    private ImageView mBotonAtras;

    //d
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tareas_disponibles);
        mContext =this;

        initViews();
        addEvents();

        //AsignacionTareas.getInstance().getTareas();
        //AsignacionTareas.getInstance().asignarTarea(new Tarea());
        //AsignacionTareas.getInstance().completarTarea("Cocinar");
    }

    private void initViews(){
        mBotonAtras = findViewById(R.id.botonAtras);
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

    /*public void botonAtrasClick (View view){
        Intent intent = new Intent(mContext, TareasActivity.class);
        startActivity(intent);
    }*/
}
