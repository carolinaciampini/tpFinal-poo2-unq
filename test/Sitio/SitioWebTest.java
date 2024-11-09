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
import filtros.Filtro;
import filtros.FiltroHuespedes;
import filtros.FiltroPrecio;
import inmueble.Inmueble;
import reserva.Reserva;
import usuarios.Propietario;
import usuarios.Usuario;

class SitioWebTest {
	private SitioWeb sitio;
	private Propietario usuario1;
	private Inmueble inmueble;
	private Filtro filterManager;
	private Inmueble inmueble2;
	private Inmueble inmueble3;
	private Inmueble inmueble4;
	private Reserva reserva1;
	private Reserva reserva2;
	

	@BeforeEach
	void setUp() throws Exception {
		sitio = new SitioWeb();
		inmueble = mock(Inmueble.class);
		usuario1 = mock(Propietario.class);
		 when(usuario1.getEmail()).thenReturn("abru@gmail.com");
		 when(inmueble.getPropietario()).thenReturn(usuario1); 
		 
		 inmueble2 = mock(Inmueble.class);
	     inmueble3 = mock(Inmueble.class);
	     inmueble4 = mock(Inmueble.class);
	     
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
		sitio.addUsuario(usuario1); 	
        sitio.darDeAltaInmueble(inmueble);
        assertEquals(1, sitio.getInmuebles().size());	
        }
	
	
	@Test 
	void testFiltrarInmueblesConCiudadYFechas() {
		  sitio.agregarInmueble(inmueble2);
		  sitio.agregarInmueble(inmueble3);
		  sitio.agregarInmueble(inmueble4);
		  
		 LocalDate fechaEntrada = LocalDate.of(2024, 11, 10);
	     LocalDate fechaSalida = LocalDate.of(2024, 11, 15);
	     String ciudad = "Buenos Aires";
	        
	     filterManager = new Filtro(fechaEntrada, fechaSalida, ciudad);
		
        when(inmueble2.getCiudad()).thenReturn("Buenos Aires");
        when(inmueble2.estaDisponible(fechaEntrada,fechaSalida)).thenReturn(true); 

        when(inmueble3.getCiudad()).thenReturn("Buenos Aires");
        when(inmueble3.estaDisponible(fechaEntrada, fechaSalida)).thenReturn(false); 

        when(inmueble4.getCiudad()).thenReturn("Cordoba");
        when(inmueble4.estaDisponible(fechaEntrada, fechaSalida)).thenReturn(true); 

        List<Inmueble> resultados = sitio.filtrarInmuebles(filterManager);
        assertEquals(1, resultados.size());  
        assertTrue(resultados.contains(inmueble2));  
        assertFalse(resultados.contains(inmueble3)); 
        assertFalse(resultados.contains(inmueble4)); 
      
	}
	

	@Test 
	void testFiltrarInmueblesConCiudadFechasYHuespedes() {
		  sitio.agregarInmueble(inmueble2);
		  sitio.agregarInmueble(inmueble3);
		  sitio.agregarInmueble(inmueble4);
		 LocalDate fechaEntrada = LocalDate.of(2024, 11, 10);
	     LocalDate fechaSalida = LocalDate.of(2024, 11, 15);
	     String ciudad = "Buenos Aires";
	        
	     filterManager = new Filtro(fechaEntrada, fechaSalida, ciudad);
	     FiltroHuespedes f = new FiltroHuespedes(5);
	     filterManager.agregarFiltro(f);
		
        when(inmueble2.getCiudad()).thenReturn("Buenos Aires");
        when(inmueble2.estaDisponible(fechaEntrada,fechaSalida)).thenReturn(true); 
        when(inmueble2.getHuespedes()).thenReturn(5);

        when(inmueble3.getCiudad()).thenReturn("Buenos Aires");
        when(inmueble3.estaDisponible(fechaEntrada, fechaSalida)).thenReturn(false); 
        when(inmueble3.getHuespedes()).thenReturn(3);

        when(inmueble4.getCiudad()).thenReturn("Cordoba");
        when(inmueble4.estaDisponible(fechaEntrada, fechaSalida)).thenReturn(true); 
        when(inmueble4.getHuespedes()).thenReturn(2);

        List<Inmueble> resultados = sitio.filtrarInmuebles(filterManager);
       
        assertEquals(1, resultados.size());  
        assertTrue(resultados.contains(inmueble2));  
        assertFalse(resultados.contains(inmueble3)); 
        assertFalse(resultados.contains(inmueble4)); 
      
	}
	
	@Test
	void testFiltrarInmueblesConCiudadFechasYPrecios() {
		sitio.agregarInmueble(inmueble2);
		  sitio.agregarInmueble(inmueble3);
		  sitio.agregarInmueble(inmueble4);
		  
		 LocalDate fechaEntrada = LocalDate.of(2024, 11, 10);
	     LocalDate fechaSalida = LocalDate.of(2024, 11, 15);
	     String ciudad = "Buenos Aires";
	        
	     filterManager = new Filtro(fechaEntrada, fechaSalida, ciudad);
	     FiltroPrecio f = new FiltroPrecio(1000.00, 1500.00);
	     filterManager.agregarFiltro(f);
		
       when(inmueble2.getCiudad()).thenReturn("Buenos Aires");
       when(inmueble2.estaDisponible(fechaEntrada,fechaSalida)).thenReturn(true); 
       when(inmueble2.precioSugeridoPara(fechaEntrada,fechaSalida)).thenReturn(1000.00);

       when(inmueble3.getCiudad()).thenReturn("Buenos Aires");
       when(inmueble3.estaDisponible(fechaEntrada, fechaSalida)).thenReturn(false); 
       when(inmueble3.precioSugeridoPara(fechaEntrada,fechaSalida)).thenReturn(1100.00);

       when(inmueble4.getCiudad()).thenReturn("Buenos Aires");
       when(inmueble4.estaDisponible(fechaEntrada, fechaSalida)).thenReturn(true); 
       when(inmueble4.precioSugeridoPara(fechaEntrada,fechaSalida)).thenReturn(1490.00);
    
       List<Inmueble> resultados = sitio.filtrarInmuebles(filterManager);
       
       assertEquals(2, resultados.size());  
       assertTrue(resultados.contains(inmueble2));  
       assertFalse(resultados.contains(inmueble3)); 
       //assertFalse(resultados.contains(posteo3)); 
     
	}

}
