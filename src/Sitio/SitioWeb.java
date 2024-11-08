package Sitio;

import java.util.ArrayList;
import java.util.List;

import excepciones.PropietarioNoRegistradoExcepcion;
import excepciones.UsuarioYaExistenteException;
import filtros.FilterManager;
import usuarios.Usuario;
import inmueble.Inmueble;
import posteo.Posteo;
import reserva.Reserva;


public class SitioWeb {
	private List<Usuario> usuarios;
	private List <Posteo> posteos;
	
	public SitioWeb () {
		this.usuarios = new ArrayList <>() ;
		this.posteos = new ArrayList<>();
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
	
	public List<Posteo> getPosteos() {
		return posteos;
	}
	
	

    public boolean estaRegistrado(Usuario usuario) {
        return usuarios.stream().anyMatch(u -> u.getEmail().equals(usuario.getEmail()));
    }
    
	
    public void darDeAltaInmueble (Inmueble inmueble) throws PropietarioNoRegistradoExcepcion {
		if (estaRegistrado(inmueble.getPropietario())) {
            posteos.add(new Posteo(inmueble, null, null, null, null));
        } else {
        	throw new PropietarioNoRegistradoExcepcion();
        }
	}

	public void agregarPosteo(Posteo p) {
		this.posteos.add(p);
	}
	
// metodo para ver si el inmueble tiene un posteo
	public boolean tienePosteo(Inmueble inmueble) {
		return posteos.stream().anyMatch(p -> p.getInmueble().equals(inmueble));
	}

	
	public void removePosteo (Inmueble inmueble) {
		posteos.removeIf(posteo -> posteo.getInmueble().equals(inmueble));
	}

	public List<Reserva> getReservasDe(Posteo posteo) {
		return posteo.getReservas();
	}
	
	// FILTROS
	
	public List<Posteo> filtrarPosteos(FilterManager filter){
		return filter.filtrar(this.getPosteos());
	}
}
