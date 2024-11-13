package filtros;

import Sitio.SitioWeb;
import inmueble.Inmueble;

public class FiltroHuespedes implements Criterio {
	private int minHuespedes;

    public FiltroHuespedes(int minHuespedes) {
        this.minHuespedes = minHuespedes;
    }

    @Override
    public Boolean cumple(Inmueble inmueble, Filtro filtro) {
        return inmueble.getHuespedes() >= minHuespedes;
    }
}
