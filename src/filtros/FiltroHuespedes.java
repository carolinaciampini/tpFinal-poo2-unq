package filtros;

import Sitio.SitioWeb;
import inmueble.Inmueble;

public class FiltroHuespedes implements Criterio {
	private int minHuespedes;

    public FiltroHuespedes(int minHuespedes) {
        this.minHuespedes = minHuespedes;
    }

    @Override
    public Boolean cumple(Inmueble p, Filtro f) {
        return p.getHuespedes() >= minHuespedes;
    }
}
