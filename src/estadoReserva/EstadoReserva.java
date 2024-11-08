package estadoReserva;
import reserva.Reserva;


public abstract class EstadoReserva {
	public void rechazarReserva (Reserva reserva) {}
	public void aceptarReserva (Reserva reserva) {}
	public void enviarMail (Reserva reserva) {}
	public void cancelarReserva (Reserva reserva) {}
	public void procesarReserva (Reserva reserva) {}

}
