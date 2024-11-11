package estrategiaCancelacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import reserva.Reserva;

public class SinCancelacionTest {
	private SinCancelacion sinCancelacion;
	private Reserva reserva;
	private Inmueble inmueble;
	
	
	@BeforeEach
	void setUp() {
		reserva = mock(Reserva.class);
		inmueble = mock(Inmueble.class);
		sinCancelacion = new SinCancelacion();
	}
	
	@Test
	void testCalcularPenalizacionFaltandoMenos10dias() {
		when(inmueble.getPrecioParaReserva(reserva)).thenReturn((double) 90000);
		
		assertEquals((double)90000, sinCancelacion.calcularPenalizacion(reserva, inmueble));
	}
	
}
