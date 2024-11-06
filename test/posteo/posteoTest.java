package posteo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import mailSender.MailSender;
import periodo.PeriodoManager;
import reserva.Reserva;


class posteoTest {
	private Inmueble inmueble;
	private Reserva reserva;
	private Posteo posteo;
	private PeriodoManager periodo;
	private MailSender mailSender;

	@BeforeEach
	void setUp() throws Exception {
		mailSender = mock(MailSender.class);
    	inmueble = mock(Inmueble.class);
    	periodo = mock(PeriodoManager.class);
    	reserva = mock(Reserva.class);
		posteo = new Posteo(inmueble, 9000.0, mailSender, periodo);
	}

	@Test
	void testGettersDePosteo() {
		assertEquals(posteo.getInmueble(), inmueble);
		assertEquals(posteo.getPeiodoManager(), periodo);
		assertEquals(posteo.getPrecioBase(), 9000.0);
	}
	
	@Test
	void testPrecioParaUnaReserva() {
	  
	    LocalDate fechaEntrada = LocalDate.of(2024, 11, 1);
	    LocalDate fechaSalida = LocalDate.of(2024, 11, 3); // 2 noches
	    reserva = mock(Reserva.class);
	   
	
	    when(reserva.getFechaEntrada()).thenReturn(fechaEntrada);
	    when(reserva.getFechaSalida()).thenReturn(fechaSalida);
	    
	    // Se mockea el comportamiento del PeriodoManager
	    when(periodo.calcularPrecioPorDia(9000.0, fechaEntrada, fechaSalida)).thenReturn(18000.0); 

	    
	    Double precioCalculado = posteo.getPrecioParaReserva(reserva);
	    assertEquals(18000.0, precioCalculado);
	    verify(periodo).calcularPrecioPorDia(9000.0, fechaEntrada, fechaSalida);
	}


}
