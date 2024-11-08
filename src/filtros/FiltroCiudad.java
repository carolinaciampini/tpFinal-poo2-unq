package filtros;

import Sitio.SitioWeb;
import inmuebless.Inmuebless;

public class FiltroCiudad implements Criterio {
	private String ciudad;

    public FiltroCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    @Override
    public Boolean cumple(Inmuebless p) {
        return p.getCiudad().equals(ciudad);
    }
}
