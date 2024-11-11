package notificaciones;

import inmueble.Inmueble;
import reserva.Reserva;

public class SuscriptorMobile  implements Listener{
	private PopUpWindow popUp;
	
	
	public SuscriptorMobile(PopUpWindow popUp) {
		super();
		this.popUp = popUp;
	}

	@Override
	public void updateBajaPrecio(Inmueble inmueble) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCancelacion(Reserva reserva) {
	    this.popUp.popUp("El " + reserva.getTipoInmueble() + " que te interesa se ha liberado. ¡Corre a reservarlo!", "blue", 14);
	}


	@Override
	public void updateReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

}
