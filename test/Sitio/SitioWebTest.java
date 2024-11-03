package Sitio;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Sitio.SitioWeb;
import inmueble.Inmueble;
import usuarios.Propietario;
import usuarios.Usuario;

class SitioWebTest {
	private SitioWeb sitio;
	private Usuario usuario1;
	private Propietario propietario;
	private Inmueble inmueble;
	

	@BeforeEach
	void setUp() throws Exception {
		sitio = new SitioWeb();
		usuario1 = new Propietario("Abril", "abru@gmail.com", "1111111");
		inmueble = new Inmueble ("Quinta", (double) 123, "Argentina", "Hudson", "Calle 163 123", LocalTime.of(14,00), LocalTime.of(10,00), propietario);
	}

	@Test
	void testCantidadUsuarios() {
		sitio.addUsuario(usuario1);
		assertEquals(sitio.getUsuarios().size(), 1);
	}
	
	@Test
	void testCantidadInmuebles() {
		sitio.addInmueble(inmueble);
		assertEquals(sitio.getInmuebles().size(), 1);
	}

}
