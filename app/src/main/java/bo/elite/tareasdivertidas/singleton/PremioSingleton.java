package bo.elite.tareasdivertidas.singleton;


import java.util.ArrayList;
import java.util.List;

import bo.elite.tareasdivertidas.Premio;

public class PremioSingleton {
    private static PremioSingleton instance = new PremioSingleton();
    private List<Premio> premios;

    public PremioSingleton() {
        this.premios = new ArrayList<>();
    }

    public static PremioSingleton getInstance() {
        return instance;
    }

    public void add (Premio premio) {
        this.premios.add(premio);
    }

    public List<Premio> getPremios() {
        return this.premios;
    }
}
