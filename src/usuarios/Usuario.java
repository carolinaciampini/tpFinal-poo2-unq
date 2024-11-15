package usuarios;

import java.util.ArrayList;
import java.util.List;

import excepciones.NoHayPuntajesSobreEsteUsuario;
import ranking.Ranking;

public class Usuario implements IPropietario, IInquilino {
	private String nombreCompleto;
	private String email;
	private String telefono;
	private List<Ranking> rankingsComoPropietario;
	private List<Ranking> rankingsComoInquilino;

	public Usuario(String nombre, String mail, String telefono) {
		this.nombreCompleto = nombre;
		this.email = mail;
		this.telefono = telefono;
        this.rankingsComoPropietario = new ArrayList<>();
        this.rankingsComoInquilino = new ArrayList<>(); 
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
	
	@Override
	public void recibirRankingComoPropietario(Ranking ranking) {
		this.rankingsComoPropietario.add(ranking);
		
	}
	
	@Override
	public void recibirRankingComoInquilino(Ranking ranking) {
		this.rankingsComoInquilino.add(ranking);
		
	}
	
	@Override
	public List<Ranking> getRankingsComoPropietario() {
		return this.rankingsComoPropietario;
	}
	
	@Override
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
	
	@Override
	public List<Ranking> getRankingsComoInquilino() {
		return this.rankingsComoInquilino;
	}
	
	@Override
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
	
	@Override
	public boolean esMismoUsuarioQue(Usuario usuario) {
		 return this.getEmail().equals(usuario.getEmail());
	}

	
	
}
