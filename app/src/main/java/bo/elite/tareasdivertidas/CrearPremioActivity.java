package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import bo.elite.tareasdivertidas.db.DatabaseHelper;


public class CrearPremioActivity extends AppCompatActivity{
    private static final String LOG = PremiosActivity.class.getName();
    private Context mContext;

    private ImageView mCrear;
    private ImageView mAtras;
    private ImageView mLimpiar;
    private EditText mNombre;
    private EditText mPuntaje;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_premio);
        mContext = this;

        initViews();
    }
    private void initViews() {
        //dbHelper = new DatabaseHelper(mContext);
        mAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PremiosActivity.class);
                startActivity(intent);
            }
        });

        mCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datosLlenados();
            }
        });
        mLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNombre.setText("");
                mPuntaje.setText("");
            }
        });
    }
    private void datosLlenados() {

        if (mNombre.getText().toString().isEmpty()) {
            Toast.makeText(mContext, "Ingrese un nombre para el premio por favor", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mPuntaje.getText().toString().isEmpty()) {
            Toast.makeText(mContext, "Ingrese el Puntaje por favor", Toast.LENGTH_SHORT).show();
            return;
        }
        Premio premio = new Premio();
        premio.setNombrePremio(mNombre.getText().toString());
        premio.setPuntaje(Integer.parseInt(mPuntaje.getText().toString()));

        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        dbHelper.addPremio(premio);

        String json = new Gson().toJson(Premio);
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_CREAR_PREMIO, json);
        setResult(RESULT_OK, intent);
        finish();
    }
}
