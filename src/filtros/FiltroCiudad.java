package filtros;

import Sitio.SitioWeb;
import inmueble.Inmueble;

public class FiltroCiudad extends CriterioBusqueda {
	private String ciudad;

    public FiltroCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    @Override
    public Boolean cumple(Inmueble inmueble, SitioWeb s) {
        return inmueble.getCiudad().equals(ciudad);
    }
}
