package reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
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
import usuarios.Inquilino;
import usuarios.Usuario;

public class ReservaTest {
    private Inmueble inmueble;
    private Reserva reserva;
    private Usuario inquilino;
    private Usuario propietario;
    private Aprobada aprobado;
    private Solicitada solicitado;
    private Cancelada cancelada;
    private Rechazada rechazada;
    private Finalizada finalizada;
    


    @BeforeEach
    void setUp() {
    	inquilino = mock(Inquilino.class);
    	propietario = mock(Usuario.class);
    	inmueble = mock(Inmueble.class);

    	solicitado = mock(Solicitada.class);
    	aprobado = mock(Aprobada.class);
        reserva = new Reserva(inmueble, inquilino, LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 2), FormaDePago.EFECTIVO);
        when(inmueble.getPrecioParaReserva(reserva)).thenReturn(140.0);
        reserva.setEstadoReserva(solicitado);
        

    }
 
 
    	@Test
        void testAceptarReserva () {
        reserva.aceptarReserva();
        verify(solicitado).aceptarReserva(reserva);
        
     } 
    	
    	@Test
        void testRechazarReserva () {
        reserva.setEstadoReserva(aprobado);
        reserva.rechazarReserva();
        verify(aprobado).rechazarReserva(reserva);
        assertTrue(!inmueble.getReservas().contains(reserva));
        
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