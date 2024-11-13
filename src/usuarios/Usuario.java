package usuarios;

import java.util.List;

import excepciones.NoHayPuntajesSobreEsteUsuario;
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

	public List<Ranking> getRankingsComoPropietario() {
		return this.rankingsComoPropietario;
	}
	
	public Integer puntajePromedioComoPropietario() throws NoHayPuntajesSobreEsteUsuario {
		if (rankingsComoPropietario.isEmpty()) {
			throw new NoHayPuntajesSobreEsteUsuario();
		}
		Integer total = 0;
		for (Ranking ranking : rankingsComoPropietario) {
			total += ranking.getPuntaje();
		}
		
		return (total / rankingsComoPropietario.size());
	}
	
	public List<Ranking> getRankingsComoInquilino() {
		return this.rankingsComoInquilino;
	}
	
	public Integer puntajePromedioComoInquilino() throws NoHayPuntajesSobreEsteUsuario {
		if (rankingsComoInquilino.isEmpty()) {
			throw new NoHayPuntajesSobreEsteUsuario();
		}
		Integer total = 0;
		for (Ranking ranking : rankingsComoInquilino) {
			total += ranking.getPuntaje();
		}
		
		return (total / rankingsComoInquilino.size());
	}
	
	
	public boolean esMismoUsuarioQue(Usuario usuario) {
	    return this.getEmail().equals(usuario.getEmail());
	}

	

}
