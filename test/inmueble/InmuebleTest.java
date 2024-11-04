package inmueble;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.FormaDePago;
import enums.Servicio;
import usuarios.Propietario;

class InmuebleTest {
	private Propietario propietario;
	private Inmueble inmueble;
	private Servicio servicio;
	private FormaDePago formaDePago;
	
	@BeforeEach
	void setUp() throws Exception {
		propietario = new Propietario("Abril", "abru@gmail.com", "1111111");
		inmueble = new Inmueble ("Quinta", (double) 123, "Argentina", "Hudson", "Calle 163 123", LocalTime.of(14,00), LocalTime.of(10,00), propietario, (double) 90000);
		servicio = Servicio.AGUA;
		formaDePago = FormaDePago.EFECTIVO;
		
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
	void testPrecioBase() {
		assertEquals(inmueble.getPrecioBase(), (double) 90000);	
		}
	
	@Test
	void testServicios() {
		inmueble.addServicio(servicio);
		assertEquals(inmueble.getServicios().size(), 1);
	}
	
	@Test
	void testFormasDePagos() {
		inmueble.addFormaDePago(formaDePago);
		assertEquals(inmueble.getFormasDePago().size(), 1);
	}

	
	
	
	
}
