package filtros;

import java.util.ArrayList;
import java.util.List;

import Sitio.SitioWeb;
import inmueble.Inmueble;
import inmuebless.InmueblessREEMPLAZO;

public class FiltroPrecio implements Criterio  {
	private Double precioMin;
	private Double precioMax;
	
	 public FiltroPrecio(Double precioMin, Double precioMax) {
	        this.precioMin = precioMin;
	        this.precioMax = precioMax;
	    }
	 
	 @Override
	    public Boolean cumple(InmueblessREEMPLAZO p) {
	        Double precio = p.getPrecioBase();
	        boolean cumpleMin = (precioMin == null) || (precio >= precioMin);
	        boolean cumpleMax = (precioMax == null) || (precio <= precioMax);
	        return cumpleMin && cumpleMax;
	    }
	
	 
}
