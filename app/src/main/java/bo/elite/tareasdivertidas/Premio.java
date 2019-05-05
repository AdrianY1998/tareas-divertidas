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

    public premio(int puntaje, String nombrePremio, int image, int id) {
        this.puntaje = puntaje;
        this.nombrePremio = nombrePremio;
        this.image = image;
        this.id = id;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int pointTarea) {
        this.puntaje = puntaje;
    }

    public String getNombrePremio() {
        return nombrePremio;
    }

    public void setNombreTarea(String nameTarea) {
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
}
