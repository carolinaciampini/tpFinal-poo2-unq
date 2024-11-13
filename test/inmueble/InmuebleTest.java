package inmueble;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.FormaDePago;
import estadoReserva.Aprobada;
import estadoReserva.EstadoReserva;
import estrategiaCancelacion.EstrategiaCancelacion;
import estrategiaCancelacion.Gratuito;
import estrategiaCancelacion.SinCancelacion;
import excepciones.LimiteFotosAlcanzado;
import excepciones.PropietarioNoRegistradoExcepcion;
import mailSender.MailSender;
import notificaciones.NotificadorManager;
import periodo.PeriodoManager;
import reserva.Reserva;
import servicio.Servicio;
import usuarios.Inquilino;
import usuarios.Propietario;
import usuarios.Usuario;

class InmuebleTest {

	private Usuario inquilino;

	
	private Reserva reserva;
	private Reserva reserva2;
	private Reserva reserva5;
	
	private Inmueble inmuebleR;
	private PeriodoManager periodo;
	private MailSender mailSender;

	private SinCancelacion estrategia;
	private NotificadorManager notificador;
	private Usuario propietario;
	private Inmueble inmueble;
	private MailSender mail;
	
	private Servicio agua;

	
	@BeforeEach
	void setUp() throws Exception {
		agua = mock(Servicio.class);
		mail = mock(MailSender.class);
		periodo = mock(PeriodoManager.class);
		propietario = mock(Usuario.class);
		when(propietario.getEmail()).thenReturn("abru@gmail.com");	
		mailSender = mock(MailSender.class);
		
		estrategia = mock(SinCancelacion.class);
		periodo = mock(PeriodoManager.class);
		notificador = mock(NotificadorManager.class);
	
		inquilino = mock(Inquilino.class);
		when(inquilino.getEmail()).thenReturn("abru@gmail.com");
	
		reserva2 = mock(Reserva.class);
		// when(reserva2.getEstadoReserva()).thenReturn()
		reserva5 = mock(Reserva.class);
	
		
		inmueble = new Inmueble ( 90000.0, mail, periodo, "Quinta", 1.23, 5, "Argentina", "Hudson", "Calle 163 123", LocalTime.of(14,00), LocalTime.of(10,00), propietario, 90.000, notificador);
		inmuebleR = new Inmueble (9000.0, mailSender, periodo, "Quinta", 1.23, 5, "Argentina", "Quilmes", "Calle 163 123", LocalTime.of(14,00), LocalTime.of(10,00), propietario, 90.000, notificador);
		
   			
	}
	
	
	@Test
	void testGetMailSender() {
		assertEquals(mail, inmueble.getMailSender());
	}
	@Test
	void testCalcularPrecioPorDia() {
		inmuebleR.precioSugeridoPara(LocalDate.of(2024, 11, 12), LocalDate.of(2024, 11, 15));
		verify(periodo).calcularPrecioPorDia(9000.0,LocalDate.of(2024, 11, 12), LocalDate.of(2024, 11, 15));
	}
	
	@Test
	void testGetHuespedes() {
		assertEquals(5, inmuebleR.getHuespedes());
	}
	
	@Test
	void testCalcularPenalizacion() {
		inmuebleR.setEstrategiaCancelacion(estrategia);
		inmuebleR.calcularPenalizacion(reserva2);
		verify(estrategia).calcularPenalizacion(reserva2, inmuebleR);
	}
	
	@Test
	void testEliminarReserva() {
		inmuebleR.crearReserva(reserva2);
	    assertEquals(1, inmuebleR.getReservas().size());
	    
	    inmuebleR.eliminarReserva(reserva2);
	    assertEquals(0, inmuebleR.getReservas().size());
	}
	
	@Test 
	void testSeteoEstrategia() {
		inmueble.setEstrategiaCancelacion(estrategia);
		
		assertEquals(estrategia, inmueble.getEstrategia());
	}
	
	@Test
	void testProcesarColaDeEspera() {
		when(reserva5.getFechaEntrada()).thenReturn(LocalDate.of(2024, 10, 8));
	    when(reserva5.getFechaSalida()).thenReturn(LocalDate.of(2024, 10, 11));
	    when(reserva2.getFechaEntrada()).thenReturn(LocalDate.of(2024, 10, 9));
	    when(reserva2.getFechaSalida()).thenReturn(LocalDate.of(2024, 10, 11));
	    
		when(reserva5.sePisa(any(LocalDate.class), any(LocalDate.class))).thenReturn(true);
		when(reserva2.sePisa(any(LocalDate.class), any(LocalDate.class))).thenReturn(true); 
	    
	    when(reserva2.getInquilino()).thenReturn(inquilino);
	    when(reserva5.getInmueble()).thenReturn(inmuebleR);
		
	    inmuebleR.crearReserva(reserva5); 
	    inmuebleR.crearReserva(reserva2);
	    when(reserva5.esEstadoAprobado()).thenReturn(true);
	    when(reserva2.esEstadoAprobado()).thenReturn(true);

	    // mockeo comportamiento para que se cancele una reserva
	    when(reserva5.sePisa(any(LocalDate.class), any(LocalDate.class))).thenReturn(false);
	    when(reserva2.sePisa(any(LocalDate.class), any(LocalDate.class))).thenReturn(false); 
	    inmuebleR.procesarColaEspera();
	    verify(mailSender).enviarMail("abru@gmail.com", "Tu reserva fue procesada",
	            "Felicitaciones, como hubo una cancelación, tu reserva pudo ser realizada");
	}
	
	
	@Test
	void testSePuedenEncolarReservas() {
		when(reserva5.getFechaEntrada()).thenReturn(LocalDate.of(2024, 10, 8));
	    when(reserva5.getFechaSalida()).thenReturn(LocalDate.of(2024, 10, 11));
	    when(reserva2.getFechaEntrada()).thenReturn(LocalDate.of(2024, 10, 9));
	    when(reserva2.getFechaSalida()).thenReturn(LocalDate.of(2024, 10, 11));
	    
		when(reserva5.sePisa(any(LocalDate.class), any(LocalDate.class))).thenReturn(true);
		when(reserva2.sePisa(any(LocalDate.class), any(LocalDate.class))).thenReturn(true); // Se solapa
	    
	    when(reserva2.getInquilino()).thenReturn(inquilino);
	    when(reserva5.getInmueble()).thenReturn(inmuebleR);
		
	    inmuebleR.crearReserva(reserva5); 
	    inmuebleR.crearReserva(reserva2);
	    assertEquals(1, inmuebleR.getReservas().size());
	    assertEquals(1, inmuebleR.getColaDeEspera().size());		
	}
	
	
	
	@Test
	void testCrearReserva() {
		reserva = mock(Reserva.class);
		 when(reserva.sePisa(LocalDate.of(2024, 10, 11), LocalDate.of(2024, 10, 17))).thenReturn(false);
		 when(reserva.sePisa(LocalDate.of(2024, 10, 8), LocalDate.of(2024, 10, 11))).thenReturn(true);

	    inmuebleR.crearReserva(reserva);
	    
	    verify(notificador).altaDeReserva(reserva);
	    assertEquals(1, inmuebleR.getReservas().size());
	    assertFalse(inmuebleR.estaDisponible(LocalDate.of(2024, 10, 8), LocalDate.of(2024, 10, 11)));
	    assertTrue(inmuebleR.estaDisponible(LocalDate.of(2024, 10, 11), LocalDate.of(2024, 10, 17)));
		
	}
	
	
	@Test
	void testPrecioParaUnaReserva() {
	  
	    LocalDate fechaEntrada = LocalDate.of(2024, 11, 1);
	    LocalDate fechaSalida = LocalDate.of(2024, 11, 3); // 2 noches
	    reserva = mock(Reserva.class);
	    when(reserva.getFechaEntrada()).thenReturn(fechaEntrada);
	    when(reserva.getFechaSalida()).thenReturn(fechaSalida);
	    
	    
	    when(periodo.calcularPrecioPorDia(9000.0, fechaEntrada, fechaSalida)).thenReturn(18000.0); 

	    Double precioCalculado = inmuebleR.getPrecioParaReserva(reserva);
	    assertEquals(18000.0, precioCalculado);
	    verify(periodo).calcularPrecioPorDia(9000.0, fechaEntrada, fechaSalida);
	}
	
	@Test
	void testSeBajaElPrecioDeUnInmueble() {
		inmueble.seBajaElPrecioDelInmueble(600.00);
		verify(notificador).bajaDePrecio(inmueble);
		assertEquals(600.00, inmueble.getPrecioBase());
	}
	
	@Test
	void testNoSeBajaElPrecioDeUnInmueble() {
		inmueble.seBajaElPrecioDelInmueble(100000.00);
		verify(notificador, times(0)).bajaDePrecio(inmueble);
		assertEquals(90000.00, inmueble.getPrecioBase()); // no baja porque el precio pasado por parametro no es menor al precioBase que tenia el inmjueble
	}

	/*
	@Test
	void testGetFotos() throws LimiteFotosAlcanzado  {
		inmuebleR.addFoto("foto1");
		inmuebleR.addFoto("foto2");
		
		String[] expectedFotos = {"foto1", "foto2", null, null, null};

        // Compara el resultado de getFotos() con el arreglo esperado
        assertArrayEquals(expectedFotos, inmueble.getFotos());
		}
	*/	

	@Test 
	void testAddFotosSinTenerLugar() throws LimiteFotosAlcanzado{
		inmuebleR.addFoto("foto1");
		inmuebleR.addFoto("foto2");
		inmuebleR.addFoto("foto3");
		inmuebleR.addFoto("foto4");
		inmuebleR.addFoto("foto5");
		
		String[] expectedFotos = {"foto1", "foto2", "foto3", "foto4", "foto5"};

        // Compara el resultado de getFotos() con el arreglo esperado
        assertArrayEquals(expectedFotos, inmuebleR.getFotos());
        assertThrows(LimiteFotosAlcanzado.class, () ->  inmuebleR.addFoto("Foto 6"));
	}
	
	
	@Test
	void testGetCapacidad() {
		assertEquals(5, inmuebleR.getCapacidad());
	}
	@Test
	void testGetPropietario() {
		assertEquals(propietario, inmuebleR.getPropietario());	
		}
	
	@Test
	void testTipoInmueble() {
		assertEquals( "Quinta", inmuebleR.getTipoInmueble());	
		}
	
	@Test
	void testSuperficie() {

		assertEquals(1.23, inmueble.getSuperficie());	
		}
	
	
	@Test
	void testPais() {
		assertEquals("Argentina", inmuebleR.getPais());	
		}
	
	@Test
	void testCiudad() {
		assertEquals("Hudson", inmueble.getCiudad());	
		}
	
	@Test
	void testDireccion() {
		assertEquals("Calle 163 123", inmuebleR.getDireccion());	
		}
	
	@Test
	void testCheckin() {
		assertEquals(LocalTime.of(14,0), inmuebleR.getCheckin());	
		}
	
	@Test
	void testCheckOut() {
		assertEquals(LocalTime.of(10,0), inmuebleR.getCheckout());	
		}
	
	
	@Test
	void testServicios() {
		inmuebleR.addServicioo(agua);
		assertEquals(1, inmuebleR.getServicios().size());
	}
	
	@Test
	void testSacarServicios() {
		inmuebleR.addServicioo(agua);
		inmuebleR.sacarServicio(agua);
		assertEquals(0, inmuebleR.getServicios().size());
	}
	
	@Test
	void testFormasDePagos() {
		inmuebleR.addFormaDePago(FormaDePago.EFECTIVO);
		assertEquals(1, inmuebleR.getFormasDePago().size());
	}

	@Test 
	void testGetEmailPropietario() {
		assertEquals("abru@gmail.com", inmueble.getEmailPropietario());
	}
	
	
}
