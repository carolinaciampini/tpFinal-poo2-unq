package filtros;


import Sitio.SitioWeb;
import inmueble.Inmueble;
import posteo.Posteo;

public interface Criterio {
		
	public  Boolean cumple(Posteo p, SitioWeb sitio);
		
}

