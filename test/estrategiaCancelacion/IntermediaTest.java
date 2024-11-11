package estrategiaCancelacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import reserva.Reserva;

public class IntermediaTest {
		private Intermedio intermedio;
		private Reserva reserva;
		private Inmueble inmueble;
		
		
		@BeforeEach
		void setUp() {
			reserva = mock(Reserva.class);
			inmueble = mock(Inmueble.class);
			intermedio = new Intermedio();
		}
		
		@Test
		void testCalcularPenalizacionFaltandoMasDe20dias() {
			when(inmueble.getPrecioParaReserva(reserva)).thenReturn(150000.00);
			when(reserva.cantidadDiasFaltantes()).thenReturn(25);
			
			assertEquals(0.00, intermedio.calcularPenalizacion(reserva, inmueble));
		}
		
		@Test
		void testCalcularPenalizacionFaltandoEntre19Y10Dias() {
			when(inmueble.getPrecioParaReserva(reserva)).thenReturn(175000.00);
			when(reserva.cantidadDiasFaltantes()).thenReturn(14);
			
			assertEquals(87500.00, intermedio.calcularPenalizacion(reserva, inmueble));
		}
		
		@Test
		void testCalcularPenalizacionFaltandoMenos10Dias() {
			when(inmueble.getPrecioParaReserva(reserva)).thenReturn( 90000.00);
			when(reserva.cantidadDiasFaltantes()).thenReturn(6);
			
			assertEquals(90000.00, intermedio.calcularPenalizacion(reserva, inmueble));
		}
}
