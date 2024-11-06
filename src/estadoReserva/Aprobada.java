package estadoReserva;

import reserva.Reserva;

public class Aprobada extends EstadoReserva{
	
	public void cancelarReserva (Reserva reserva) {
		reserva.setEstadoReserva(new Cancelada());
	}

	public void finalizarReserva (Reserva reserva) {
		reserva.setEstadoReserva(new Finalizada());
	}
	
	@Override
	public void enviarMail(Reserva reserva) {
		reserva.getPosteo().getMailSender().enviarMail(reserva.getInquilino().getEmail(), "Tu reserva ha sido aprobada", "¡Felicitaciones! tu reserva fue aprobada");
	}

}
