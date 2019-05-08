package bo.elite.tareasdivertidas;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class fichaPremioActivity extends AppCompatActivity {
    private Context mContext;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ficha_premio);
        mContext = this;
        initViews();
    }

    private void initViews() {
    }
}
