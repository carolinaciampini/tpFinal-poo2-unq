package estadoReserva;

import reserva.Reserva;

public class Rechazada extends EstadoReserva {


	@Override
	public void enviarMail(Reserva reserva) {
		reserva.enviarMailAInquilino("Reserva rechazada", "¡La reserva para tu Inmueble fue rechazada!");
		
	}

}
