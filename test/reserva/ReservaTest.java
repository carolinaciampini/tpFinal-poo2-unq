package reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.FormaDePago;
import estadoReserva.Aprobada;
import estadoReserva.Cancelada;
import estadoReserva.EstadoReserva;
import estadoReserva.Finalizada;
import estadoReserva.Rechazada;
import estadoReserva.Solicitada;
import excepciones.EstadoInvalidoParaRankear;
import inmueble.Inmueble;
import mailSender.MailSender;
import notificaciones.NotificadorManager;
import ranking.Ranking;
import usuarios.IInquilino;
import usuarios.IPropietario;
import usuarios.Usuario;

public class ReservaTest {
    private Inmueble inmueble;
    private Reserva reserva;
    private IInquilino inquilino;
    private Aprobada aprobado;
    private Finalizada finalizado;
    private Solicitada solicitado;
    private MailSender mail;
    private IPropietario propietario;
    private NotificadorManager notificador;
    private Ranking ranking;
    


    @BeforeEach
    void setUp() {
    	inquilino = mock(IInquilino.class);
    	inmueble = mock(Inmueble.class);
   	
    	mail = mock(MailSender.class);
    	propietario = mock(IPropietario.class);
    	solicitado = spy(Solicitada.class);
    	aprobado = spy(Aprobada.class);
    	finalizado = spy(Finalizada.class);
    	notificador = mock(NotificadorManager.class);
    	ranking = mock(Ranking.class);
   	
        reserva = new Reserva(inmueble, inquilino, LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 2), FormaDePago.EFECTIVO, mail, notificador);
        when (reserva.getPropietario()).thenReturn(propietario);
        
        when(inmueble.getPropietario()).thenReturn(propietario);
        when(inmueble.getEmailPropietario()).thenReturn("guada@gmail.com");
        when(inquilino.getEmail()).thenReturn("caro@gmail.com");
        when(inmueble.getTipoInmueble()).thenReturn("Casa");
       
       
    }
    
 
    	@Test
    	void testAlCancelarUnaReservaSeDeterminaUnaPenalizacion() {
    		reserva.setEstadoReserva(aprobado);
    		reserva.cancelarReserva();
    		verify(inmueble).calcularPenalizacion(reserva);
    	}
    	
    	
    
    	@Test
    	void testRankear_LanzaExcepcionSiNoEstaFinalizada() {
    		assertThrows(EstadoInvalidoParaRankear.class, () -> reserva.rankearInmueble(ranking));
    		assertThrows(EstadoInvalidoParaRankear.class, () -> reserva.rankearPropietario(ranking));
    		assertThrows(EstadoInvalidoParaRankear.class, () -> reserva.rankearInquilino(ranking));
    	}
    	
    	@Test
        void testRankearInmueble_CuandoEstaFinalizada() throws EstadoInvalidoParaRankear {
            reserva.setEstadoReserva(finalizado);
            reserva.rankearInmueble(ranking);
            verify(inmueble).recibirRanking(ranking);
        }
    	
    	@Test
        void testRankearPropietario_CuandoEstaFinalizada() throws EstadoInvalidoParaRankear {
    		reserva.setEstadoReserva(finalizado);
            reserva.rankearPropietario(ranking);
            verify(propietario).recibirRankingComoPropietario(ranking);
        }
    	
    	@Test
        void testRankearInquilino_CuandoEstaFinalizada() throws EstadoInvalidoParaRankear {
            reserva.setEstadoReserva(finalizado);
            reserva.rankearInquilino(ranking);
            verify(inquilino).recibirRankingComoInquilino(ranking);
        }
    	
    	@Test
    	void testCantidadDiasFaltantesParaQueSeConcreteLaReserva() {
    		int diasFaltantes = (int) ChronoUnit.DAYS.between(LocalDate.now(), reserva.getFechaEntrada());
    		assertEquals(diasFaltantes, reserva.cantidadDiasFaltantes());
    	}
 
    	@Test
        void testSeAceptaLaReservaYSeEnviaMail () {  	
    	reserva.setEstadoReserva(solicitado);
        reserva.aceptarReserva();
        verify(solicitado).aceptarReserva(reserva);
        
        
        
        assertTrue(reserva.esEstadoAprobado());
        assertFalse(reserva.esEstadoRechazado());
        verify(mail).enviarMail(
                "caro@gmail.com", 
                "Tu reserva ha sido aprobada", 
                "¡Felicitaciones! tu reserva fue aprobada"
        		);
        
        
    	} 
    	
    	
    	@Test
        void testRechazarReserva () {
	        reserva.setEstadoReserva(solicitado);
	        reserva.rechazarReserva();
	        
	        verify(solicitado).rechazarReserva(reserva);
	        assertFalse(inmueble.getReservas().contains(reserva));
	        assertTrue(reserva.esEstadoRechazado());
	        assertFalse(reserva.esEstadoAprobado());        
    	} 

  
    	@Test 
    	void testSeEnviaMailCuandoSeRechazaLaReserva() {
    		reserva.setEstadoReserva(solicitado);
    		reserva.rechazarReserva();
    		assertTrue(reserva.esEstadoRechazado());
    		
    		verify(mail).enviarMail(
    					 "caro@gmail.com", 
    					 "Reserva rechazada",
    					 "¡La reserva para tu Inmueble fue rechazada!"
    		);
    	}
    	
    	
    	@Test	
    	void testFinalizarReserva () {
	    	reserva.setEstadoReserva(aprobado);
	    	reserva.finalizarReserva();
	    	verify(aprobado).finalizarReserva(reserva);

    	}
    	
    	@Test
    	void testCancelarReserva () {
	    	reserva.setEstadoReserva(aprobado);	
	    	reserva.cancelarReserva();
	
	    	verify(aprobado).cancelarReserva(reserva);
	    	verify(notificador).cancelacionDeReserva(reserva);
    	}
    	
    	
    	@Test 
    	void testSeEnviaMailCuandoSeCancelaLaReserva() {
    		reserva.setEstadoReserva(aprobado);	
    		reserva.cancelarReserva();
    		verify(mail).enviarMail(
    					"guada@gmail.com", 
    					 "Reserva cancelada",
    					 "¡La reserva para tu Inmueble fue cancelada!"
    		);
    	}
    	
    	
    @Test
    void testUnaReservaEstaAprobada () {
    		reserva.setEstadoReserva(aprobado);
    		assertTrue(reserva.esEstadoAprobado());
    		
    	}
    	
    @Test
    void testGettersDeLaReserva() {
        
        assertEquals(inmueble, reserva.getInmueble());
        assertEquals(inquilino, reserva.getInquilino());
        assertEquals(LocalDate.of(2024, 11, 1), reserva.getFechaEntrada());
        assertEquals(LocalDate.of(2024, 11, 2), reserva.getFechaSalida());
        assertEquals(FormaDePago.EFECTIVO, reserva.getFormaDePago());
 	   	assertEquals(1, reserva.cantidadDeDias());

    }
   
    @Test
    void testCalcularPrecioTotalDeLaReserva() {
    	when(inmueble.getPrecioParaReserva(reserva)).thenReturn(140.0);
         assertEquals(140.0, reserva.getPrecioTotal());
    }
    
    @Test
    void testLaReservaSePisaConOtraReserva () {
    	assertFalse(reserva.sePisa(LocalDate.of(2023, 01, 28), LocalDate.of(2024, 01, 5)));
    	assertTrue(reserva.sePisa(LocalDate.of(2023, 10, 28), LocalDate.of(2024, 11, 5)));
    }
    
    @Test
    void testGetTipoInmueble() {
    	assertEquals("Casa", reserva.getTipoInmueble());
    }
    
    @Test
    void testGetEstadoReserva() {
    	reserva.setEstadoReserva(aprobado);
    	assertEquals(aprobado, reserva.getEstadoReserva());
    }

    
}





