package filtros;

import Sitio.SitioWeb;
import inmueble.Inmueble;
import inmuebless.InmueblessREEMPLAZO;

public class FiltroHuespedes implements Criterio {
	private int minHuespedes;

    public FiltroHuespedes(int minHuespedes) {
        this.minHuespedes = minHuespedes;
    }

    @Override
    public Boolean cumple(InmueblessREEMPLAZO p) {
        return p.getHuespedes() >= minHuespedes;
    }
}
