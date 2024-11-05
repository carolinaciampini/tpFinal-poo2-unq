package filtros;


import Sitio.SitioWeb;
import inmueble.Inmueble;

public interface Criterio {
		
	public  Boolean cumple(Inmueble inmueble, SitioWeb sitio);
		
}

