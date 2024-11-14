package usuarios;

import java.util.List;

import excepciones.NoHayPuntajesSobreEsteUsuario;
import ranking.Ranking;

public interface IInquilino {
	public void recibirRankingComoInquilino(Ranking ranking);
	public List<Ranking> getRankingsComoInquilino();
	public Integer puntajePromedioComoInquilino() throws NoHayPuntajesSobreEsteUsuario;
	public String getEmail();
	boolean esMismoUsuarioQue(Usuario usuario);
	public String getNombre();
	public String getTelefono();
}
