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
import usuarios.Inquilino;
import usuarios.Usuario;

public class ReservaTest {
    private Inmueble inmueble;
    private Reserva reserva;
    private Usuario inquilino;
    private Aprobada aprobado;
    private Solicitada solicitado;
    private MailSender mail;

    


    @BeforeEach
    void setUp() {
    	inquilino = mock(Inquilino.class);
    	inmueble = mock(Inmueble.class);
   	
    	mail = mock(MailSender.class);
    	solicitado = mock(Solicitada.class);
    	aprobado = spy(Aprobada.class);
   	
        reserva = new Reserva(inmueble, inquilino, LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 2), FormaDePago.EFECTIVO, mail);
        when(inmueble.getPrecioParaReserva(reserva)).thenReturn(140.0);
        when(inquilino.getEmail()).thenReturn("caro@gmail.com");
        reserva.setEstadoReserva(solicitado);
       
    }
 
 
    	@Test
        void testAceptarReserva () {  	
        reserva.aceptarReserva();
        verify(solicitado).aceptarReserva(reserva);
        
        reserva.setEstadoReserva(aprobado);
        assertEquals(aprobado, reserva.getEstadoReserva());
    	} 
    	
    	@Test 
    	void testSeEnviaMailCuandoSeApruebaLaReserva() {
    		reserva.setEstadoReserva(aprobado);
    	    reserva.enviarMail();
    	    verify(aprobado).enviarMail(reserva);
    	       
    	    verify(mail).enviarMail(
    	                "caro@gmail.com", 
    	                "Tu reserva ha sido aprobada", 
    	                "¡Felicitaciones! tu reserva fue aprobada"
    	    );
    	}
    	
    	@Test
        void testRechazarReserva () {
        reserva.setEstadoReserva(aprobado);
        reserva.rechazarReserva();
        verify(aprobado).rechazarReserva(reserva);
        assertFalse(inmueble.getReservas().contains(reserva));
        
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
    	
    	 Double precioTotal = reserva.getPrecioTotal();
         Double precioEsperado = 140.0; 
         assertEquals(precioEsperado, precioTotal);
    }
    
    @Test
    void testSePisa () {
    	
    	assertTrue(reserva.sePisa(LocalDate.of(2023, 10, 28), LocalDate.of(2024, 11, 5)));
    }

    
}