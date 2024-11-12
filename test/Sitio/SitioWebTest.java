package Sitio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Sitio.SitioWeb;
import excepciones.PropietarioNoRegistradoExcepcion;
import excepciones.UsuarioYaExistenteException;
import filtros.Criterio;
import filtros.Filtro;
import filtros.FiltroPrecio;
import inmueble.Inmueble;
import reserva.Reserva;
import usuarios.Inquilino;
import usuarios.Propietario;
import usuarios.Usuario;

class SitioWebTest {
	private SitioWeb sitio;
	private Propietario usuario1;
	private Inquilino usuarioI;
	private Inquilino usuarioI2;
	private Inmueble inmueble;
	private Filtro filterManager;
	private Inmueble inmueble2;
	private Inmueble inmueble3;
	private Inmueble inmueble4;
	private Reserva reserva1;
	private Reserva reserva2;
	private Reserva reserva3;

	

	@BeforeEach
	void setUp() throws Exception {
		sitio = new SitioWeb();
		inmueble = mock(Inmueble.class);
		usuario1 = mock(Propietario.class);
		 when(usuario1.getEmail()).thenReturn("abru@gmail.com");
		 when(inmueble.getPropietario()).thenReturn(usuario1); 
		usuarioI = mock(Inquilino.class);
		usuarioI2 = mock(Inquilino.class);

	     
		reserva1 = mock(Reserva.class);
        reserva2 = mock(Reserva.class);
        reserva3 = mock(Reserva.class);


        // Mocks de inmuebles con sus respectivas reservas
        inmueble2 = mock(Inmueble.class);
        inmueble3 = mock(Inmueble.class);
        inmueble4 = mock(Inmueble.class);


        
	}
	
	@Test
	void testInmueblesLibres() {
		when(inmueble2.getReservas()).thenReturn(List.of());
		when(inmueble3.getReservas()).thenReturn(List.of());
		when(inmueble4.getReservas()).thenReturn(Arrays.asList(reserva3));

		sitio.agregarInmueble(inmueble2);
		sitio.agregarInmueble(inmueble3);
		sitio.agregarInmueble(inmueble4);

		List<Inmueble> inmueblesEsperados = Arrays.asList(inmueble2, inmueble3);
	        assertEquals(inmueblesEsperados, sitio.inmueblesLibres() );

	}
	
	@Test
	void testReservasDeUnUsuario() {
		when(reserva1.getInquilino()).thenReturn(usuarioI);
	    when(reserva2.getInquilino()).thenReturn(usuarioI);
	    when(reserva3.getInquilino()).thenReturn(usuarioI2);
	    
	    when(inmueble2.getReservas()).thenReturn(Arrays.asList(reserva1));
        when(inmueble3.getReservas()).thenReturn(Arrays.asList(reserva2));
        when(inmueble4.getReservas()).thenReturn(Arrays.asList(reserva3));
        
     // Agrega los inmuebles al sitio
        sitio.agregarInmueble(inmueble2);
        sitio.agregarInmueble(inmueble3);
        sitio.agregarInmueble(inmueble4);
        
		List<Reserva> reservasEsperadas = Arrays.asList(reserva1, reserva2);
        assertEquals(reservasEsperadas, sitio.obtenerReservasDeUsuario(usuarioI));
        assertEquals(2, sitio.obtenerCantidadReservasDeUsuario(usuarioI));
	}
	
	
	@Test
	void testAltaDeInmuebleFallido() throws PropietarioNoRegistradoExcepcion {
		assertThrows(PropietarioNoRegistradoExcepcion.class, () ->  sitio.darDeAltaInmueble(inmueble));
	}

	@Test
	void testAddGetUsuarios() throws UsuarioYaExistenteException {
		sitio.addUsuario(usuario1);
		assertEquals(sitio.getUsuarios().size(), 1);
	}
	
	@Test
	void testUsuarioFechaRegistro () throws UsuarioYaExistenteException {
		sitio.addUsuario(usuario1);
		LocalDate fechaRegistro = sitio.usuarioFechaRegistro(usuario1);

	    assertEquals(LocalDate.now(), fechaRegistro);		
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
	void testFiltrosObligatorios(){
		sitio.agregarInmueble(inmueble2);
		sitio.agregarInmueble(inmueble3);
		sitio.agregarInmueble(inmueble4);
		filterManager = mock(Filtro.class);
		sitio.filtrarInmuebles(filterManager);
		
		verify(filterManager).filtrar(sitio.getInmuebles());
	}
	
}

