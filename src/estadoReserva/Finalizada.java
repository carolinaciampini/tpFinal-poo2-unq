package estadoReserva;

import ranking.Ranking;
import reserva.Reserva;


public class Finalizada extends EstadoReserva {
	
	public void rankearInmueble(Ranking ranking, Reserva reserva) {
		reserva.getInmueble().recibirRanking(ranking);
	}
	
	public void rankearPropietario(Ranking ranking, Reserva reserva) {
		reserva.getPropietario().recibirRankingComoPropietario(ranking);
	}
	
	public void rankearInquilino(Ranking ranking, Reserva reserva) {
		reserva.getInquilino().recibirRankingComoInquilino(ranking);
	}
	
	
}
