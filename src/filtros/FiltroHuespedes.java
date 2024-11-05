package filtros;

import Sitio.SitioWeb;
import inmueble.Inmueble;

public class FiltroHuespedes implements Criterio {
	private int minHuespedes;

    public FiltroHuespedes(int minHuespedes) {
        this.minHuespedes = minHuespedes;
    }

    @Override
    public Boolean cumple(Posteo p, SitioWeb s) {
        return p.getHuespedes() >= minHuespedes;
    }
}
