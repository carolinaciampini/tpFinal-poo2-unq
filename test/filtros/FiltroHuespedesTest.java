package filtros;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;

public class FiltroHuespedesTest {
    private FiltroHuespedes filtroHuespedes;
    private Inmueble inmuebleCumple;
    private Inmueble inmuebleNoCumple;

    @BeforeEach
    void setUp() {
        
        filtroHuespedes = new FiltroHuespedes(4);

       
        inmuebleCumple = mock(Inmueble.class);
        inmuebleNoCumple = mock(Inmueble.class);


        when(inmuebleCumple.getHuespedes()).thenReturn(5);

     
        when(inmuebleNoCumple.getHuespedes()).thenReturn(3);
    }
    @Test
    void testCumpleConInmuebleQueSatisfaceHuespedes() {
    
        assertTrue(filtroHuespedes.cumple(inmuebleCumple, null));
    }

    @Test
    void testNoCumpleConInmuebleQueNoSatisfaceHuespedes() {
      
        assertFalse(filtroHuespedes.cumple(inmuebleNoCumple, null));
    }
}