package Sitio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import excepciones.PropietarioNoRegistradoExcepcion;
import excepciones.UsuarioYaExistenteException;
import filtros.Filtro;
import inmueble.Inmueble;
import usuarios.Usuario;
import reserva.Reserva;


public class SitioWeb {
	private List<Usuario> usuarios;
	private List <Inmueble> inmuebles;
	private Map<Usuario, LocalDate> usuariosFechaRegistro;
	
	public SitioWeb () {
		this.usuarios = new ArrayList <>() ;
		this.inmuebles = new ArrayList<>();
		this.usuariosFechaRegistro = new HashMap<>();
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
	
	public int obtenerCantidadReservasDeUsuario (Usuario usuario) {
		return obtenerReservasDeUsuario(usuario).size();
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

	public void agregarInmueble(Inmueble i) {
		this.inmuebles.add(i);
	}
	
	public List<Reserva> getReservasDe(Inmueble inmueble) {
		return inmueble.getReservas();
	}
	
	// FILTROS
	
	public List<Inmueble> filtrarInmuebles(Filtro filter){
		return filter.filtrar(this.getInmuebles());
	}
}
