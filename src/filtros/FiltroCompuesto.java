package filtros;

import java.util.ArrayList;
import java.util.List;

import Sitio.SitioWeb;
import inmueble.Inmueble;

public class FiltroCompuesto extends CriterioBusqueda {
	private List<CriterioBusqueda> filtros;

	public FiltroCompuesto() {
		this.filtros = new ArrayList<CriterioBusqueda>();
	};
	
	@Override
    public Boolean cumple(Inmueble inmueble, SitioWeb s) {
		return filtros.stream().allMatch(filtro -> filtro.cumple(inmueble, s));
    }

	
	public void agregarFiltro(CriterioBusqueda c) {
		this.filtros.add(c);
	}
	
	public void eliminarFiltro(CriterioBusqueda c) {
		this.filtros.remove(c);
	}
}
