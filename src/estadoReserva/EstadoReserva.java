package estadoReserva;
import excepciones.EstadoInvalidoParaRankear;
import ranking.Ranking;
import reserva.Reserva;



public abstract class EstadoReserva {
	public void rechazarReserva 	(Reserva reserva) {}
	public void aceptarReserva 		(Reserva reserva) {}
	public void enviarMail 			(Reserva reserva) {}
	public void cancelarReserva 	(Reserva reserva) {}
	public void finalizarReserva 	(Reserva reserva) {}
	public boolean estaAprobada () {
		return false;
	}
	public boolean estaRechazada () {
		return false;
	}
	public void rankearInmueble(Ranking ranking, Reserva reserva) throws EstadoInvalidoParaRankear {	
		throw new EstadoInvalidoParaRankear();
	}
	public void rankearPropietario(Ranking ranking, Reserva reserva) throws EstadoInvalidoParaRankear {
		throw new EstadoInvalidoParaRankear();
	}
	public void rankearInquilino(Ranking ranking, Reserva reserva) throws EstadoInvalidoParaRankear {
        throw new EstadoInvalidoParaRankear();
    }
	
}
