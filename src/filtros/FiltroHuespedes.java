package filtros;

import Sitio.SitioWeb;
import inmueble.Inmueble;

public class FiltroHuespedes extends CriterioBusqueda {
	private int minHuespedes;

    public FiltroHuespedes(int minHuespedes) {
        this.minHuespedes = minHuespedes;
    }

    @Override
    public Boolean cumple(Inmueble inmueble, SitioWeb s) {
        return inmueble.getHuespedes() >= minHuespedes;
    }
}
