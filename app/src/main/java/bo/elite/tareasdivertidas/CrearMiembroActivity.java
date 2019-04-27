package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class CrearMiembroActivity extends AppCompatActivity {

    private LinearLayout padre;
    private Context mContext;
    private LinearLayout imagenes;
    private ImageView retornar;
    private ImageView fotoPerfil;
    private EditText mNombre;
    private EditText mEdad;
    private EditText email;
    private LinearLayout botones;
    private Button crearMiembro;
    private Button limpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        //Padre
        padre = new LinearLayout(mContext);
        padre.setOrientation(LinearLayout.VERTICAL);
        //padre.setBackgroundResource(R.color.colorPrimaryDark);
        padre.setLayoutParams(new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, //Width
                ViewGroup.LayoutParams.MATCH_PARENT)); //Height
        padre.setPadding(25, 25, 25, 25);
        padre.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryDark));

        imagenes = new LinearLayout(mContext);
        //LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ancho, alto);
        imagenes.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, //Width
                ViewGroup.LayoutParams.WRAP_CONTENT));
        retornar = new ImageView(mContext);
        retornar.setImageResource(R.drawable.boton_atras);
        retornar.setLayoutParams(new RelativeLayout.LayoutParams(120,120));
        retornar.setPadding(5, 5, 5, 5);
        imagenes.addView(retornar);

        fotoPerfil = new ImageView(mContext);
        fotoPerfil.setImageResource(R.drawable.user);
        fotoPerfil.setLayoutParams(new LinearLayout.LayoutParams(360,360));
        imagenes.setGravity(Gravity.CENTER);
        imagenes.addView(fotoPerfil);
        padre.addView(imagenes);
        mNombre = new EditText(mContext);
        padre.addView(mNombre);

        mEdad = new EditText(mContext);
        mEdad.setInputType(InputType.TYPE_CLASS_NUMBER);
        int maxLength = 2;
        mEdad.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        padre.addView(mEdad);


        email = new EditText(mContext);
        email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        padre.addView(email);

        botones = new LinearLayout(mContext);
        botones.setOrientation(LinearLayout.HORIZONTAL);


        crearMiembro = new Button(mContext);
        crearMiembro.setText(getString(R.string.enviar));
        crearMiembro.setLayoutParams(new LinearLayout.LayoutParams(
                0, //Width
                ViewGroup.LayoutParams.MATCH_PARENT,
                1)); //Height
        botones.addView(crearMiembro);

        limpiar = new Button(mContext);
        limpiar.setText(getString(R.string.limpiar));
        limpiar.setLayoutParams(new LinearLayout.LayoutParams(
                0, //Width
                ViewGroup.LayoutParams.MATCH_PARENT,
                1)); //Height
        botones.addView(limpiar);
        padre.addView(botones);
        setContentView(padre);
        initViews();
    }
    private void initViews(){
        retornar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MiembroActivity.class);
                startActivity(intent);
            }
        });

        crearMiembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datosLlenados();
            }
        });
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNombre.setText("");
                mEdad.setText("");
                email.setText("");
            }
        });
    }

    private void datosLlenados(){
        if(mNombre.getText().toString().isEmpty()){
            Toast.makeText(mContext, "Ingrese el usuario por favor", Toast.LENGTH_SHORT).show();
            return;
        }
        if(mEdad.getText().toString().isEmpty()){
            Toast.makeText(mContext, "Ingrese su edad por favor", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.getText().toString().isEmpty()){
            Toast.makeText(mContext, "Ingrese su correo electrónico por favor", Toast.LENGTH_SHORT).show();
            return;
        }
        Miembro miembro = new Miembro();
        miembro.setNombre(mNombre.getText().toString());
        miembro.setEdad(Integer.parseInt(mEdad.getText().toString()));
        miembro.setCorreoElectronico(email.getText().toString());

        String json = new Gson().toJson(miembro);

        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_REGISTRAR_USUARIO, json);
        setResult(RESULT_OK, intent); //OK: funciono, intent --> retornando el valor
        finish();
    }
}