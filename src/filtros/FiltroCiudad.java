package filtros;

import Sitio.SitioWeb;
import inmueble.Inmueble;

public class FiltroCiudad implements Criterio {
	private String ciudad;

    public FiltroCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    @Override
    public Boolean cumple(Inmueble inmueble, Filtro filtro) {
        return inmueble.getCiudad().equals(ciudad);
    }
}
