package Sitio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import categoria.Categoria;
import excepciones.PropietarioNoRegistradoExcepcion;
import excepciones.UsuarioYaExistenteException;
import filtros.Filtro;
import inmueble.Inmueble;
import usuarios.Usuario;
import reserva.Reserva;
import servicio.Servicio;
import tipoInmueble.TipoInmueble;


public class SitioWeb {
	private List<Usuario> usuarios;
	private List <Inmueble> inmuebles;
	private Map<Usuario, LocalDate> usuariosFechaRegistro;
	private List<TipoInmueble> tiposInmuebles;
	private List<Servicio> serviciosInmueble;
	private List<Categoria> usuariosCategoria;
	private List<Categoria> inmueblesCategoria;

	
	public SitioWeb () {
		this.usuarios = new ArrayList <>() ;
		this.inmuebles = new ArrayList<>();
		this.usuariosFechaRegistro = new HashMap<>();
		this.tiposInmuebles = new ArrayList<>();
		this.serviciosInmueble = new ArrayList<>();
		this.inmueblesCategoria = new ArrayList<>();
		this.usuariosCategoria = new ArrayList<>();


	}
	
	 public Integer tiempoQueEsUsuario(Usuario usuario) {
		 LocalDate fecha1 = usuarioFechaRegistro(usuario);
		 LocalDate fecha2 = LocalDate.now();
		 int diasDeDiferencia = (int) ChronoUnit.DAYS.between(fecha1, fecha2);
		 return diasDeDiferencia;
	 	}
	 	
	 	public Integer vecesQueAlquiloInmueble(Usuario usuario, Inmueble inmueble) {
	 	Integer contador = 0;
	 	for (Reserva reserva : obtenerReservasDeUsuario(usuario)) {
	 			if (reserva.getInmueble().equals(inmueble)) {
	 			contador ++;
	 		}	
	 	}
		return contador;
	 }
	 	
	 
	 public Integer vecesQueAlquilo(Usuario usuario) {
	 	return obtenerReservasDeUsuario(usuario).size();
	 }
	 
	 
	 public List <Inmueble> inmueblesAlquiladosPor (Usuario usuario) {
	 	List <Inmueble> inmueblesAlq = new ArrayList <>();
	 	for (Reserva reserva : obtenerReservasDeUsuario(usuario)) {
	 		inmueblesAlq.add(reserva.getInmueble());
	 	}
	 	return inmueblesAlq;
	 }
	 
	  
	 
	public List<Categoria> getUsuariosCategoria() {
		return usuariosCategoria;
	}
	
	public void agregarCategoriaUsuario (Categoria categoria) {
		usuariosCategoria.add(categoria);
	}
	
	public void sacarCategoriaUsuario (Categoria categoria) {
		usuariosCategoria.remove(categoria);
	}
	
	public List<Categoria> getUsuarioCategoria() {
		return usuariosCategoria;
	}
	
	public List<Categoria> getInmueblesCategoria() {
		return inmueblesCategoria;
	}
	
	public void agregarCategoriaInmueble (Categoria categoria) {
		inmueblesCategoria.add(categoria);
	}
	
	public void sacarCategoriaInmueble (Categoria categoria) {
		inmueblesCategoria.remove(categoria);
	}
	
	public void sacarServicio (Servicio servicio) {
		serviciosInmueble.remove(servicio);
	}
	
	public void agregarServicio(Servicio servicio) {
		serviciosInmueble.add(servicio);
	}
	
	public List <Servicio> getServicios() {
		return serviciosInmueble;
	}
	
	public void sacarTipoDeInmueble (TipoInmueble tipo) {
		tiposInmuebles.remove(tipo);
	}
	
	public void agregarTipoDeInmueble(TipoInmueble tipo) {
		tiposInmuebles.add(tipo);
	}
	
	public List <TipoInmueble> getTiposInmuebles() {
		return tiposInmuebles;
	}
	
	
	public List<Reserva> obtenerReservasDeUsuario(Usuario usuario) {
	    List<Reserva> reservasDelUsuario = new ArrayList<>();
	    
	    for (Inmueble inmueble : inmuebles) {
	        for (Reserva reserva : inmueble.getReservas()) {
	            if (reserva.getInquilino().equals(usuario)) {
	                reservasDelUsuario.add(reserva);
	            }
	    }
	  }
		return reservasDelUsuario;
	}
	
	public Integer obtenerCantidadReservasDeUsuario (Usuario usuario) {
		return obtenerReservasDeUsuario(usuario).size();
	}
	
	
	public List<Usuario> topTenInquilinos() {
	    return inmuebles.stream()
	        .flatMap(inmueble -> inmueble.getReservas().stream())
	        .map(Reserva::getInquilino)
	        .distinct()
	        .sorted(Comparator.comparingInt(this::obtenerCantidadReservasDeUsuario).reversed())
	        .limit(10)
	        .toList();
	}
	
	
	public List<Inmueble> inmueblesLibres(){
		List<Inmueble> libres = new ArrayList<>();
		
		for (Inmueble inmueble : inmuebles) {
			if (inmueble.getReservas().isEmpty()) {
				libres.add(inmueble);
			}
		}
		return libres;
	}
	
	
	public Double tasaDeOcupacion() {
		return (double) cantidadDeInmueblesAlquilados() / getInmuebles().size();
	}
	
	public int cantidadDeInmueblesAlquilados() {
		int inmueblesAlquilados = 0;
        for (Inmueble inmueble : inmuebles) {
            if (!inmueble.getReservas().isEmpty()) {
                inmueblesAlquilados++;
            }
        }
        return inmueblesAlquilados;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void addUsuario (Usuario usuario) throws UsuarioYaExistenteException{
		 if (estaRegistrado(usuario)) {
	            throw new UsuarioYaExistenteException();
	        }
		 usuarios.add(usuario);
		 usuariosFechaRegistro.put(usuario, LocalDate.now());
	}
	
	public LocalDate usuarioFechaRegistro (Usuario usuario) {
		return usuariosFechaRegistro.get(usuario);
	}

	
	public void removeUsuario (Usuario usuario) {
		usuarios.remove(usuario);
		 usuariosFechaRegistro.remove(usuario);
	}
	
	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}
	
	
    public boolean estaRegistrado(Usuario usuario) {
        return usuarios.stream().anyMatch(u -> u.getEmail().equals(usuario.getEmail()));
    }
    
	
    public void darDeAltaInmueble (Inmueble inmueble) throws PropietarioNoRegistradoExcepcion {
		if (estaRegistrado(inmueble.getPropietario())) {
            inmuebles.add(inmueble);
        } else {
        	throw new PropietarioNoRegistradoExcepcion();
        }
	}

	
	public List<Reserva> getReservasDe(Inmueble inmueble) {
		return inmueble.getReservas();
		
		
	}
	
	
	
	public List<Reserva> getReservasFuturasDe(Usuario usuario){
		List<Reserva> reservasFuturas = new ArrayList<>();
	    LocalDate hoy = LocalDate.now();
	    
	    
	    for (Inmueble inmueble : inmuebles) {
	       
	        reservasFuturas.addAll(inmueble.getReservas().stream()
	            .filter(reserva -> reserva.esMismoInquilino(usuario) && reserva.getFechaEntrada().isAfter(hoy))
	            .toList());
	    }
	    return reservasFuturas;
		
	}
	
	
	
	public List<Reserva> getTodasLasReservasRealizadasDe(Usuario usuario) {
		
		List<Reserva> todasLasReservas = new ArrayList<>();
	    
	    for (Inmueble inmueble : inmuebles) {
	    	 todasLasReservas.addAll(inmueble.getReservas().stream()
	            .filter(reserva -> reserva.esMismoInquilino(usuario))
	            .toList());  
	        ;
	    }
	    
	    return todasLasReservas;
	}
	
	public List<Reserva> getReservasDelUsuarioEnCiudad(Usuario usuario, String ciudad){
		 List<Reserva> reservasEnCiudad = new ArrayList<>();
		    
		    for (Inmueble inmueble : inmuebles) {
		        reservasEnCiudad.addAll(inmueble.getReservas().stream()
		            .filter(reserva -> reserva.esMismoInquilino(usuario) && inmueble.getCiudad().equals(ciudad))
		            .toList());
		    }
		    return reservasEnCiudad;
		
	}
	
	public List<String> getCiudadesDeReservasDel(Usuario usuario){
		List<String> ciudades = new ArrayList<>();
	    
	    for (Inmueble inmueble : inmuebles) {
	        List<String> ciudadesReservadas = inmueble.getReservas().stream()
	            .filter(reserva -> reserva.esMismoInquilino(usuario)) 
	            .map(reserva -> inmueble.getCiudad()) 
	            .distinct()  
	            .toList(); 
	        
	        ciudades.addAll(ciudadesReservadas); 
	    }
	    
	    return ciudades;
		
	}
	
	
	
	public List<Inmueble> filtrarInmuebles(Filtro filter){
		return filter.filtrar(this.getInmuebles());
	}
}
