package inmueble;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.FormaDePago;
import enums.Servicio;
import usuarios.Propietario;

class InmuebleTest {
	private Propietario propietario;
	private Inmueble inmueble;
	
	@BeforeEach
	void setUp() throws Exception {
		propietario = mock(Propietario.class);

		inmueble = new Inmueble ("Quinta", (double) 123, "Argentina", "Hudson", "Calle 163 123", LocalTime.of(14,00), LocalTime.of(10,00), propietario, (double) 90000);
	}

	@Test
	void testGetPropietario() {
		assertEquals(inmueble.getPropietario(), propietario);	
		}
	
	@Test
	void testTipoInmueble() {
		assertEquals(inmueble.getTipoInmueble(), "Quinta");	
		}
	
	@Test
	void testSuperficie() {
		assertEquals(inmueble.getSuperficie(), (double)123);	
		}
	
	@Test
	void testPais() {
		assertEquals(inmueble.getPais(), "Argentina");	
		}
	
	@Test
	void testCiudad() {
		assertEquals(inmueble.getCiudad(), "Hudson");	
		}
	
	@Test
	void testDireccion() {
		assertEquals(inmueble.getDireccion(), "Calle 163 123");	
		}
	
	@Test
	void testCheckin() {
		assertEquals(inmueble.getCheckin(), LocalTime.of(14,0));	
		}
	
	@Test
	void testCheckOut() {
		assertEquals(inmueble.getCheckout(), LocalTime.of(10,0));	
		}
	
	
	@Test
	void testServicios() {
		inmueble.addServicio(Servicio.AGUA);
		assertEquals(inmueble.getServicios().size(), 1);
	}
	
	@Test
	void testFormasDePagos() {
		inmueble.addFormaDePago(FormaDePago.EFECTIVO);
		assertEquals(inmueble.getFormasDePago().size(), 1);
	}

	
	
	
	
}
