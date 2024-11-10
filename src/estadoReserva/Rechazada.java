package estadoReserva;

import reserva.Reserva;

public class Rechazada extends EstadoReserva {


	@Override
	public void enviarMail(Reserva reserva) {
		reserva.getInmueble().getMailSender().enviarMail(reserva.getInquilino().getEmail(), "Reserva rechazada", "¡Tu reserva para el Inmueble fue rechazada!");
	}

}
