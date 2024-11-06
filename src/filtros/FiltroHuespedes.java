package filtros;

import Sitio.SitioWeb;
import inmueble.Inmueble;
import posteo.Posteo;

public class FiltroHuespedes implements Criterio {
	private int minHuespedes;

    public FiltroHuespedes(int minHuespedes) {
        this.minHuespedes = minHuespedes;
    }

    @Override
    public Boolean cumple(Posteo p) {
        return p.getHuespedes() >= minHuespedes;
    }
}
