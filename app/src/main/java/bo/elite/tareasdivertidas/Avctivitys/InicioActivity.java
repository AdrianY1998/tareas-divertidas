package bo.elite.tareasdivertidas.Avctivitys;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import bo.elite.tareasdivertidas.R;

public class InicioActivity extends AppCompatActivity{
    private static final String LOG = InicioActivity.class.getName();
    private Context mContext;
    private ImageView mBotonComenzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_inicio);
        mContext = this;
        initViews();
    }
    private void initViews() {
        mBotonComenzar = findViewById(R.id.boton);
        mBotonComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuActivity.class);
                startActivity(intent);
            }
        });
    }

}

