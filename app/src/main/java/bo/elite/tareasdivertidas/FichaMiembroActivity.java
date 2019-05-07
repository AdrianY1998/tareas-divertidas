package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class FichaMiembroActivity extends AppCompatActivity {
    private Context mContext;
    private ImageView retornar;
    private ImageView fotoPerfil;
    private TextView fichaTitulo;
    private TextView nombre;
    private TextView edadTitle;
    private TextView edad;
    private TextView puntajeTitle;
    private TextView puntaje;
    private TextView objetivoTitle;
    private ImageView imagenPremio;
    private TextView nombrePremio;
    private TextView necesitaTitle;
    private TextView puntajePremio;
    private TextView textoPuntos;
    private ImageView editarMiembro;
    private ImageView cambiarObjetivo;
    private ImageView eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ficha_miembro);
        mContext = this;
        initViews();
        addEvents();
    }

    private void initViews(){
        retornar = findViewById(R.id.botonAtras);
        fotoPerfil = findViewById(R.id.fotoPerfil);
        fichaTitulo = findViewById(R.id.fichaTitulo);
        nombre = findViewById(R.id.nombre);
        edadTitle = findViewById(R.id.edadTittle);
        edad = findViewById(R.id.edad);
        puntajeTitle = findViewById(R.id.puntajeTittle);
        puntaje = findViewById(R.id.puntaje);
        objetivoTitle = findViewById(R.id.objetivoTittle);
        imagenPremio = findViewById(R.id.imagenPremio);
        nombrePremio = findViewById(R.id.nombrePremio);
        necesitaTitle = findViewById(R.id.necesitaTittle);
        puntajePremio = findViewById(R.id.puntajePremio);
        textoPuntos = findViewById(R.id.textoPuntos);
        editarMiembro = findViewById(R.id.editarMiembro);
        cambiarObjetivo = findViewById(R.id.cambiarObjetivo);
        eliminar = findViewById(R.id.eliminar);
    }
    private void addEvents(){
        retornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MiembroActivity.class);
                startActivity(intent);
            }
        });

    }
}