package posteo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.FormaDePago;
import enums.Servicio;
import inmueble.Inmueble;
import mailSender.MailSender;
import periodo.PeriodoManager;
import reserva.Reserva;
import usuarios.Inquilino;
import usuarios.Usuario;


class posteoTest {
	private Inmueble inmueble;
	private Usuario inquilino;
	private Usuario inquilino2;
	private Reserva reserva;
	private Posteo posteo;
	private PeriodoManager periodo;
	private MailSender mailSender;

	@BeforeEach
	void setUp() throws Exception {
		mailSender = mock(MailSender.class);
		inquilino = mock(Inquilino.class);
		inquilino2 = mock(Inquilino.class);
    	inmueble = mock(Inmueble.class);
    	when(inmueble.getCiudad()).thenReturn("Quilmes");
    	
    	periodo = mock(PeriodoManager.class);
    	
    	reserva = mock(Reserva.class);
		posteo = new Posteo(inmueble, 9000.0, mailSender, periodo);
		when(posteo.getHuespedes()).thenReturn(5);
	}
	
	
	@Test
	void testGetCantidadDeReservasEnLaColaDeEspera() {
		posteo.crearReserva(inmueble, inquilino, LocalDate.of(2024, 10, 15), LocalDate.of(2024, 10, 20), FormaDePago.EFECTIVO);
		posteo.crearReserva(inmueble, inquilino2, LocalDate.of(2024, 10, 8), LocalDate.of(2024, 10, 17), FormaDePago.EFECTIVO);
		assertEquals(1, posteo.getColaDeEspera().size());
	}
	
	@Test
	void testGetHuespedes() {
		assertEquals(5, posteo.getHuespedes());
	}
	
	
	@Test
	void testCrearReserva() {
		posteo.crearReserva(inmueble, inquilino, LocalDate.of(2024, 10, 15), LocalDate.of(2024, 10, 20), FormaDePago.EFECTIVO);
		assertEquals(1, posteo.getReservas().size());
		assertTrue(posteo.estaDisponible(LocalDate.of(2024, 10, 8), LocalDate.of(2024, 10, 11)));
	}
	
	@Test
	void testCrearReservaParaFechaOcupada() {
		posteo.crearReserva(inmueble, inquilino, LocalDate.of(2024, 10, 15), LocalDate.of(2024, 10, 20), FormaDePago.EFECTIVO);
		assertFalse(posteo.estaDisponible(LocalDate.of(2024, 10, 8), LocalDate.of(2024, 10, 17)));
	}
	
	@Test
	void testGetMailSender() {
		assertEquals(mailSender, posteo.getMailSender());
	}
	
	@Test
	void testGetColaDeEspera() {
		assertEquals(0, posteo.getColaDeEspera().size());
	}
	
	@Test 
	void testGetCiudad () {
		assertEquals("Quilmes", posteo.getCiudad());
	}
	
	@Test
	void testGetReserva() {
		assertEquals(0, posteo.getReservas().size());
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
