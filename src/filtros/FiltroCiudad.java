package filtros;

import Sitio.SitioWeb;
import inmueble.Inmueble;
import inmuebless.InmueblessREEMPLAZO;

public class FiltroCiudad implements Criterio {
	private String ciudad;

    public FiltroCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    @Override
    public Boolean cumple(InmueblessREEMPLAZO p) {
        return p.getCiudad().equals(ciudad);
    }
}
