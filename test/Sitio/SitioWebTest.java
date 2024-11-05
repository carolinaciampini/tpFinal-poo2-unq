package Sitio;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Sitio.SitioWeb;
import excepciones.PropietarioNoRegistradoExcepcion;
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
	void testAltaDeInmuebleFallido() throws PropietarioNoRegistradoExcepcion {
		
		assertThrows(PropietarioNoRegistradoExcepcion.class, () ->  sitio.darDeAltaInmueble(inmueble));
	}
	
	@Test
	void testAltaDeInmuebleExitoso() throws UsuarioYaExistenteException, PropietarioNoRegistradoExcepcion {
		sitio.addUsuario(usuario1);
		sitio.darDeAltaInmueble(inmueble);
		assertTrue(sitio.tienePosteo(inmueble));
	}
	
	@Test
	void testRemoveInmueble() throws UsuarioYaExistenteException, PropietarioNoRegistradoExcepcion {
		sitio.addUsuario(usuario1);
		sitio.darDeAltaInmueble(inmueble);
		sitio.removePosteo(inmueble);
		assertEquals(sitio.getPosteos().size(), 0);
	}
	

}
