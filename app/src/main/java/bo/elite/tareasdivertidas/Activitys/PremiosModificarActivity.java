package bo.elite.tareasdivertidas.Activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.Constants;
import bo.elite.tareasdivertidas.models.Premio;
import bo.elite.tareasdivertidas.R;
import bo.elite.tareasdivertidas.adapters.premiosAdapter;
import bo.elite.tareasdivertidas.db.DatabaseHelper;

public class PremiosModificarActivity extends AppCompatActivity {

    private Context mContext;

    private ImageView mBotonAtras;
    private List<Premio> premios = new ArrayList<>();
    private Premio premio;
    private ListView premiosLista;
    private bo.elite.tareasdivertidas.adapters.premiosAdapter premiosAdapter;

    private DatabaseHelper dbHelper;

    private Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_premios_seleccionar);
        mContext = this;

        dbHelper = new DatabaseHelper(mContext);
        this.premios = dbHelper.getPremios();

        initViews();

    }

    private void initViews() {
        mBotonAtras = findViewById(R.id.inicio);
        mBotonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(mContext, FichaMiembroActivity.class);
                //startActivity(intent);
                Intent intent = getIntent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
        premiosLista = findViewById(R.id.premiosVista);

        premiosAdapter = new premiosAdapter(mContext, premios);
        premiosLista.setAdapter(premiosAdapter);

        premiosLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Premio premio = premios.get(position);
                Intent intent = getIntent();
                intent.putExtra(Constants.KEY_PREMIO_SELECTED, new Gson().toJson(premio));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.premios.clear();
        this.premios.addAll(dbHelper.getPremios());
        this.premiosAdapter.notifyDataSetChanged();
        Log.e("Permios", ": " + new Gson().toJson(this.premios));
    }
}
