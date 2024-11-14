package tipoInmueble;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TipoInmuebleTest {
	private TipoInmueble tipo;
	
	@BeforeEach
	void setUp() {
		tipo = new TipoInmueble("Quincho");
	}
	
	@Test
	void testGetNombre() {
		assertEquals("Quincho", tipo.getNombre());
	}
}
