package bo.elite.tareasdivertidas.models;

import com.google.gson.annotations.Expose;

public class Tarea {

    @Expose
    private int pointTarea;

    @Expose
    private String nameTarea;

    @Expose
    private int imageTarea;


    public Tarea(int pointTarea, String nameTarea, int imageTarea) {
        this.pointTarea = pointTarea;
        this.nameTarea = nameTarea;
        this.imageTarea = imageTarea;
    }

    public int getPointTarea() {
        return pointTarea;
    }

    public void setId(int pointTarea) {
        this.pointTarea = pointTarea;
    }

    public String getNameTarea() {
        return nameTarea;
    }

    public void setNameTarea(String nameTarea) {
        this.nameTarea = nameTarea;
    }

    public int getImageTarea() {
        return imageTarea;
    }

    public void setImageTarea(int imageTarea) {
        this.imageTarea = imageTarea;
    }
}
