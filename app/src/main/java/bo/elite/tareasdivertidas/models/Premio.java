package bo.elite.tareasdivertidas.models;

import com.google.gson.annotations.Expose;

import bo.elite.tareasdivertidas.R;

public class Premio {

    @Expose
    private int puntaje;

    @Expose
    private String nombrePremio;

    @Expose
    private int image;

    @Expose
    private int id;

    public Premio(){
        image = R.drawable.premio_predeterminado;
    }
    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public String getNombrePremio() {
        return nombrePremio;
    }

    public void setNombrePremio(String nombrePremio) {
        this.nombrePremio = nombrePremio;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
