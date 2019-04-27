package bo.elite.tareasdivertidas.utils;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.R;
import bo.elite.tareasdivertidas.models.Tarea;

public class TareaU {
    public static List<Tarea> getTareas() {
        List<Tarea> tareas = new ArrayList<>();
        //d

        tareas.add(new Tarea(1,"Cocinar", R.drawable.cocinar));
        tareas.add(new Tarea(2,"Dar comida a mascota", R.drawable.dar_momida_mascota));
        tareas.add(new Tarea(3,"Estudiar", R.drawable.estudiar));
        tareas.add(new Tarea(4,"Ir al cine", R.drawable.ir_al_cine));
        tareas.add(new Tarea(5,"Lavar ropa", R.drawable.lavar_ropa));
        tareas.add(new Tarea(6,"Limpiar casa", R.drawable.limpiar_casa));
        tareas.add(new Tarea(8,"Nuevo libro", R.drawable.nuevo_libro));
        tareas.add(new Tarea(9,"Ordenar cuarto", R.drawable.ordenar_cuarto));
        tareas.add(new Tarea(10,"Pasear perro", R.drawable.pasear_perro));
        tareas.add(new Tarea(11,"Poner mesa", R.drawable.poner_mesa));
        tareas.add(new Tarea(12,"Regar plantas", R.drawable.regar_plantas));
        tareas.add(new Tarea(13,"Sacar basura", R.drawable.sacar_basura));
        return tareas;
    }
}
