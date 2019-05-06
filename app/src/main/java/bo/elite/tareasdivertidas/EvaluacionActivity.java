package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class EvaluacionActivity extends AppCompatActivity {
    private Context mContext;
    private ImageView mBotonAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluacion);
        mContext = this;
        initViews();
    }

    private void initViews() {
        mBotonAtras=findViewById(R.id.botonHome);
        mBotonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MenuActivity.class);
                startActivity(intent);
            }
        });


    }
}
