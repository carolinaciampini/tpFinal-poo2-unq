package inmueble;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.FormaDePago;
import enums.Servicio;
import excepciones.LimiteFotosAlcanzado;
import excepciones.PropietarioNoRegistradoExcepcion;
import inmuebless.Inmueble;
import mailSender.MailSender;
import periodo.PeriodoManager;
import usuarios.Propietario;

class InmuebleTest {
	private Propietario propietario;
	private Inmueble inmueble;
	private MailSender mail;
	private PeriodoManager periodo;
	
	@BeforeEach
	void setUp() throws Exception {
		mail = mock(MailSender.class);
		periodo = mock(PeriodoManager.class);
		propietario = mock(Propietario.class);

		inmueble = new Inmueble ((double) 90000, mail, periodo, "Quinta", (double) 123, 5, "Argentina", "Hudson", "Calle 163 123", LocalTime.of(14,00), LocalTime.of(10,00), propietario, (double) 90000);
	
	}

	@Test
	void testGetFotos() throws LimiteFotosAlcanzado  {
		inmueble.addFoto("foto1");
		inmueble.addFoto("foto2");
		String[] expectedFotos = {"foto1", "foto2", null, null, null};

        // Compara el resultado de getFotos() con el arreglo esperado
        assertArrayEquals(expectedFotos, inmueble.getFotos());
		}

	@Test 
	void testAddFotosSinTenerLugar() throws LimiteFotosAlcanzado{
		inmueble.addFoto("foto1");
		inmueble.addFoto("foto2");
		inmueble.addFoto("foto3");
		inmueble.addFoto("foto4");
		inmueble.addFoto("foto5");
		
		String[] expectedFotos = {"foto1", "foto2", "foto3", "foto4", "foto5"};

        // Compara el resultado de getFotos() con el arreglo esperado
        assertArrayEquals(expectedFotos, inmueble.getFotos());
        assertThrows(LimiteFotosAlcanzado.class, () ->  inmueble.addFoto("Foto 6"));
	}
	
	@Test
	void testGetCapacidad() {
		assertEquals(5, inmueble.getCapacidad());
	}
	@Test
	void testGetPropietario() {
		assertEquals(propietario, inmueble.getPropietario());	
		}
	
	@Test
	void testTipoInmueble() {
		assertEquals( "Quinta", inmueble.getTipoInmueble());	
		}
	
	@Test
	void testSuperficie() {
		assertEquals((double)123, inmueble.getSuperficie());	
		}
	
	
	@Test
	void testPais() {
		assertEquals("Argentina", inmueble.getPais());	
		}
	
	@Test
	void testCiudad() {
		assertEquals("Hudson", inmueble.getCiudad());	
		}
	
	@Test
	void testDireccion() {
		assertEquals("Calle 163 123", inmueble.getDireccion());	
		}
	
	@Test
	void testCheckin() {
		assertEquals(LocalTime.of(14,0), inmueble.getCheckin());	
		}
	
	@Test
	void testCheckOut() {
		assertEquals(LocalTime.of(10,0), inmueble.getCheckout());	
		}
	
	
	@Test
	void testServicios() {
		inmueble.addServicio(Servicio.AGUA);
		assertEquals(1, inmueble.getServicios().size());
	}
	
	@Test
	void testFormasDePagos() {
		inmueble.addFormaDePago(FormaDePago.EFECTIVO);
		assertEquals(1, inmueble.getFormasDePago().size());
	}

	
	
	
	
}
