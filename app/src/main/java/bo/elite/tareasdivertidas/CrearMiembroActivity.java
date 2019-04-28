package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
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
    private RelativeLayout imagenes;
    private ImageView retornar;
    private ImageView fotoPerfil;
    private TextView nombreTexto;
    private EditText mNombre;
    private TextView edadTexto;
    private EditText mEdad;
    private TextView emailTexto;
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
        padre.setLayoutParams(new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, //Width
                ViewGroup.LayoutParams.MATCH_PARENT)); //Height
        padre.setPadding(25, 25, 25, 25);
        padre.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryDark));

        imagenes = new RelativeLayout(mContext);
        imagenes.setLayoutParams(new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, //Width
                ViewGroup.LayoutParams.WRAP_CONTENT));

        RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(150, 150);
        relativeParams.addRule(RelativeLayout.ALIGN_PARENT_START);
        retornar = new ImageView(mContext);
        retornar.setImageResource(R.drawable.boton_atras);
        retornar.setPadding(5, 5, 5, 5);
        retornar.setLeft(5);
        imagenes.addView(retornar, relativeParams);


        RelativeLayout.LayoutParams relativeParams2 = new RelativeLayout.LayoutParams(500, 500);
        relativeParams2.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

        fotoPerfil = new ImageView(mContext);
        fotoPerfil.setImageResource(R.drawable.user);
        imagenes.addView(fotoPerfil, relativeParams2);
        padre.addView(imagenes);

        nombreTexto = new TextView(mContext);
        nombreTexto.setText("Nombre");
        padre.addView(nombreTexto);

        mNombre = new EditText(mContext);
        padre.addView(mNombre);

        edadTexto = new TextView(mContext);
        edadTexto.setText("Edad");
        padre.addView(edadTexto);

        mEdad = new EditText(mContext);
        mEdad.setInputType(InputType.TYPE_CLASS_NUMBER);
        int maxLength = 2;
        mEdad.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        padre.addView(mEdad);


        emailTexto = new TextView(mContext);
        emailTexto.setText("Correo Electrónico");
        padre.addView(emailTexto);

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

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            //email.setError("Ingrese un email valido por favor");
            AlertDialog.Builder dialogo = new AlertDialog.Builder(mContext);

            dialogo.setTitle("Email invalido");
            dialogo.setMessage("Ingrese un email valido por favor");
            dialogo.setIcon(R.drawable.warning);
            dialogo.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.e("OK", "Clicked");
                }
            });
            dialogo.show();

            return;
        }
        Miembro miembro = new Miembro();
        miembro.setNombre(mNombre.getText().toString());
        miembro.setEdad(Integer.parseInt(mEdad.getText().toString()));
        miembro.setCorreoElectronico(email.getText().toString());

        String json = new Gson().toJson(miembro);

        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_REGISTRAR_USUARIO, json);
        setResult(RESULT_OK, intent);
        finish();
    }
}