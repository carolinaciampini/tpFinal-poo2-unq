package inmueble;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import categoria.Categoria;
import enums.FormaDePago;
import estadoReserva.Aprobada;
import estadoReserva.EstadoReserva;
import estrategiaCancelacion.EstrategiaCancelacion;
import estrategiaCancelacion.Gratuito;
import estrategiaCancelacion.SinCancelacion;
import excepciones.LimiteFotosAlcanzado;
import excepciones.NoHayPuntajesParaEstaCategoria;
import excepciones.NoHayPuntajesSobreEsteInmueble;
import excepciones.NoHayPuntajesSobreEsteUsuario;
import excepciones.PropietarioNoRegistradoExcepcion;
import mailSender.MailSender;
import notificaciones.NotificadorManager;
import periodo.PeriodoManager;
import ranking.Ranking;
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
	
	private Ranking ranking1;
	private Ranking ranking2;
	private Ranking ranking3;
	
	private Categoria categoria;
	private Categoria otraCategoria;
	
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
		
		ranking1 = mock(Ranking.class);
		ranking2 = mock(Ranking.class);
		ranking3 = mock(Ranking.class);
		
		categoria = mock(Categoria.class);
		otraCategoria = mock(Categoria.class);
		
		when(ranking1.getComentario()).thenReturn("Increible patio!");
		when(ranking2.getComentario()).thenReturn("Hermosa quinta");
		when(ranking3.getComentario()).thenReturn("No es tan espaciosa");
		
		when(ranking1.getPuntaje()).thenReturn(4);
		when(ranking2.getPuntaje()).thenReturn(3);
		when(ranking3.getPuntaje()).thenReturn(3);
		
		when(ranking1.getCategoria()).thenReturn(otraCategoria);
		when(ranking2.getCategoria()).thenReturn(categoria);
		when(ranking3.getCategoria()).thenReturn(categoria);
		
		
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


	@Test
	void testGetFotos() throws LimiteFotosAlcanzado  {
		inmuebleR.addFoto("foto1");
		inmuebleR.addFoto("foto2");
		
		String[] expectedFotos = {"foto1", "foto2", null, null, null};

        // Compara el resultado de getFotos() con el arreglo esperado
        assertArrayEquals(expectedFotos, inmuebleR.getFotos());
		}
	
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
	
	
	@Test 
	void testVisualizarComentarios() {
		inmueble.recibirRanking(ranking1);
		inmueble.recibirRanking(ranking2);
		inmueble.recibirRanking(ranking3);
		
		List<String> expectedComentarios = Arrays.asList("Increible patio!", "Hermosa quinta", "No es tan espaciosa");
		// No corre
	    assertEquals(expectedComentarios, inmueble.visualizarComentarios());

	}
	@Test
	void testGetRankings() {
		inmueble.recibirRanking(ranking1);
		inmueble.recibirRanking(ranking2);
		inmueble.recibirRanking(ranking3);
		
		List<Ranking> expectedRankings = Arrays.asList(ranking1, ranking2, ranking3);
		
		assertEquals(expectedRankings, inmueble.getRankings());
	}
	
	@Test
	void testPromedioPuntajeTotal() throws NoHayPuntajesSobreEsteInmueble {
		inmueble.recibirRanking(ranking1);
		inmueble.recibirRanking(ranking2);
		inmueble.recibirRanking(ranking3);
		
		Integer promedio = 3;
		
		assertEquals(promedio, inmueble.promedioPuntajeTotal());
		
	}
	
	@Test
	void testPromedioPuntajeTotalLanzaExcepcion() throws NoHayPuntajesSobreEsteInmueble {
		assertThrows(NoHayPuntajesSobreEsteInmueble.class, () -> {
	        inmueble.promedioPuntajeTotal();
	    });
	}
	
	@Test
	void testPromedioPorCategoria() throws NoHayPuntajesParaEstaCategoria {
		inmueble.recibirRanking(ranking1);
		inmueble.recibirRanking(ranking2);
		inmueble.recibirRanking(ranking3);
		
		Integer promedioCategoria = 3;
		 
		assertEquals(promedioCategoria, inmueble.promedioPorCategoria(categoria));
	}
	
	@Test
	void testPromedioPorCategoriaLanzaExcepcion() throws NoHayPuntajesParaEstaCategoria {
		assertThrows(NoHayPuntajesParaEstaCategoria.class, () -> {
	        inmueble.promedioPorCategoria(categoria);
	    });
	}
	
	@Test
	void testVisualizarPromedioDePropietario() throws NoHayPuntajesSobreEsteUsuario {
		inmueble.visualizarPromedioDePropietario();
		
		verify(propietario).puntajePromedioComoPropietario();
	}
	
	@Test
	void testVisualizarPuntajesDePropietario() {
		inmueble.visualizarPuntajesDePropietario();
		
		verify(propietario).getRankingsComoPropietario();
	}
	
	
	
}
