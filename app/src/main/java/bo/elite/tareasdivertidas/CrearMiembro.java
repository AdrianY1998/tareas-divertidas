package bo.elite.tareasdivertidas;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class CrearMiembro extends AppCompatActivity {
    private ImageView mHombre;
    private ImageView mMujer;
    private ImageView mVolverAtras;
    private TextView nombre;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_miembro);
        mContext = this;

    }
}
