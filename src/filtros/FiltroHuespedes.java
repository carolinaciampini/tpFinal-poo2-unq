package filtros;

import Sitio.SitioWeb;
import inmuebless.Inmueble;

public class FiltroHuespedes implements Criterio {
	private int minHuespedes;

    public FiltroHuespedes(int minHuespedes) {
        this.minHuespedes = minHuespedes;
    }

    @Override
    public Boolean cumple(Inmueble p) {
        return p.getHuespedes() >= minHuespedes;
    }
}
