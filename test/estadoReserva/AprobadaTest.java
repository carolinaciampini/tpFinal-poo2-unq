package estadoReserva;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import reserva.Reserva;

class AprobadaTest {
	private Reserva reserva1;
	private Reserva reserva2;
	private Reserva reserva3;
	
	private Aprobada estadoAprobada;

	@BeforeEach
	void setUp() throws Exception {
		reserva1 = mock(Reserva.class);
		reserva2 = mock(Reserva.class);
		reserva3 = mock(Reserva.class);

		estadoAprobada = new Aprobada();
		
		when(reserva1.getEstadoReserva()).thenReturn(estadoAprobada);
		
	}

	@Test
	void testEnviarMail() {
		estadoAprobada.enviarMail(reserva1);
		verify(reserva1).enviarMailAInquilino("Tu reserva ha sido aprobada", "¡Felicitaciones! tu reserva fue aprobada");
	}
	
	@Test
	void testCancelarReserva() {
		estadoAprobada.cancelarReserva(reserva2);
		verify(reserva2).setEstadoReserva(any(Cancelada.class));
		verify(reserva2).enviarMail();
		verify(reserva2).borrarReserva(reserva2);
		verify(reserva2).ejecutarColaEspera();
		
	}

	
	@Test
	void testFinalizarReserva() {
		estadoAprobada.finalizarReserva(reserva3);
		verify(reserva3).setEstadoReserva(any(Finalizada.class));
	}
	

}
