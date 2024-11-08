package filtros;

import Sitio.SitioWeb;
import inmuebless.Inmuebless;

public class FiltroHuespedes implements Criterio {
	private int minHuespedes;

    public FiltroHuespedes(int minHuespedes) {
        this.minHuespedes = minHuespedes;
    }

    @Override
    public Boolean cumple(Inmuebless p) {
        return p.getHuespedes() >= minHuespedes;
    }
}
