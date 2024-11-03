package inmueble;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usuarios.Propietario;

class InmuebleTest {
	private Propietario propietario;
	private Inmueble inmueble;
	
	@BeforeEach
	void setUp() throws Exception {
		propietario = new Propietario("Abril", "abru@gmail.com", "1111111");
		inmueble = new Inmueble ("Quinta", (double) 123, "Argentina", "Hudson", "Calle 163 123", LocalTime.of(14,00), LocalTime.of(10,00), propietario);
		
	}

	@Test
	void test() {
		assertEquals(inmueble.getPropietario(), propietario);	}

}
