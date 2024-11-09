package filtros;

import Sitio.SitioWeb;
import inmuebless.Inmueble;

public class FiltroCiudad implements Criterio {
	private String ciudad;

    public FiltroCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    @Override
    public Boolean cumple(Inmueble p) {
        return p.getCiudad().equals(ciudad);
    }
}
