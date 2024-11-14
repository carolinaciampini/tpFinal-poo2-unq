package filtros;

import inmueble.Inmueble;

public interface Criterio {
		
	public Boolean cumple(Inmueble inmueble, Filtro filtro);
		
}

