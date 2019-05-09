package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import bo.elite.tareasdivertidas.db.DatabaseHelper;

public class FichaEditarActivity extends AppCompatActivity {
    private Context mContext;
    private ImageView retornar;
    private ImageView fotoPerfil;
    private EditText nombre;
    private EditText edad;
    private TextView puntaje;;
    private ImageView imagenPremio;
    private TextView nombrePremio;
    private TextView puntajePremio;
    private TextView textoPuntos;
    private ImageView modificar;
    private Miembro miembro;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ficha_editar_miembro);
        mContext = this;
        initViews();
        addEvents();
        receiveData();
    }

    private void initViews(){
        retornar = findViewById(R.id.botonAtras);
        fotoPerfil = findViewById(R.id.fotoPerfil);
        nombre = findViewById(R.id.nombre);
        edad = findViewById(R.id.edad);
        puntaje = findViewById(R.id.puntaje);
        imagenPremio = findViewById(R.id.imagenPremio);
        nombrePremio = findViewById(R.id.nombreMiembro);
        puntajePremio = findViewById(R.id.puntajePremio);
        textoPuntos = findViewById(R.id.textoPuntos);
        modificar = findViewById(R.id.modificar);
    }
    private void addEvents(){
        retornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FichaMiembroActivity.class);
                miembro.setNombre(nombre.getText().toString());
                miembro.setEdad(Integer.parseInt(edad.getText().toString()));
                String json = new Gson().toJson(miembro);
                intent.putExtra(Constants.KEY_MIEMBRO_SELECCIONADO, json);
                setResult(RESULT_OK, intent);

                DatabaseHelper dbHelper = new DatabaseHelper(mContext);
                dbHelper.modifyMiembro(id, nombre.getText().toString(), Integer.parseInt(edad.getText().toString()));
                finish();
            }
        });
    }

    private void receiveData(){
        Intent intent = getIntent();
        String json = intent.getStringExtra(Constants.KEY_MIEMBRO_SELECCIONADO);
        miembro = new Gson().fromJson(json, Miembro.class);
        nombre.setText(miembro.getNombre());
        edad.setText(""+miembro.getEdad());
        id = miembro.getId();

    }
}