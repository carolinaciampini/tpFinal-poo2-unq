package filtros;

import java.util.ArrayList;
import java.util.List;

import Sitio.SitioWeb;
import inmueble.Inmueble;

public class FiltroPrecio extends CriterioBusqueda  {
	private Double precioMin;
	private Double precioMax;
	
	 public FiltroPrecio(Double precioMin, Double precioMax) {
	        this.precioMin = precioMin;
	        this.precioMax = precioMax;
	    }
	 
	 @Override
	    public Boolean cumple(Inmueble inmueble, SitioWeb s) {
	        Double precio = inmueble.getPrecio();
	        boolean cumpleMin = (precioMin == null) || (precio >= precioMin);
	        boolean cumpleMax = (precioMax == null) || (precio <= precioMax);
	        return cumpleMin && cumpleMax;
	    }
	
	 
}
