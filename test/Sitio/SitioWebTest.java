package Sitio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Sitio.SitioWeb;
import excepciones.PropietarioNoRegistradoExcepcion;
import excepciones.UsuarioYaExistenteException;
import filtros.Criterio;
import filtros.FilterManager;
import filtros.FiltroHuespedes;
import inmueble.Inmueble;
import posteo.Posteo;
import reserva.Reserva;
import usuarios.Propietario;
import usuarios.Usuario;

class SitioWebTest {
	private SitioWeb sitio;
	private Propietario usuario1;
	private Inmueble inmueble;
	private FilterManager filterManager;
	private Posteo posteo1;
	private Posteo posteo2;
	private Posteo posteo3;
	private Reserva reserva1;
	private Reserva reserva2;
	

	@BeforeEach
	void setUp() throws Exception {
		sitio = new SitioWeb();
		inmueble = mock(Inmueble.class);
		usuario1 = mock(Propietario.class);
		 when(usuario1.getEmail()).thenReturn("abru@gmail.com");
		 when(inmueble.getPropietario()).thenReturn(usuario1); 
		 
		 posteo1 = mock(Posteo.class);
		 when(posteo1.getInmueble()).thenReturn(inmueble);
	     posteo2 = mock(Posteo.class);
		 when(posteo2.getInmueble()).thenReturn(inmueble);
	     posteo3 = mock(Posteo.class);
		 when(posteo3.getInmueble()).thenReturn(inmueble);

	   
	     sitio.agregarPosteo(posteo1);
	     sitio.agregarPosteo(posteo2);
	     sitio.agregarPosteo(posteo3);
	     
	     reserva1 = mock(Reserva.class);
	     reserva2 = mock(Reserva.class);

	}
	
	
	@Test
	void testAltaDeInmuebleFallido() throws PropietarioNoRegistradoExcepcion {
		
		assertThrows(PropietarioNoRegistradoExcepcion.class, () ->  sitio.darDeAltaInmueble(inmueble));
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
	void testAltaDeInmuebleExitoso() throws UsuarioYaExistenteException, PropietarioNoRegistradoExcepcion {
		sitio.addUsuario(usuario1); // Añadir propietario antes de alta inmueble		
        sitio.darDeAltaInmueble(inmueble);
        assertTrue(sitio.tienePosteo(inmueble));
	}
	
	@Test
	void testRemoveInmueble() throws UsuarioYaExistenteException, PropietarioNoRegistradoExcepcion {
		sitio.addUsuario(usuario1);
        sitio.darDeAltaInmueble(inmueble);
        sitio.removePosteo(inmueble);
        assertEquals(0, sitio.getPosteos().size());
	};
	
	@Test 
	void testFiltrarPosteosConCiudadYFechas() {
		 LocalDate fechaEntrada = LocalDate.of(2024, 11, 10);
	     LocalDate fechaSalida = LocalDate.of(2024, 11, 15);
	     String ciudad = "Buenos Aires";
	        
	     filterManager = new FilterManager(fechaEntrada, fechaSalida, ciudad);
		
        when(posteo1.getCiudad()).thenReturn("Buenos Aires");
        when(posteo1.estaDisponible(fechaEntrada,fechaSalida)).thenReturn(true); 

        when(posteo2.getCiudad()).thenReturn("Buenos Aires");
        when(posteo2.estaDisponible(fechaEntrada, fechaSalida)).thenReturn(false); 

        when(posteo3.getCiudad()).thenReturn("Cordoba");
        when(posteo3.estaDisponible(fechaEntrada, fechaSalida)).thenReturn(true); 

       
        List<Posteo> resultados = sitio.filtrarPosteos(filterManager);

       
        assertEquals(1, resultados.size());  
        assertTrue(resultados.contains(posteo1));  
        assertFalse(resultados.contains(posteo2)); 
        assertFalse(resultados.contains(posteo3)); 
      
	}
	
<<<<<<< Updated upstream
	/*@Test
	void testGetReservas() throws UsuarioYaExistenteException, PropietarioNoRegistradoExcepcion {
		sitio.addUsuario(usuario1);
        sitio.darDeAltaInmueble(inmueble);
        assertEquals(1, sitio.getReservasDe(posteo1).size());
	}*/
	
=======
	@Test 
	void testFiltrarPosteosConCiudadFechasYHuespedes() {
		 LocalDate fechaEntrada = LocalDate.of(2024, 11, 10);
	     LocalDate fechaSalida = LocalDate.of(2024, 11, 15);
	     String ciudad = "Buenos Aires";
	        
	     filterManager = new FilterManager(fechaEntrada, fechaSalida, ciudad);
	     FiltroHuespedes f = new FiltroHuespedes(5);
	     filterManager.agregarFiltro(f);
		
        when(posteo1.getCiudad()).thenReturn("Buenos Aires");
        when(posteo1.estaDisponible(fechaEntrada,fechaSalida)).thenReturn(true); 
        when(posteo1.getHuespedes()).thenReturn(5);

        when(posteo2.getCiudad()).thenReturn("Buenos Aires");
        when(posteo2.estaDisponible(fechaEntrada, fechaSalida)).thenReturn(false); 
        when(posteo1.getHuespedes()).thenReturn(3);

        when(posteo3.getCiudad()).thenReturn("Cordoba");
        when(posteo3.estaDisponible(fechaEntrada, fechaSalida)).thenReturn(true); 
        when(posteo1.getHuespedes()).thenReturn(2);

       
        List<Posteo> resultados = sitio.filtrarPosteos(filterManager);

       
        assertEquals(1, resultados.size());  
        assertTrue(resultados.contains(posteo1));  
        assertFalse(resultados.contains(posteo2)); 
        assertFalse(resultados.contains(posteo3)); 
      
	}
>>>>>>> Stashed changes

}
