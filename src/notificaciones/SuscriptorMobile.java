package notificaciones;

import java.time.LocalDate;

import inmueble.Inmueble;
import reserva.Reserva;

public class SuscriptorMobile  implements Listener{
	private PopUpWindow popUp;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	
	
	public SuscriptorMobile(PopUpWindow popUp, LocalDate fechaEntrada, LocalDate fechaSalida) {
		super();
		this.popUp = popUp;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida; 
	}

	@Override
	public void bajaDePrecio(Inmueble inmueble) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelacionDeReserva(Reserva reserva) {
		if(reserva.getFechaEntrada().isAfter(this.fechaEntrada) && reserva.getFechaSalida().isBefore(this.fechaSalida)) {
			this.popUp.popUp("El " + reserva.getTipoInmueble() + " que te interesa se ha liberado. ¡Corre a reservarlo!", "blue", 14);
		}
	}
	    


	@Override
	public void altaDeReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

}
