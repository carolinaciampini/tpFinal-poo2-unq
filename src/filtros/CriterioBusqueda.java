package filtros;

import java.util.ArrayList;
import java.util.List;

import Sitio.SitioWeb;
import inmueble.Inmueble;

public abstract class CriterioBusqueda {
	
	  public abstract Boolean cumple(Inmueble inmueble, SitioWeb sitio);
	
	public List<Inmueble> aplicarFiltro(SitioWeb s) {
		List<Inmueble> inmueblesFiltrados = new ArrayList<>();
        for (Inmueble inmueble : s.getInmuebles()) {
            if (this.cumple(inmueble, s)) {
                inmueblesFiltrados.add(inmueble);
            }
        }
        return inmueblesFiltrados;
    }
}
