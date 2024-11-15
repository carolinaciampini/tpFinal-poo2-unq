package estadoReserva;

import java.time.LocalDate;

import reserva.Reserva;

public class Aprobada extends EstadoReserva{
	
	@Override
	public boolean estaAprobada () {
		return true;
	}
	
	public void cancelarReserva (Reserva reserva) {
		reserva.setEstadoReserva(new Cancelada());
		reserva.enviarMail();
		
		reserva.borrarReserva(reserva);
		reserva.ejecutarColaEspera();
		reserva.ejecutarPenalizacionPorCancelacion();
	}

	public void finalizarReserva (Reserva reserva) {
		reserva.setEstadoReserva(new Finalizada());
	}
	
	
	@Override
	public void enviarMail(Reserva reserva) {
		reserva.enviarMailAInquilino("Tu reserva ha sido aprobada", "¡Felicitaciones! tu reserva fue aprobada");
	}

}
