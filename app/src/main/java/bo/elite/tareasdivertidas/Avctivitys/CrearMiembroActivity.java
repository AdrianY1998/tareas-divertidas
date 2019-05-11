package bo.elite.tareasdivertidas.Avctivitys;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
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

import bo.elite.tareasdivertidas.Constants;
import bo.elite.tareasdivertidas.models.Miembro;
import bo.elite.tareasdivertidas.R;
import bo.elite.tareasdivertidas.db.DatabaseHelper;

public class CrearMiembroActivity extends AppCompatActivity {

    private LinearLayout padre;
    private Context mContext;
    private RelativeLayout imagenes;
    private RelativeLayout imagenContenedora;
    private LinearLayout botonesPerfil;
    private ImageView retornar;
    private ImageView fotoPerfil;
    private ImageView elegirFotoGaleria;
    private ImageView elegirFotoCamara;
    private TextView nombreTexto;
    private EditText mNombre;
    private TextView edadTexto;
    private EditText mEdad;
    private TextView emailTexto;
    private EditText email;
    private LinearLayout botones;
    private Button crearMiembro;
    private Button limpiar;
    private Uri photoURI;
    private String mCurrentPhotoPath;
    private Uri imageUri;


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


        RelativeLayout.LayoutParams relativeParams3 = new RelativeLayout.LayoutParams(500, 500);
        relativeParams3.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

        fotoPerfil = new ImageView(mContext);
        fotoPerfil.setImageResource(R.drawable.user);

        imagenes.addView(fotoPerfil, relativeParams3);
        //padre.addView(imagenes);

        RelativeLayout.LayoutParams relativeParams2 = new RelativeLayout.LayoutParams(200, 200);
        relativeParams2.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.ALIGN_BOTTOM);

        botonesPerfil = new LinearLayout(mContext);
        botonesPerfil.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(100, 100);
        elegirFotoGaleria = new ImageView(mContext);
        elegirFotoGaleria.setImageResource(R.drawable.galeria);
        elegirFotoGaleria.setPadding(5, 5, 5, 5);
        elegirFotoCamara = new ImageView(mContext);
        elegirFotoCamara.setImageResource(R.drawable.camara);
        elegirFotoCamara.setPadding(5,5,5,5);
        botonesPerfil.addView(elegirFotoCamara, linearParams);
        botonesPerfil.addView(elegirFotoGaleria, linearParams);
        imagenes.addView(botonesPerfil, relativeParams2);
        padre.addView(imagenes);
        //padre.addView(botonesPerfil);

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

    private void initViews() {
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
        elegirFotoGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirGaleria();
            }
        });
        elegirFotoCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cambiarFotoPerfilCamara();
            }
        });

    }

    private void datosLlenados() {
        if (mNombre.getText().toString().isEmpty()) {
            Toast.makeText(mContext, "Ingrese el usuario por favor", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mEdad.getText().toString().isEmpty()) {
            Toast.makeText(mContext, "Ingrese su edad por favor", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.getText().toString().isEmpty()) {
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
        miembro.setIcono(R.drawable.user);

        //Adicionar a la clase singleton
        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        dbHelper.addMiembro(miembro);

        String json = new Gson().toJson(miembro);
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_REGISTRAR_USUARIO, json);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void cambiarFotoPerfilCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, Constants.CODIGO_TRANSACCION_FOTO);

    }

    private void abrirGaleria(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, Constants.CODIGO_TRANSACCION_FOTO_2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.CODIGO_TRANSACCION_FOTO) {
            //Foto
            if (resultCode == RESULT_OK) {
                Log.e("Foto", "Valida");
                Bitmap thumbnail = data.getParcelableExtra("data"); // Obtenemos el Bitmap (imagen) capturada
                fotoPerfil.setImageBitmap(thumbnail);
            } else {
                Log.e("Foto cancelada", "Canceled");
            }
        } else if (requestCode == Constants.CODIGO_TRANSACCION_FOTO_2){
            if (resultCode == RESULT_OK){
            imageUri = data.getData();
            fotoPerfil.setImageURI(imageUri);
        } else {
            Log.e("Foto cancelada", "Canceled");
            }
        }
    }
}