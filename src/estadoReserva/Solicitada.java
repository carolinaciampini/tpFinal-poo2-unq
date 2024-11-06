package estadoReserva;

import java.time.LocalDate;

import reserva.Reserva;

public class Solicitada extends EstadoReserva {
	

	public void cancelarReserva (Reserva reserva) {
		reserva.setEstadoReserva(new Cancelada());
		reserva.getPosteo().calcularPenalizacion(LocalDate.now(), reserva);
		

	}
	@Override
	public void aceptarReserva(Reserva reserva) {
		reserva.setEstadoReserva(new Aprobada());
	}

	@Override
	public void rechazarReserva(Reserva reserva) {
		reserva.setEstadoReserva(new Rechazada());
		
	}
}
