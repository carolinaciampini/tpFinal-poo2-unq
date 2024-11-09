package estadoReserva;

import reserva.Reserva;

public class Cancelada extends EstadoReserva {
	
	public void procesarReserva (Reserva reserva) {
		reserva.getInmueble().procesarColaEspera();
	}
	
	@Override
	public void enviarMail(Reserva r) {
		// TODO Auto-generated method stub

	}
}
