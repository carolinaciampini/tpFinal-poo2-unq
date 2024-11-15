package estrategiaCancelacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import reserva.Reserva;

public class GratuitoTest {
		private Gratuito gratuito;
		private Reserva reserva;
		private Inmueble inmueble;
		
		
		@BeforeEach
		void setUp() {
			reserva = mock(Reserva.class);
			inmueble = mock(Inmueble.class);
			gratuito = new Gratuito();
		}
		
		@Test
		void testCalcularPenalizacionFaltandoMenos10dias() {
			when(inmueble.getPrecioParaReserva(reserva)).thenReturn(90000.00);
			when(reserva.cantidadDeDias()).thenReturn(4);
			when(reserva.cantidadDiasFaltantes()).thenReturn(7);
			
			assertEquals(45000.00, gratuito.calcularPenalizacion(reserva, inmueble));
		}
		
		@Test
		void testCalcularPenalizacionFaltandoMas10dias() {
			when(inmueble.getPrecioParaReserva(reserva)).thenReturn(90000.00);
			when(reserva.cantidadDeDias()).thenReturn(8);
			when(reserva.cantidadDiasFaltantes()).thenReturn(20);
			
			assertEquals(0.00, gratuito.calcularPenalizacion(reserva, inmueble));
		}
}
