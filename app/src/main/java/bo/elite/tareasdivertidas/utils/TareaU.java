package bo.elite.tareasdivertidas.utils;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.R;
import bo.elite.tareasdivertidas.models.Tarea;

public class TareaU {
    public static List<Tarea> getTareas() {
        List<Tarea> tareas = new ArrayList<>();
        //d

        tareas.add(new Tarea(1,"cocinar", R.drawable.cocinar));
        tareas.add(new Tarea(2,"dar comida a mascota", R.drawable.dar_momida_mascota));
        tareas.add(new Tarea(3,"estudiar", R.drawable.estudiar));
        tareas.add(new Tarea(4,"ir al cine", R.drawable.ir_al_cine));
        tareas.add(new Tarea(5,"lavar ropa", R.drawable.lavar_ropa));
        tareas.add(new Tarea(6,"limpiar casa", R.drawable.limpiar_casa));
        tareas.add(new Tarea(8,"nuevo libro", R.drawable.nuevo_libro));
        tareas.add(new Tarea(9,"ordenar cuarto", R.drawable.ordenar_cuarto));
        tareas.add(new Tarea(10,"pasear perro", R.drawable.pasear_perro));
        tareas.add(new Tarea(11,"poner mesa", R.drawable.poner_mesa));
        tareas.add(new Tarea(12,"regar plantas", R.drawable.regar_plantas));
        tareas.add(new Tarea(13,"sacar basura", R.drawable.sacar_basura));
        return tareas;
    }
}
