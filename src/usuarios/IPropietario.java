package usuarios;

import java.util.List;

import excepciones.NoHayPuntajesSobreEsteUsuario;
import ranking.Ranking;

public interface IPropietario {
	public void recibirRankingComoPropietario(Ranking ranking);
	public List<Ranking> getRankingsComoPropietario();
	public Integer puntajePromedioComoPropietario() throws NoHayPuntajesSobreEsteUsuario;
	public String getEmail();
	boolean esMismoUsuarioQue(Usuario usuario);
	public String getNombre();
	public String getTelefono();
}
