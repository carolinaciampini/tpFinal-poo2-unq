package reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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
import inmueble.Inmueble;
import mailSender.MailSender;
import notificaciones.NotificadorManager;
import usuarios.Inquilino;
import usuarios.Usuario;

public class ReservaTest {
    private Inmueble inmueble;
    private Reserva reserva;
    private Usuario inquilino;
    private Aprobada aprobado;
    private Solicitada solicitado;
    private MailSender mail;
    private Rechazada rechazado;
    private Cancelada cancelado;
    private MailSender mailProp;
    private Usuario propietario;
    private Reserva reserva2;
    private NotificadorManager notificador;
    private Aprobada aprobado2;
    


    @BeforeEach
    void setUp() {
    	inquilino = mock(Inquilino.class);
    	inmueble = mock(Inmueble.class);
   	
    	mail = mock(MailSender.class);
    	mailProp = mock(MailSender.class);
    	propietario = mock(Usuario.class);
    	solicitado = spy(Solicitada.class);
    	aprobado = spy(Aprobada.class);
    	rechazado = spy(Rechazada.class);
    	cancelado = spy(Cancelada.class);
    	notificador = mock(NotificadorManager.class);
    	
   	
        reserva = new Reserva(inmueble, inquilino, LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 2), FormaDePago.EFECTIVO, mail, notificador);
        reserva2 = new Reserva(inmueble, inquilino, LocalDate.of(2025, 11, 1), LocalDate.of(2024, 11, 2), FormaDePago.EFECTIVO, mail, notificador);
        when(inmueble.getPropietario()).thenReturn(propietario);
        when(inmueble.getEmailPropietario()).thenReturn("guada@gmail.com");
        when(inquilino.getEmail()).thenReturn("caro@gmail.com");
       
       
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
	    	verify(notificador).notificarCancelacion(reserva);
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
    	
    	assertTrue(reserva.sePisa(LocalDate.of(2023, 10, 28), LocalDate.of(2024, 11, 5)));
    }
    
    

    
}





