package notificaciones;

import inmueble.Inmueble;
import reserva.Reserva;

public class SuscriptorWeb implements Listener {
	private HomePagePublisher homePage;
	
	public SuscriptorWeb(HomePagePublisher homePage) {
		super();
		this.homePage = homePage;
	}

	@Override
	public void bajaDePrecio(Inmueble inmueble) {

		  this.homePage.publish("No te pierdas esta oferta: Un inmueble " + 
                  inmueble.getTipoInmueble() + 
                  " a tan sólo " + 
                  inmueble.getPrecioBase());
		
	}

	@Override
	public void cancelacionDeReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void altaDeReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

}
