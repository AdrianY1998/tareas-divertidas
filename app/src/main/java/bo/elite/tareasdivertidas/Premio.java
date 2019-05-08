package bo.elite.tareasdivertidas;

import com.google.gson.annotations.Expose;

public class Premio {

    @Expose
    private int puntaje;

    @Expose
    private String nombrePremio;

    @Expose
    private int image;

    @Expose
    private int id;


    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int pointTarea) {
        this.puntaje = puntaje;
    }

    public String getNombrePremio() {
        return nombrePremio;
    }

    public void setNombrePremio(String nameTarea) {
        this.nombrePremio = nombrePremio;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
