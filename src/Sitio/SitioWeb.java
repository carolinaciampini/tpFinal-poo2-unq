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
	
// Metodo para chequear si el propietario está registrado
    public boolean estaRegistrado(Usuario propietario) {
        return usuarios.stream().anyMatch(usuario -> usuario.getEmail().equals(propietario.getEmail()));
    }
    
    
	
//Metodos de inmueble
	public List<Inmueble> getInmuebles() {
		return this.inmuebles;
	}
	public Boolean addInmueble (Inmueble inmueble) {
		if (estaRegistrado(inmueble.getPropietario())) {
            inmuebles.add(inmueble);
            return true; // Indica que el inmueble fue agregado
        } else {
            System.out.println("El propietario no está registrado en el sitio.");
            return false; // Indica que el inmueble no fue agregado
        }
	}
	
	public void removeInmueble (Inmueble inmueble) {
		inmuebles.remove(inmueble);
	}
}
