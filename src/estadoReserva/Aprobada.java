package estadoReserva;

import java.time.LocalDate;

import reserva.Reserva;

public class Aprobada extends EstadoReserva{
	
	public void cancelarReserva (Reserva reserva) {
		reserva.setEstadoReserva(new Cancelada());
		reserva.getEstadoReserva().enviarMail(reserva);
    	reserva.getInmueble().getReservas().remove(reserva);
		reserva.getInmueble().procesarColaEspera();
		reserva.getInmueble().calcularPenalizacion(LocalDate.now(), reserva);
	}

	public void finalizarReserva (Reserva reserva) {
		reserva.setEstadoReserva(new Finalizada());
	}
	
	public void rechazarReserva (Reserva reserva) {
		reserva.setEstadoReserva(new Rechazada());
		reserva.getInmueble().eliminarReserva(reserva);
	}
	
	@Override
	public void enviarMail(Reserva reserva) {
		reserva.getInmueble().getMailSender().enviarMail(reserva.getInquilino().getEmail(), "Tu reserva ha sido aprobada", "¡Felicitaciones! tu reserva fue aprobada");
	}

}
