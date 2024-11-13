package estadoReserva;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reserva.Reserva;

class CanceladaTest {
	private Reserva reserva1;
	private Reserva reserva2;
	
	private Cancelada estadoCancelada;

	@BeforeEach
	void setUp() throws Exception {
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		
		estadoCancelada = new Cancelada();
		
		when(reserva1.getEstadoReserva()).thenReturn(estadoCancelada);
	}

	@Test
	void testEnviarMail() {
		estadoCancelada.enviarMail(reserva1);
		verify(reserva1).enviarMailDeAviso("Reserva cancelada", "¡La reserva para tu Inmueble fue cancelada!");
	}

	@Test
	void testProcesarReserva() {
		estadoCancelada.procesarReserva(reserva2);
		verify(reserva2).ejecutarColaEspera();
	}

}
