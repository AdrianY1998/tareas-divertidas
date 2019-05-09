package bo.elite.tareasdivertidas;

import com.google.gson.annotations.Expose;

public class MiembrosAEvaluar {
    @Expose
    private int icono;
    @Expose
    private String nombre;

    public void setNombre (String nombre){
        this.nombre = nombre;
    }
    public void setIcono(int icono){
        this.icono = icono;
    }
    public String getNombre (){
        return nombre;
    }
    public int getIcono() {return icono;}

}
