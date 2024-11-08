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
import estrategiaCancelacion.EstrategiaCancelacion;
import estrategiaCancelacion.Gratuito;
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
	private Usuario inquilino3;
	private Usuario inquilino4;

	
	private Reserva reserva;
	private Reserva reserva2;
	private Reserva reserva3;
	private Reserva reserva4;
	private Reserva reserva5;

	
	private Posteo posteo;
	private PeriodoManager periodo;
	private MailSender mailSender;
	
	private EstrategiaCancelacion estrategia;

	@BeforeEach
	void setUp() throws Exception {
		mailSender = mock(MailSender.class);
		estrategia = mock(Gratuito.class);
		
		inquilino = mock(Inquilino.class);
		inquilino2 = mock(Inquilino.class);
		when(inquilino.getEmail()).thenReturn("abru@gmail.com");
		
		inquilino3 = mock(Inquilino.class);
		inquilino4 = mock(Inquilino.class);

		
    	inmueble = mock(Inmueble.class);
    	when(inmueble.getCiudad()).thenReturn("Quilmes");
    	
    	periodo = mock(PeriodoManager.class);
    	
    	posteo = new Posteo(inmueble, 9000.0, mailSender, periodo, estrategia);
		when(posteo.getHuespedes()).thenReturn(5);
    	
    	reserva = new Reserva (posteo, inmueble, inquilino, LocalDate.of(2024, 10, 15),LocalDate.of(2024, 10, 20), FormaDePago.EFECTIVO);
    	
    	reserva2 = new Reserva(posteo, inmueble,inquilino2, LocalDate.of(2024, 10, 8),LocalDate.of(2024, 10, 16), FormaDePago.EFECTIVO );
    	
    	reserva3 = new Reserva(posteo, inmueble,inquilino3, LocalDate.of(2024, 10, 14),LocalDate.of(2024, 10, 21), FormaDePago.EFECTIVO );
    	
    	reserva4 = new Reserva(posteo, inmueble,inquilino4, LocalDate.of(2024, 10, 18),LocalDate.of(2024, 10, 23), FormaDePago.EFECTIVO );
    	
    	reserva5 = new Reserva (posteo, inmueble, inquilino4, LocalDate.of(2024, 10, 10),LocalDate.of(2024, 10, 15), FormaDePago.EFECTIVO);
	}
	
	@Test
	void testSeConcretaLaSegundaaReservaDeLaColaDeEspera() {
		posteo.crearReserva(reserva5);
		posteo.crearReserva(reserva4);
		assertEquals(2, posteo.getReservas().size());
		posteo.crearReserva(reserva3);
		posteo.crearReserva(reserva2);
		assertEquals(2, posteo.getColaDeEspera().size());
		reserva5.cancelarReserva(LocalDate.now());		
		assertEquals(2, posteo.getReservas().size());
		assertEquals(1, posteo.getColaDeEspera().size());
		
		verify(mailSender).enviarMail("abru@gmail.com","Tu reserva fue procesada", 
                "Felicitaciones, como hubo una cancelación, tu reserva pudo ser realizada");
		
		
		
	}
	

	@Test
	void testCrearReserva() {
		posteo.crearReserva(reserva);
		assertEquals(1, posteo.getReservas().size());
		assertTrue(posteo.estaDisponible(LocalDate.of(2024, 10, 8), LocalDate.of(2024, 10, 11)));
		assertFalse(posteo.estaDisponible(LocalDate.of(2024, 10, 8), LocalDate.of(2024, 10, 17)));
		
	}
/*	
	
	@Test
	void testGetHuespedes() {
		assertEquals(5, posteo.getHuespedes());
	}
	
	@Test
	void testCrearReserva() {
		posteo.crearReserva(reserva);
		assertEquals(1, posteo.getReservas().size());
		assertTrue(posteo.estaDisponible(LocalDate.of(2024, 10, 8), LocalDate.of(2024, 10, 11)));
		assertFalse(posteo.estaDisponible(LocalDate.of(2024, 10, 8), LocalDate.of(2024, 10, 17)));
		
	}
	
	 @Test
	void testGetCantidadDeReservasEnLaColaDeEspera() {
		posteo.crearReserva(reserva);
		posteo.crearReserva(reserva2);
		assertEquals(1, posteo.getColaDeEspera().size());
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
*/
}
