package filtros;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Sitio.SitioWeb;
import posteo.Posteo;

public class FilterManager {
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private String ciudad;
	private List<Criterio> criterios;
	
	
	public FilterManager(LocalDate fechaEntrada, LocalDate fechaSalida, String ciudad) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.ciudad = ciudad;
		this.criterios = new ArrayList<Criterio>();
		
		this.criterios.add(new FiltroFecha(fechaEntrada, fechaSalida));
        this.criterios.add(new FiltroCiudad(ciudad));
	}
	
	public void agregarFiltro(Criterio c){
		this.criterios.add(c);
	
	}
	
	public List<Posteo> filtrar(SitioWeb s){
		List<Posteo> resultados = s.getPosteos();

        
        for (Criterio criterio : criterios) {
            resultados.removeIf(posteo -> !criterio.cumple(posteo, s));
        }

        return resultados;
    	}
}
