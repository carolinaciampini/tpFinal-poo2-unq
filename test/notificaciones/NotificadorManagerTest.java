package notificaciones;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import reserva.Reserva;

public class NotificadorManagerTest {
	private NotificadorManager notificador; 
	private Inmueble inmueble;
	private Reserva reserva;
	 private Listener listener1;
	 private Listener listener2;

	@BeforeEach
	void setUp() {
		notificador = new NotificadorManager();
		inmueble = mock(Inmueble.class);
		reserva = mock(Reserva.class);
		listener2 = mock(Listener.class);
		listener1 = mock(Listener.class);
	}
	
	 @Test
	    public void testAgregarListener() {
		 notificador.agregarListener(listener1);
		 notificador.notificarBajaPrecio(inmueble);
	       verify(listener1, times(1)).updateBajaPrecio(inmueble);
	    }
	
	 @Test
	    public void testRemoverListener() {
		 notificador.agregarListener(listener1);
		 notificador.removerListener(listener1);
		 notificador.notificarBajaPrecio(inmueble);
	        verify(listener1, never()).updateBajaPrecio(inmueble);
	    }
	 
	 @Test
	    public void testNotificarBajaPrecioMultiplesListeners() {
		 notificador.agregarListener(listener1);
		 notificador.agregarListener(listener2);
	        
		 notificador.notificarBajaPrecio(inmueble);
	        
	       verify(listener1, times(1)).updateBajaPrecio(inmueble);
	       verify(listener2, times(1)).updateBajaPrecio(inmueble);
	    }
	
	 @Test
	    public void testNotificarCancelacion() {
		 notificador.agregarListener(listener1);
		 notificador.agregarListener(listener2);
	        
		 notificador.notificarCancelacion(reserva);
	        
	        verify(listener1, times(1)).updateCancelacion(reserva);
	        verify(listener2, times(1)).updateCancelacion(reserva);
	    }
	 
	 @Test
	 public void testNotificarReserva() {
		 notificador.agregarListener(listener1);
		 notificador.agregarListener(listener2);
	        
		 notificador.notificarReserva(reserva);
	        
	        verify(listener1, times(1)).updateReserva(reserva);
	        verify(listener2, times(1)).updateReserva(reserva);
	    }
}
