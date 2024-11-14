package notificaciones;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import reserva.Reserva;

public class SuscriptorWebTest {
	 private SuscriptorWeb suscriptorWeb;
	 private HomePagePublisher homePagePublisher;
	 private Inmueble inmueble;
	 private Reserva reserva;
	 
	 @BeforeEach
	    public void setUp() {
		 	homePagePublisher = mock(HomePagePublisher.class);
		 	inmueble = mock(Inmueble.class);
	        suscriptorWeb = new SuscriptorWeb(homePagePublisher);
	        reserva = mock(Reserva.class);
	 }
	 
	 @Test
	    public void testUpdateBajaPrecio() {
	        when(inmueble.getTipoInmueble()).thenReturn("Departamento");
	        when(inmueble.getPrecioBase()).thenReturn(1000.0);
	        
	        suscriptorWeb.bajaDePrecio(inmueble);
	        
	        
	        verify(homePagePublisher).publish(
	        		  "No te pierdas esta oferta: Un inmueble Departamento a tan sólo 1000.0"
	        );
	        
	    }
	 
	 @Test
	    public void testElSuscriptorNoHaceUpdate() {

	        suscriptorWeb.cancelacionDeReserva(reserva);
	        suscriptorWeb.altaDeReserva(reserva);
	        verifyNoInteractions(homePagePublisher);
	        
	        
	        
	    }
	 
}
