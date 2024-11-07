package reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.FormaDePago;
import estadoReserva.Aprobada;
import estadoReserva.EstadoReserva;
import estadoReserva.Solicitada;
import inmueble.Inmueble;
import posteo.Posteo;
import usuarios.Inquilino;
import usuarios.Usuario;

public class ReservaTest {
    private Posteo posteoInmueble;
    private Reserva reserva;
    private Usuario inquilino;
    private Inmueble inmueble;


    @BeforeEach
    void setUp() {
    	inquilino = mock(Inquilino.class);
    	inmueble = mock(Inmueble.class);
    	posteoInmueble = mock(Posteo.class);

        reserva = new Reserva(posteoInmueble, inmueble, inquilino, LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 2), FormaDePago.EFECTIVO);
        when(posteoInmueble.getPrecioParaReserva(reserva)).thenReturn(140.0);
        
    }
    
    
    @Test
    void testGettersDeLaReserva() {
        
        assertEquals(posteoInmueble, reserva.getPosteo());
        assertEquals(inmueble, reserva.getInmueble());
        assertEquals(inquilino, reserva.getInquilino());
        assertEquals(LocalDate.of(2024, 11, 1), reserva.getFechaEntrada());
        assertEquals(LocalDate.of(2024, 11, 2), reserva.getFechaSalida());
        assertEquals(FormaDePago.EFECTIVO, reserva.getFormaDePago());
      
    }
    
   @Test
   void testCantidadDias() {
	   assertEquals(1, reserva.getCantidadDeDias());
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