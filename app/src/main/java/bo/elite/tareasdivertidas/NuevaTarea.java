package bo.elite.tareasdivertidas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import bo.elite.tareasdivertidas.db.DatabaseHelper;
import bo.elite.tareasdivertidas.models.Tarea;

public class NuevaTarea extends AppCompatActivity {

    private Context mContext;

    private ImageView mBotonAtras;
    private ImageView mCrear;
    private ImageView mLimpiar;
    private ImageView mCamara;
    private ImageView mGaleria;

    private EditText mTarea;
    private EditText mPuntaje;
    private ImageView mImagen;

    private static int TAKE_PICTURE = 1;
    private static int SELECT_PICTURE = 2;
    private String name = "";

    private Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    private int code = TAKE_PICTURE;

    //d
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tareas_disponibles);
        mContext =this;

        name = Environment.getExternalStorageDirectory() + "/test.jpg";


        initViews();
        addEvents();
        options();
    }

    private void initViews(){
        mBotonAtras = findViewById(R.id.botonAtras);
        mCrear = findViewById(R.id.crear);
        mLimpiar = findViewById(R.id.Limpiar);
        mTarea = findViewById(R.id.nombreTarea);
        mPuntaje = findViewById(R.id.Puntaje);
        //mImagen = findViewById(R.id.imagen);
        //mCamara = findViewById(R.id.);
        mGaleria = findViewById(R.id.galeriaButton);


    }

    public void options(){
        /*mCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri output = Uri.fromFile(new File(name));
                intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
            }
        });*/

        mGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                code = SELECT_PICTURE;
                startActivityForResult(intent, code);
            }
        });

    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TAKE_PICTURE) {

        } else if (requestCode == SELECT_PICTURE){
            Uri selectedImage = data.getData();
            InputStream is;
            try {
                is = getContentResolver().openInputStream(selectedImage);
                BufferedInputStream bis = new BufferedInputStream(is);
                Bitmap bitmap = BitmapFactory.decodeStream(bis);
                mImagen = (ImageView)findViewById(R.id.imagen);
                mImagen.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {}
        }
    }

    private void addEvents(){
        mBotonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TareasActivity.class);
                startActivity(intent);
            }
        });

        mCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tarea tarea = new Tarea();
                tarea.setPointTarea(Integer.parseInt(mPuntaje.getText().toString()));
                tarea.setNameTarea(mTarea.getText().toString());
                tarea.setImageTarea(R.drawable.tarea_predeterminada);

                DatabaseHelper dbHelper = new DatabaseHelper(mContext);
                dbHelper.insertTarea(tarea);

                String json = new Gson().toJson(tarea);
                Intent intent = new Intent();
                intent.putExtra(Constants.TAREA_NUEVA, json);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        mLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTarea.setText("");
                mPuntaje.setText("");
            }
        });
    }
}
