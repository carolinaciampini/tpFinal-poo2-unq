package filtros;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Sitio.SitioWeb;
import inmueble.Inmueble;

public class Filtro {
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private List<Criterio> criterios;
	
	
	public Filtro(LocalDate fechaEntrada, LocalDate fechaSalida, String ciudad) {
		super();
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.criterios = this.filtrosObligatorios(ciudad, fechaEntrada, fechaSalida);
	}
	
	public List<Criterio> filtrosObligatorios(String ciudad, LocalDate fechaEntrada, LocalDate fechaSalida){
		List<Criterio> lista = new ArrayList<Criterio>();
		lista.add(new FiltroFecha(fechaEntrada, fechaSalida));
		lista.add(new FiltroCiudad(ciudad));
		return lista;
		
	}
	
	public void agregarFiltro(Criterio c){
		this.criterios.add(c);
	
	}
	
	public List<Inmueble> filtrar(List<Inmueble> inmueble) {
	    return inmueble.stream()
	            .filter(posteo -> criterios.stream().allMatch(c -> c.cumple(posteo, this)))  
	            .toList();
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

}
