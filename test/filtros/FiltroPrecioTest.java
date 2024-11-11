package filtros;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;

public class FiltroPrecioTest {
    private FiltroPrecio filtroPrecio;
    private Inmueble inmuebleDentroRango;
    private Inmueble inmuebleFueraRango;
    private Filtro filtro;

    @BeforeEach
    void setUp() {
        
        filtroPrecio = new FiltroPrecio(1000.0, 1500.0);
        inmuebleDentroRango = mock(Inmueble.class);
        inmuebleFueraRango = mock(Inmueble.class);
        filtro = mock(Filtro.class);

        
        LocalDate fechaEntrada = LocalDate.of(2024, 11, 10);
        LocalDate fechaSalida = LocalDate.of(2024, 11, 15);
        when(filtro.getFechaEntrada()).thenReturn(fechaEntrada);
        when(filtro.getFechaSalida()).thenReturn(fechaSalida);

        
        when(inmuebleDentroRango.precioSugeridoPara(fechaEntrada, fechaSalida)).thenReturn(1200.0);
        when(inmuebleFueraRango.precioSugeridoPara(fechaEntrada, fechaSalida)).thenReturn(1600.0);
    }

    @Test
    void testCumpleConInmuebleDentroDelRangoDePrecios() {
        
        assertTrue(filtroPrecio.cumple(inmuebleDentroRango, filtro));
    }

    @Test
    void testNoCumpleConInmuebleFueraDelRangoDePrecios() {
       
        assertFalse(filtroPrecio.cumple(inmuebleFueraRango, filtro));
    }
}