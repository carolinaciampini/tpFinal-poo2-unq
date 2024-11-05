package filtros;

import Sitio.SitioWeb;
import inmueble.Inmueble;
import posteo.Posteo;

public class FiltroCiudad implements Criterio {
	private String ciudad;

    public FiltroCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    @Override
    public Boolean cumple(Posteo p, SitioWeb s) {
        return p.getCiudad().equals(ciudad);
    }
}
