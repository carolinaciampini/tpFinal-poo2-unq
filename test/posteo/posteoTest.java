package posteo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.FormaDePago;
import enums.Servicio;
import estrategiaCancelacion.EstrategiaCancelacion;
import estrategiaCancelacion.Gratuito;
import inmueble.Inmueble;
import inmuebless.InmueblessREEMPLAZO;
import mailSender.MailSender;
import periodo.PeriodoManager;
import reserva.Reserva;
import usuarios.Inquilino;
import usuarios.Propietario;
import usuarios.Usuario;


class posteoTest {
	private Usuario inquilino;
	private Usuario inquilino2;
	private Usuario inquilino3;
	private Usuario inquilino4;
	private Propietario propietario;

	
	private Reserva reserva;
	private Reserva reserva2;
	private Reserva reserva3;
	private Reserva reserva4;
	private Reserva reserva5;

	
	private InmueblessREEMPLAZO inmuebleR;
	private PeriodoManager periodo;
	private MailSender mailSender;
	
	private EstrategiaCancelacion estrategia;

	@BeforeEach
	void setUp() throws Exception {
		propietario = mock(Propietario.class);
		mailSender = mock(MailSender.class);
		estrategia = mock(Gratuito.class);
		
		inquilino = mock(Inquilino.class);
		inquilino2 = mock(Inquilino.class);
		when(inquilino.getEmail()).thenReturn("abru@gmail.com");
		
		inquilino3 = mock(Inquilino.class);
		inquilino4 = mock(Inquilino.class);
    	
    	periodo = mock(PeriodoManager.class);
    	
    	inmuebleR = new InmueblessREEMPLAZO (9000.0, mailSender, periodo, "Quinta", (double) 123, 5, "Argentina", "Quilmes", "Calle 163 123", LocalTime.of(14,00), LocalTime.of(10,00), propietario, (double) 90000);
    			
    	
    	reserva = new Reserva (inmuebleR, inquilino, LocalDate.of(2024, 10, 15),LocalDate.of(2024, 10, 20), FormaDePago.EFECTIVO);
    	
    	reserva2 = new Reserva(inmuebleR, inquilino, LocalDate.of(2024, 10, 8),LocalDate.of(2024, 10, 16), FormaDePago.EFECTIVO );
    	
    	reserva3 = new Reserva(inmuebleR, inquilino, LocalDate.of(2024, 10, 14),LocalDate.of(2024, 10, 21), FormaDePago.EFECTIVO );
    	
    	reserva4 = new Reserva(inmuebleR, inquilino, LocalDate.of(2024, 10, 18),LocalDate.of(2024, 10, 23), FormaDePago.EFECTIVO );
    	
    	reserva5 = new Reserva (inmuebleR, inquilino, LocalDate.of(2024, 10, 10),LocalDate.of(2024, 10, 15), FormaDePago.EFECTIVO);
	}
	
	@Test
	void testSeConcretaLaSegundaaReservaDeLaColaDeEspera() {
		inmuebleR.crearReserva(reserva5);
		inmuebleR.crearReserva(reserva4);
		assertEquals(2, inmuebleR.getReservas().size());
		inmuebleR.crearReserva(reserva3);
		inmuebleR.crearReserva(reserva2);
		assertEquals(2, inmuebleR.getColaDeEspera().size());
		reserva5.cancelarReserva(LocalDate.now());		
		assertEquals(2, inmuebleR.getReservas().size());
		assertEquals(1, inmuebleR.getColaDeEspera().size());
		
		verify(mailSender).enviarMail("abru@gmail.com","Tu reserva fue procesada", 
               "Felicitaciones, como hubo una cancelación, tu reserva pudo ser realizada");
		
		
		
	}

	@Test
	void testGetHuespedes() {
		assertEquals(5, inmuebleR.getHuespedes());
	}
	
	@Test
	void testCrearReserva() {
		inmuebleR.crearReserva(reserva);
		assertEquals(1, inmuebleR.getReservas().size());
		assertTrue(inmuebleR.estaDisponible(LocalDate.of(2024, 10, 8), LocalDate.of(2024, 10, 11)));
		assertFalse(inmuebleR.estaDisponible(LocalDate.of(2024, 10, 8), LocalDate.of(2024, 10, 17)));
		
	}
	
	 @Test
	void testGetCantidadDeReservasEnLaColaDeEspera() {
		inmuebleR.crearReserva(reserva);
		inmuebleR.crearReserva(reserva2);
		assertEquals(1, inmuebleR.getColaDeEspera().size());
	}

	@Test
	void testGetMailSender() {
		assertEquals(mailSender, inmuebleR.getMailSender());
	}
	
	@Test
	void testGetColaDeEspera() {
		assertEquals(0, inmuebleR.getColaDeEspera().size());
	}
	
	@Test 
	void testGetCiudad () {
		assertEquals("Quilmes", inmuebleR.getCiudad());
	}
	
	@Test
	void testGetReserva() {
		assertEquals(0, inmuebleR.getReservas().size());
	}
	
	
	
	@Test
	void testGettersDePosteo() {
		assertEquals(inmuebleR.getPeiodoManager(), periodo);
		assertEquals(inmuebleR.getPrecioBase(), 9000.0);
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

	    
	    Double precioCalculado = inmuebleR.getPrecioParaReserva(reserva);
	    assertEquals(18000.0, precioCalculado);
	    verify(periodo).calcularPrecioPorDia(9000.0, fechaEntrada, fechaSalida);
	}
}
