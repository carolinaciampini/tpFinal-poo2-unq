package estadoReserva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reserva.Reserva;

class RechazadaTest {
	private Rechazada estadoRechazada;
	private Reserva reserva1;

	@BeforeEach
	void setUp() throws Exception {
		reserva1 = mock(Reserva.class);
		
		estadoRechazada = new Rechazada();
		
		when(reserva1.getEstadoReserva()).thenReturn(estadoRechazada);
	}
	/*
	@Test
	void testEnviarMail() {
		estadoRechazada.enviarMail(reserva1);
		verify(reserva1).enviarMailAInquilino("Reserva rechazada", "¡La reserva para tu Inmueble fue rechazada!");
	}
	
	@Test
	void testEstaRechazada() {
		assertTrue(estadoRechazada.estaRechazada());
	}
	*/
}
