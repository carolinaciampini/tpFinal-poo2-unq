package estadoReserva;

import reserva.Reserva;

public class Cancelada extends EstadoReserva {
	
	/*
	public void procesarReserva (Reserva reserva) {
		// TO DO: rompe encapsulamiento 
		reserva.getInmueble().procesarColaEspera();
	}
	*/
	@Override
	public void enviarMail(Reserva reserva) {
		//reserva.getMailSender().enviarMail(reserva.getInmueble().getPropietario().getEmail(), "Reserva cancelada", "¡La reserva para tu Inmueble fue cancelada!");
		reserva.enviarMailDeAviso("Reserva cancelada", "¡La reserva para tu Inmueble fue cancelada!");
	}
}
