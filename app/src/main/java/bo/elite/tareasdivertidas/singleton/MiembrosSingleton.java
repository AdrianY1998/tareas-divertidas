package bo.elite.tareasdivertidas.singleton;

import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.Miembro;
import bo.elite.tareasdivertidas.db.DatabaseHelper;

/**
 * La clase Miembros singleton nos ayuda a tener una sola instancia para todas las clases de la app
 * Esta es una solucion temporal pues mas adelante veremos Bases de datos
 */
public class MiembrosSingleton {

    private static MiembrosSingleton instance = new MiembrosSingleton();
    private List<Miembro> miembros;

    /**
     * Constructor inicia la lista de miembros
     */
    public MiembrosSingleton() {
        this.miembros = new ArrayList<>();
    }

    /**
     * Retorna una instancia en comun para toda la App
     *
     * @return
     */
    public static MiembrosSingleton getInstance() {
        return instance;
    }

    /**
     * Adiciona un nuevo miembro
     *
     * @param miembro
     */
    public void addMiembro(Miembro miembro) {
        this.miembros.add(miembro);

    }

    /**
     * Retorna la lista de todos los miembros
     *
     * @return
     */
    public List<Miembro> getMiembros() {
        return this.miembros;
    }
}
