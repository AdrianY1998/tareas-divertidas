package bo.elite.tareasdivertidas;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.models.Tarea;

class AsignacionTareas {
    private static final AsignacionTareas ourInstance = new AsignacionTareas();
    private List<Tarea> tareas;

    static AsignacionTareas getInstance() {
        return ourInstance;
    }

    private AsignacionTareas() {
        tareas = new ArrayList<>();
    }

    public void asignarTarea(Tarea tarea){
        tareas.add(tarea);
    }

    public void completarTarea(String nombreTarea){
        for(Tarea tarea : tareas){
            if(tarea.getNameTarea().equals(nombreTarea)){
                tareas.remove(tarea);
            }
        }
    }

    public List<Tarea> getTareas() {
        return tareas;
    }
}
