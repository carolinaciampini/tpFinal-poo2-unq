package Sitio;

import java.util.ArrayList;
import java.util.List;

import excepciones.PropietarioNoRegistradoExcepcion;
import excepciones.UsuarioYaExistenteException;
import filtros.FilterManager;
import usuarios.Usuario;
import inmuebless.Inmuebless;
import reserva.Reserva;


public class SitioWeb {
	private List<Usuario> usuarios;
	private List <Inmuebless> inmuebles;
	
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
	
	public List<Inmuebless> getInmuebles() {
		return inmuebles;
	}
	
	

    public boolean estaRegistrado(Usuario usuario) {
        return usuarios.stream().anyMatch(u -> u.getEmail().equals(usuario.getEmail()));
    }
    
	
    public void darDeAltaInmueble (Inmuebless inmueble) throws PropietarioNoRegistradoExcepcion {
		if (estaRegistrado(inmueble.getPropietario())) {
            inmuebles.add(new Inmuebless(null, null, null, null, null, null, null, null, null, null, null, null, null));
        } else {
        	throw new PropietarioNoRegistradoExcepcion();
        }
	}

	public void agregarPosteo(Inmuebless p) {
		this.inmuebles.add(p);
	}
	
	public List<Reserva> getReservasDe(Inmuebless posteo) {
		return posteo.getReservas();
	}
	
	// FILTROS
	
	public List<Inmuebless> filtrarPosteos(FilterManager filter){
		return filter.filtrar(this.getInmuebles());
	}
}
