package Sitio;

import java.util.ArrayList;
import java.util.List;

import excepciones.PropietarioNoRegistradoExcepcion;
import excepciones.UsuarioYaExistenteException;
import filtros.Filtro;
import inmueble.Inmueble;
import usuarios.Usuario;
import reserva.Reserva;


public class SitioWeb {
	private List<Usuario> usuarios;
	private List <Inmueble> inmuebles;
	
	public SitioWeb () {
		this.usuarios = new ArrayList <>() ;
		this.inmuebles = new ArrayList<>();
	}


	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	
	public void addUsuario (Usuario usuario) throws UsuarioYaExistenteException{
		 if (estaRegistrado(usuario)) {
	            throw new UsuarioYaExistenteException();
	        }
		 usuarios.add(usuario);
	}
	
	public void removeUsuario (Usuario usuario) {
		usuarios.remove(usuario);
	}
	
	public List<Inmueble> getInmuebles() {
		return inmuebles;
	}
	
	

    public boolean estaRegistrado(Usuario usuario) {
        return usuarios.stream().anyMatch(u -> u.getEmail().equals(usuario.getEmail()));
    }
    
	
    public void darDeAltaInmueble (Inmueble inmueble) throws PropietarioNoRegistradoExcepcion {
		if (estaRegistrado(inmueble.getPropietario())) {
            inmuebles.add(new Inmueble(null, null, null, null, null, null, null, null, null, null, null, null, null));
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
