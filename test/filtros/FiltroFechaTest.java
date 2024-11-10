package filtros;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;

public class FiltroFechaTest {
	private FiltroFecha filtroFecha;
    private Inmueble inmuebleDisponible;
    private Inmueble inmuebleNoDisponible;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;

    @BeforeEach
    void setUp() {
        
        fechaEntrada = LocalDate.of(2024, 11, 10);
        fechaSalida = LocalDate.of(2024, 11, 15);

       
        filtroFecha = new FiltroFecha(fechaEntrada, fechaSalida);

        inmuebleDisponible = mock(Inmueble.class);
        inmuebleNoDisponible = mock(Inmueble.class);

        
        when(inmuebleDisponible.estaDisponible(fechaEntrada, fechaSalida)).thenReturn(true);
        when(inmuebleNoDisponible.estaDisponible(fechaEntrada, fechaSalida)).thenReturn(false);
    }
    
    @Test
    void testCumpleConInmuebleDisponible() {
        
        assertTrue(filtroFecha.cumple(inmuebleDisponible, null));
    }

    @Test
    void testNoCumpleConInmuebleNoDisponible() {
        
        assertFalse(filtroFecha.cumple(inmuebleNoDisponible, null));
    }
}
