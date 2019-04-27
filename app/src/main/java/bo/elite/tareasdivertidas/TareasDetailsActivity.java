package bo.elite.tareasdivertidas;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class TareasDetailsActivity extends AppCompatActivity {
    private ImageView imagenTarea;
    private TextView nombreTarea;
    private TextView puntajeTarea;
    //d

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantilla_tareas);
        initViews();
        fillTareaData();
    }

    private void initViews() {
        this.imagenTarea= findViewById(R.id.imagenTarea);
        this.nombreTarea = findViewById(R.id.nombreTarea);
        this.puntajeTarea = findViewById(R.id.puntajeTarea);

    }

    private void fillTareaData() {

    }
}
