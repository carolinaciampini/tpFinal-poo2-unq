package filtros;

import java.util.ArrayList;
import java.util.List;

import Sitio.SitioWeb;
import inmueble.Inmueble;

public class FiltroPrecio implements Criterio  {
	private Double precioMin;
	private Double precioMax;
	
	 public FiltroPrecio(Double precioMin, Double precioMax) {
	        this.precioMin = precioMin;
	        this.precioMax = precioMax;
	    }
	 
	 @Override
	    public Boolean cumple(Inmueble inmueble, Filtro f) {
	        Double precio = inmueble.precioSugeridoPara(f.getFechaEntrada(), f.getFechaSalida());
	        boolean cumpleMin =  precio >= precioMin;
	        boolean cumpleMax =  precio <= precioMax;
	        return cumpleMin && cumpleMax;
	    }
	
	 
}
