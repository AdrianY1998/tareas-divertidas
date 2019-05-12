package bo.elite.tareasdivertidas;

import com.google.gson.annotations.Expose;

public class TareaAEvaluar {
    @Expose
    private int IDmiembro = 1;
    @Expose
    private int tarea;

    public int getIDmiembro(){ return IDmiembro;}
    public void setIDmiembro(int IDmiembro){
        this.IDmiembro = IDmiembro;
    }
    public int getTarea() {return tarea;}

    public void setTarea(int tarea){
        this.tarea = tarea;
    }
}
