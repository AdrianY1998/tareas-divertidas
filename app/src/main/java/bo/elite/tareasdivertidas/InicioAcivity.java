package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class InicioAcivity extends AppCompatActivity{
    private Context mContext;
    private ImageView mBotonComenzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_main);
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

