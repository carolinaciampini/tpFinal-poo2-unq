package estadoReserva;

import java.time.LocalDate;

import reserva.Reserva;

public class Aprobada extends EstadoReserva{
	
	public void cancelarReserva (Reserva reserva) {
		reserva.setEstadoReserva(new Cancelada());
		reserva.enviarMail();
		
		// TO DO: rompe encapsulamiento 
    	reserva.getInmueble().getReservas().remove(reserva);
    	
    	// TO DO: rompe encapsulamiento 
		reserva.getInmueble().procesarColaEspera();
		
		// TO DO: rompe encapsulamiento 
		//reserva.getInmueble().calcularPenalizacion(fech, reserva);
	}

	public void finalizarReserva (Reserva reserva) {
		reserva.setEstadoReserva(new Finalizada());
	}
	
	
	@Override
	public void enviarMail(Reserva reserva) {
		// TO DO: rompe encapsulamiento 
		reserva.getMailSender().enviarMail(reserva.getInquilino().getEmail(), "Tu reserva ha sido aprobada", "¡Felicitaciones! tu reserva fue aprobada");
	}

}
