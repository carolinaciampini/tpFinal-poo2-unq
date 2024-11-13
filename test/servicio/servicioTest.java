package servicio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class servicioTest {
	private Servicio servicio;
	
	@BeforeEach
	void setUp() throws Exception {
		servicio = new Servicio("AGUA");
	}

	@Test
	void testGetNombre() {
		assertEquals("AGUA", servicio.getNombre());
		}

}
