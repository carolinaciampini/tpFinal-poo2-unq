package Sitio;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import usuarios.Propietario;

class SitioWebTest {
	private SitioWeb sitio;
	private Usuario usuario1;

	@BeforeEach
	void setUp() throws Exception {
		sitio = new Sitio();
		usuario = new Propietario("Abril", "abru@gmail.com", "1111111");
		inmueble = new Inmueble ("Quinta", 123, "Argentina", "Hudson", "Calle 163 123", LocalTime.of(14,00), LocalTime.of(10,00), propietario);
	}

	@Test
	void testCantidadUsuarios() {
		sitio.addUsuario(usuario);
		assertEquals(sitio.getUsuarios().size(), 1);
	}
	
	@Test
	void testCantidadInmuebles() {
		sitio.addInmueble(inmueble);
		assertEquals(sitio.getInmuebles().size(), 1);
	}

}
