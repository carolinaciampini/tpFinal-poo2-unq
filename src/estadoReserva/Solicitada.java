package estadoReserva;

import java.time.LocalDate;

import reserva.Reserva;

public class Solicitada extends EstadoReserva {
	
	
	@Override
	public void aceptarReserva(Reserva reserva) {
		reserva.setEstadoReserva(new Aprobada());
		reserva.enviarMail();
	}
	
	@Override
	public void rechazarReserva(Reserva reserva) {
		reserva.setEstadoReserva(new Rechazada());
		reserva.enviarMail();
	}

}
