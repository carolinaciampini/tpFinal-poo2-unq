package Sitio;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Sitio.SitioWeb;
import excepciones.UsuarioYaExistenteException;
import inmueble.Inmueble;
import usuarios.Propietario;
import usuarios.Usuario;

class SitioWebTest {
	private SitioWeb sitio;
	private Propietario usuario1;
	private Inmueble inmueble;
	

	@BeforeEach
	void setUp() throws Exception {
		sitio = new SitioWeb();
		usuario1 = new Propietario("Abril", "abru@gmail.com", "1111111");
		inmueble = new Inmueble ("Quinta", (double) 123, "Argentina", "Hudson", "Calle 163 123", LocalTime.of(14,00), LocalTime.of(10,00), usuario1, (double) 90000);
	}

	@Test
	void testAddUsuarios() throws UsuarioYaExistenteException {
		sitio.addUsuario(usuario1);
		assertEquals(sitio.getUsuarios().size(), 1);
	}
	
	@Test
	void testAddUsuarioExistenteLanzaExcepcion() throws UsuarioYaExistenteException {
	    sitio.addUsuario(usuario1);
	    
	    assertThrows(UsuarioYaExistenteException.class, () -> sitio.addUsuario(usuario1));
	   
	}
	
	@Test
	void testRemoveUsuarios() throws UsuarioYaExistenteException {
		sitio.addUsuario(usuario1);
		sitio.removeUsuario(usuario1);
		assertEquals(sitio.getUsuarios().size(), 0);
	}
	
	@Test
	void testAltaDeInmuebleFallido() {
		assertEquals(sitio.addInmueble(inmueble), false);
	}
	
	@Test
	void testAltaDeInmuebleExitoso() throws UsuarioYaExistenteException {
		sitio.addUsuario(usuario1);
		assertEquals(sitio.addInmueble(inmueble), true);
	}
	
	@Test
	void testRemoveInmueble() throws UsuarioYaExistenteException {
		sitio.addUsuario(usuario1);
		sitio.addInmueble(inmueble);
		sitio.removeInmueble(inmueble);
		assertEquals(sitio.getInmuebles().size(), 0);
	}
	

}
