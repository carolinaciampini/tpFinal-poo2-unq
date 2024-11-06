package filtros;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<Posteo> filtrar(List<Posteo> posteos) {
	    return posteos.stream()
	            .filter(posteo -> criterios.stream().allMatch(c -> c.cumple(posteo)))  
	            .collect(Collectors.toList()); 
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public String getCiudad() {
		return ciudad;
	}

	public List<Criterio> getCriterios() {
		return criterios;
	}
}
