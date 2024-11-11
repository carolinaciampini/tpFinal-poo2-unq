package Sitio;

import java.util.ArrayList;
import java.util.List;

import usuarios.Usuario;
import inmueble.Inmueble;

public class SitioWeb {
	private List<Usuario> usuarios;
	private List<Inmueble> inmuebles;
	
	public SitioWeb () {
		this.usuarios = new ArrayList <>() ;
		this.inmuebles = new ArrayList<>();
	}

//Metodos de usuario
	
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}
	public void addUsuario (Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public void removeUsuario (Usuario usuario) {
		usuarios.remove(usuario);
	}
	
//Metodos de inmueble
	public List<Inmueble> getInmuebles() {
		return this.inmuebles;
	}
	public void addInmueble (Inmueble inmueble) {
		inmuebles.add(inmueble);
	}
	
	public void removeInmueble (Inmueble inmueble) {
		inmuebles.remove(inmueble);
	}
}
