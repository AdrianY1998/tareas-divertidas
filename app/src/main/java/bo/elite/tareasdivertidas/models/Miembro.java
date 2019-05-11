package bo.elite.tareasdivertidas.models;

import android.widget.ImageView;

import com.google.gson.annotations.Expose;

import bo.elite.tareasdivertidas.models.Tarea;

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
    @Expose
    private int puntaje = 0;
    @Expose
    private int IDpremio = 1;

    public int getIDPremio(){ return IDpremio;}
    public void setIDPremio(int IDpremio){
        this.IDpremio = IDpremio;
    }
    public int getPuntaje(){
        return puntaje;
    }
    public void setPuntaje(int puntaje){
        this.puntaje += puntaje;
    }
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
