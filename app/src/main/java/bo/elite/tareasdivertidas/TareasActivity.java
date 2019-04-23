package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class TareasActivity extends AppCompatActivity {

    private Context mContext;

    private ImageView mBotonAtras;
    private ImageView mNuevaTarea;
    private ImageView mEditarTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.asignacion_tareas);
        mContext =this;

        initViews();
        addEveents();
    }

    private void initViews(){
        mBotonAtras = findViewById(R.id.botonAtras);
        mNuevaTarea = findViewById(R.id.nuevaTarea);
        mEditarTarea= findViewById(R.id.editar);
    }

    private  void addEveents(){
        mBotonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuActivity.class);
                startActivity(intent);
            }
        });

        mNuevaTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NuevaTarea.class);
                startActivity(intent);
            }
        });

        /*mEditarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, //);
                startActivity(intent);
            }
        });*/
    }

    /*public void nuevaTareaClick (View view){
        Intent intent = new Intent(mContext, NuevaTarea.class);
        startActivity(intent);
    }*/
}
