package filtros;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;

public class FiltroCiudadTest {
	private FiltroCiudad filtroCiudad;
    private Inmueble inmuebleCorrecto;
    private Inmueble inmuebleIncorrecto;
	   @BeforeEach
	    void setUp() {
	        
	        filtroCiudad = new FiltroCiudad("Buenos Aires");

	        
	        inmuebleCorrecto = mock(Inmueble.class);
	        inmuebleIncorrecto = mock(Inmueble.class);

	        when(inmuebleCorrecto.getCiudad()).thenReturn("Buenos Aires");
	        when(inmuebleIncorrecto.getCiudad()).thenReturn("Cordoba");
	    }
	   
	   @Test
	    void testCumpleConCiudadCorrecta() {
	       
	        assertTrue(filtroCiudad.cumple(inmuebleCorrecto, null));
	    }

	    @Test
	    void testNoCumpleConCiudadIncorrecta() {
	        assertFalse(filtroCiudad.cumple(inmuebleIncorrecto, null));
	    }
}
