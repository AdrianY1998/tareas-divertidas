package bo.elite.tareasdivertidas;

import android.widget.ImageView;

import com.google.gson.annotations.Expose;

public class Miembro {
    @Expose
    private int icono;
    @Expose
    private int edad;
    @Expose
    private String nombre;
    @Expose
    private String genero;
    @Expose
    private String correoElectronico;
    @Expose
    private int id;


    public void setNombre (String nombre){
        this.nombre = nombre;
    }
    public void setGenero (String genero){
        this.genero = genero;
    }
    public void setEdad (int edad){
        this.edad = edad;
    }
    public void setCorreoElectronico(String correoElectronico){
        this.correoElectronico = correoElectronico;
    }
    public String getCorreoElectronico(){return correoElectronico;}
    public String getNombre (){
        return nombre;
    }

    public int getEdad(){ return edad;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcono() {return icono;}

    public void setIcono(int icono){
        this.icono = icono;
    }
}
