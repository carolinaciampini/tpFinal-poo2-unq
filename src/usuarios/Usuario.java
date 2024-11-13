package usuarios;

import java.util.List;

import ranking.Ranking;

public class Usuario {
	private String nombreCompleto;
	private String email;
	private String telefono;
	private List<Ranking> rankingsComoPropietario;
	private List<Ranking> rankingsComoInquilino;

	public Usuario(String nombre, String mail, String telefono) {
		this.nombreCompleto = nombre;
		this.email = mail;
		this.telefono = telefono;
	}
	
	public String getNombre() {
		return nombreCompleto;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void recibirRankingComoPropietario(Ranking ranking) {
		this.rankingsComoPropietario.add(ranking);
		
	}

	public void recibirRankingComoInquilino(Ranking ranking) {
		this.rankingsComoInquilino.add(ranking);
		
	}

	public boolean esMismoUsuarioQue(Usuario usuario) {
	    return this.getEmail().equals(usuario.getEmail());
	}
}
