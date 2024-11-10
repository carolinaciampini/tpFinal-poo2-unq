package estadoReserva;

import reserva.Reserva;

public class Cancelada extends EstadoReserva {
	
	public void procesarReserva (Reserva reserva) {
		reserva.getInmueble().procesarColaEspera();
	}
	
	@Override
	public void enviarMail(Reserva reserva) {
		reserva.getInmueble().getMailSender().enviarMail(reserva.getInmueble().getPropietario().getEmail(), "Reserva cancelada", "¡La reserva para tu Inmueble fue cancelada!");

	}
}
