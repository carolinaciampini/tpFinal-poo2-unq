package filtros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;

public class FiltroTest {
	private Filtro filtro;
	private Inmueble inmueble1;
	private Inmueble inmueble2;
	private Inmueble inmueble3;
	
	@BeforeEach
	void setUp() {
		LocalDate fechaEntrada = LocalDate.of(2024, 11, 10);
	    LocalDate fechaSalida = LocalDate.of(2024, 11, 15);
	    String ciudad = "Buenos Aires";
		filtro = new Filtro(fechaEntrada, fechaSalida, ciudad);
		inmueble1 = mock(Inmueble.class);
		when(inmueble1.getCiudad()).thenReturn("Buenos Aires");
        when(inmueble1.estaDisponible(fechaEntrada,fechaSalida)).thenReturn(true); 
        when(inmueble1.precioSugeridoPara(fechaEntrada,fechaSalida)).thenReturn(1100.00);

		inmueble2 = mock(Inmueble.class);
		 when(inmueble2.getCiudad()).thenReturn("Buenos Aires");
	     when(inmueble2.estaDisponible(fechaEntrada, fechaSalida)).thenReturn(false);
	     
		inmueble3 = mock(Inmueble.class);
		when(inmueble3.getCiudad()).thenReturn("Cordoba");
        when(inmueble3.estaDisponible(fechaEntrada, fechaSalida)).thenReturn(true); 
        when(inmueble3.precioSugeridoPara(fechaEntrada,fechaSalida)).thenReturn(1100.00);
			
	}
	
	@Test 
	void testFiltrarConFiltrosObligatorios() {
		assertEquals(1, this.filtro.filtrar(Arrays.asList(inmueble1, inmueble2, inmueble3)).size());

	}
	
	
	@Test
	void testFiltrarAgregandoHuespedes() {
		this.filtro.agregarFiltro(new FiltroHuespedes(4));
		 when(inmueble1.getHuespedes()).thenReturn(5);
		 when(inmueble3.getHuespedes()).thenReturn(3);
		 assertEquals(1, this.filtro.filtrar(Arrays.asList(inmueble1, inmueble3)).size());
		 assertFalse(this.filtro.filtrar(Arrays.asList(inmueble1, inmueble3)).contains(inmueble3));

	}
	

	@Test
	void testFiltrarAgregandoPrecios() {
		this.filtro.agregarFiltro(new FiltroPrecio(1000.0, 1500.0));
		assertEquals(1, this.filtro.filtrar(Arrays.asList(inmueble2, inmueble1)).size());
		assertTrue(this.filtro.filtrar(Arrays.asList(inmueble2, inmueble1)).contains(inmueble1));
	    assertFalse(this.filtro.filtrar(Arrays.asList(inmueble2, inmueble1)).contains(inmueble2));
	}

}
