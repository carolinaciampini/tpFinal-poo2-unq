package Sitio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Sitio.SitioWeb;
import categoria.Categoria;
import excepciones.PropietarioNoRegistradoExcepcion;
import excepciones.UsuarioYaExistenteException;
import filtros.Criterio;
import filtros.Filtro;
import filtros.FiltroPrecio;
import inmueble.Inmueble;
import reserva.Reserva;
import servicio.Servicio;
import tipoInmueble.TipoInmueble;
import usuarios.Inquilino;
import usuarios.Propietario;
import usuarios.Usuario;

class SitioWebTest {
	private SitioWeb sitio;
	private Usuario usuario1;
	private Usuario usuarioI;
	private Usuario usuarioI2;
	private Inmueble inmueble;
	private Filtro filterManager;
	private Inmueble inmueble2;
	private Inmueble inmueble3;
	private Inmueble inmueble4;
	private Reserva reserva1;
	private Reserva reserva2;
	private Reserva reserva4;
	private Reserva reserva3;
	private TipoInmueble tipo1;
	private TipoInmueble tipo2;
	private Servicio agua;
	private Servicio gas;
	private Categoria categoriaU;
	private Categoria categoriaI;

	

	@BeforeEach
	void setUp() throws Exception {
		sitio = new SitioWeb();
		
		usuario1 = mock(Usuario.class);
		 when(usuario1.getEmail()).thenReturn("abru@gmail.com");
		 inmueble = mock(Inmueble.class);
		 when(inmueble.getPropietario()).thenReturn(usuario1); 
		 
		usuarioI = mock(Usuario.class);
		when(usuarioI.getEmail()).thenReturn("caaro@gmail.com");
		
		usuarioI2 = mock(Usuario.class);
		when(usuarioI2.getEmail()).thenReturn("gua@gmail.com");

	     
		reserva1 = mock(Reserva.class);
        reserva2 = mock(Reserva.class);
        reserva3 = mock(Reserva.class);
        when(reserva1.getInquilino()).thenReturn(usuario1);
        when(reserva2.getInquilino()).thenReturn(usuarioI);
        when(reserva3.getInquilino()).thenReturn(usuario1);


        // Mocks de inmuebles con sus respectivas reservas
        inmueble2 = mock(Inmueble.class);
        
        inmueble3 = mock(Inmueble.class);

        inmueble4 = mock(Inmueble.class);
        
        when(inmueble2.getPropietario()).thenReturn(usuario1);
        when(inmueble3.getPropietario()).thenReturn(usuario1);
        when(inmueble4.getPropietario()).thenReturn(usuario1);
        
        tipo1 = mock(TipoInmueble.class);
        tipo2 = mock(TipoInmueble.class);
        
        agua = mock(Servicio.class);
        gas = mock(Servicio.class);
        
        categoriaU = mock(Categoria.class);
        categoriaI = mock(Categoria.class);
      
        
	}
	
	
	
	@Test
	 void testInmueblesAlquiladosPorUsuario() throws UsuarioYaExistenteException, PropietarioNoRegistradoExcepcion {
		reserva4 = mock(Reserva.class);
		when(reserva4.getInquilino()).thenReturn(usuarioI);
		
		sitio.addUsuario(usuario1);
	    sitio.darDeAltaInmueble(inmueble);
	    sitio.darDeAltaInmueble(inmueble2);


	    when(reserva1.getInmueble()).thenReturn(inmueble);
	    when(reserva4.getInmueble()).thenReturn(inmueble2);

	    when(inmueble.getReservas()).thenReturn(List.of(reserva1));  // este no es alquilado por el usuarioI
	    when(inmueble2.getReservas()).thenReturn(List.of(reserva4)); // este si

	    List<Inmueble> esperado = Arrays.asList( inmueble2);
	    assertEquals(esperado, sitio.inmueblesAlquiladosPor(usuarioI));
	 }
	 
	
	 
	 @Test
	 void testVecesQueAlquiloUnInmueble() throws UsuarioYaExistenteException, PropietarioNoRegistradoExcepcion {
		 	reserva4 = mock(Reserva.class);
			when(reserva4.getInquilino()).thenReturn(usuario1);
			
		 	sitio.addUsuario(usuario1);
			sitio.darDeAltaInmueble(inmueble);
			sitio.darDeAltaInmueble(inmueble2);
			
	        
	        when(inmueble.getReservas()).thenReturn(List.of(reserva1));
	        when(inmueble2.getReservas()).thenReturn(List.of(reserva4));

	        assertEquals(2, sitio.vecesQueAlquilo(usuario1));
	 }
	 
	
	@Test
	void testVecesQueAlquiloEsteInmueble() throws UsuarioYaExistenteException, 	PropietarioNoRegistradoExcepcion {
		sitio.addUsuario(usuario1);
		sitio.darDeAltaInmueble(inmueble);
		sitio.darDeAltaInmueble(inmueble2);
		
      when(reserva1.esMismoInquilino(usuario1)).thenReturn(true);
      when(reserva2.esMismoInquilino(usuario1)).thenReturn(false);
      
      when(reserva1.getInmueble()).thenReturn(inmueble);
	  when(reserva2.getInmueble()).thenReturn(inmueble2);
        
      when(inmueble.getReservas()).thenReturn(List.of(reserva1));
      when(inmueble2.getReservas()).thenReturn(List.of(reserva2));

        
        assertEquals(1, sitio.vecesQueAlquiloInmueble(usuario1, inmueble));
       
	}
	
	 @Test
	 void testTiempoQueEsUsuario() throws UsuarioYaExistenteException {
	 	sitio.addUsuario(usuario1);
	 	LocalDate fecha1 = sitio.usuarioFechaRegistro(usuario1);
		LocalDate fecha2 = LocalDate.now();
		 int diasDeDiferencia = (int) ChronoUnit.DAYS.between(fecha1, fecha2);

		
		assertEquals(diasDeDiferencia, sitio.tiempoQueEsUsuario(usuario1));
	 
	 }
	 	  
	@Test 
	
	    public void testGetTodasLasReservasRealizadasDe() throws PropietarioNoRegistradoExcepcion, UsuarioYaExistenteException {
			sitio.addUsuario(usuario1);
			sitio.darDeAltaInmueble(inmueble);
			sitio.darDeAltaInmueble(inmueble2);
			
	        when(reserva1.esMismoInquilino(usuario1)).thenReturn(true);
	      when(reserva2.esMismoInquilino(usuario1)).thenReturn(true);
	        
	        when(inmueble.getReservas()).thenReturn(List.of(reserva1));
	        when(inmueble2.getReservas()).thenReturn(List.of(reserva2));

	       
	        List<Reserva> todasLasReservas = sitio.getTodasLasReservasRealizadasDe(usuario1);

	        
	        assertEquals(2, todasLasReservas.size());
	        assertTrue(todasLasReservas.contains(reserva1));
	        assertTrue(todasLasReservas.contains(reserva2));
	    }
	
	@Test 
	public void testGetReservasDelUsuarioEnCiudad() throws UsuarioYaExistenteException, PropietarioNoRegistradoExcepcion {
		sitio.addUsuario(usuario1);
		sitio.darDeAltaInmueble(inmueble);
		sitio.darDeAltaInmueble(inmueble2);
        when(inmueble.getCiudad()).thenReturn("Madrid");
        when(inmueble2.getCiudad()).thenReturn("Barcelona");

        when(reserva1.esMismoInquilino(usuario1)).thenReturn(true);
        when(reserva2.esMismoInquilino(usuario1)).thenReturn(true);

        when(inmueble.getReservas()).thenReturn(List.of(reserva1));
        when(inmueble2.getReservas()).thenReturn(List.of(reserva2));
 
        List<Reserva> reservasEnMadrid = sitio.getReservasDelUsuarioEnCiudad(usuario1, "Madrid");
        
        assertEquals(1, reservasEnMadrid.size());
        assertTrue(reservasEnMadrid.contains(reserva1));
        assertFalse(reservasEnMadrid.contains(reserva2));
       
    }

	
	@Test
	 public void testGetCiudadesDeReservasDel() throws UsuarioYaExistenteException, PropietarioNoRegistradoExcepcion {
		sitio.addUsuario(usuario1);
		sitio.darDeAltaInmueble(inmueble);
		sitio.darDeAltaInmueble(inmueble2);
		when(inmueble.getCiudad()).thenReturn("Madrid");
        when(inmueble2.getCiudad()).thenReturn("Barcelona");

        when(reserva1.esMismoInquilino(usuario1)).thenReturn(true);
        when(reserva2.esMismoInquilino(usuario1)).thenReturn(true);
        
        when(inmueble.getReservas()).thenReturn(List.of(reserva1));
        when(inmueble2.getReservas()).thenReturn(List.of(reserva2));
       
        List<String> ciudades = sitio.getCiudadesDeReservasDel(usuario1);

        assertEquals(2, ciudades.size());
        assertTrue(ciudades.contains("Madrid"));
        assertTrue(ciudades.contains("Barcelona"));
    }
	
	 @Test
	    public void testGetReservasFuturasDe() throws PropietarioNoRegistradoExcepcion, UsuarioYaExistenteException {
		 	sitio.addUsuario(usuario1);
			sitio.darDeAltaInmueble(inmueble);
			sitio.darDeAltaInmueble(inmueble2);
	      
	        when(reserva1.esMismoInquilino(usuario1)).thenReturn(true);
	        when(reserva1.getFechaEntrada()).thenReturn(LocalDate.now().plusDays(5));
	        
	        when(reserva2.esMismoInquilino(usuario1)).thenReturn(true);
	        when(reserva2.getFechaEntrada()).thenReturn(LocalDate.now().minusDays(5));

	        when(inmueble.getReservas()).thenReturn(List.of(reserva1));
	        when(inmueble2.getReservas()).thenReturn(List.of(reserva2));

	        
	        List<Reserva> reservasFuturas = sitio.getReservasFuturasDe(usuario1);

	        
	        assertEquals(1, reservasFuturas.size());
	        assertTrue(reservasFuturas.contains(reserva1));
	    }
	
	@Test 
	void testTopTenInquilinos() throws PropietarioNoRegistradoExcepcion, UsuarioYaExistenteException {
		when(inmueble3.getReservas()).thenReturn(List.of(reserva1, reserva2));
		when(inmueble3.getPropietario()).thenReturn(usuario1); 
		
		when(inmueble2.getPropietario()).thenReturn(usuario1); 
		when(inmueble2.getReservas()).thenReturn(List.of(reserva3));
		
		sitio.addUsuario(usuario1);
		sitio.darDeAltaInmueble(inmueble3);
		sitio.darDeAltaInmueble(inmueble2);
		List<Usuario> topInquilinos = sitio.topTenInquilinos();
		
		assertEquals(2, topInquilinos.size());
        assertEquals(usuario1, topInquilinos.get(0));
        assertEquals(usuarioI, topInquilinos.get(1));
		
	}
	
	
	@Test
	void testGetUsuariosCategorias() {
		sitio.agregarCategoriaUsuario(categoriaU);
		
		List<Categoria> categoriasEsperadas = Arrays.asList(categoriaU);
        assertEquals(categoriasEsperadas, sitio.getUsuariosCategoria());
	}
 	
	@Test
	void testGetReservasDe() throws PropietarioNoRegistradoExcepcion, UsuarioYaExistenteException {
		when(reserva1.getInquilino()).thenReturn(usuarioI);
	    when(reserva2.getInquilino()).thenReturn(usuarioI);
	    when(reserva3.getInquilino()).thenReturn(usuarioI2);
	    
	    
	    when(inmueble2.getReservas()).thenReturn(Arrays.asList(reserva1, reserva2));
        when(inmueble4.getReservas()).thenReturn(Arrays.asList(reserva3));
        when(inmueble2.getPropietario()).thenReturn(usuario1); 
        when(inmueble4.getPropietario()).thenReturn(usuario1); 
        when(inmueble3.getPropietario()).thenReturn(usuario1); 
        
     
        sitio.addUsuario(usuario1);
        sitio.darDeAltaInmueble(inmueble2);
        sitio.darDeAltaInmueble(inmueble3);
        sitio.darDeAltaInmueble(inmueble4);
        
        assertEquals(2, sitio.getReservasDe(inmueble2).size());
	}
	
	@Test
	void testTasaOcupacion() throws PropietarioNoRegistradoExcepcion, UsuarioYaExistenteException {
		when(inmueble2.getPropietario()).thenReturn(usuario1); 
		when(inmueble2.getPropietario()).thenReturn(usuario1); 
		when(inmueble2.getPropietario()).thenReturn(usuario1); 

		when(reserva3.getInquilino()).thenReturn(usuarioI2);
        
		when(inmueble4.getPropietario()).thenReturn(usuario1); 
        when(inmueble4.getPropietario()).thenReturn(usuario1); 
       
	    
        when(inmueble4.getReservas()).thenReturn(Arrays.asList(reserva3));
        
    
        sitio.addUsuario(usuario1);
        sitio.addUsuario(usuarioI2);
        sitio.darDeAltaInmueble(inmueble2);
        sitio.darDeAltaInmueble(inmueble4);
        
        assertEquals(1, sitio.cantidadDeInmueblesAlquilados());
        assertEquals(2, sitio.getInmuebles().size());
        assertEquals(0.5, sitio.tasaDeOcupacion());
	}
	
	@Test
	void testCantidadInmueblesAlquilados() throws PropietarioNoRegistradoExcepcion, UsuarioYaExistenteException {
	    when(reserva2.getInquilino()).thenReturn(usuarioI);
	    when(reserva3.getInquilino()).thenReturn(usuarioI2);
	    
        when(inmueble3.getReservas()).thenReturn(Arrays.asList(reserva2));
        when(inmueble4.getReservas()).thenReturn(Arrays.asList(reserva3));
        
      
        
        sitio.addUsuario(usuario1);
        sitio.addUsuario(usuarioI2);
        sitio.addUsuario(usuarioI);
        
        sitio.darDeAltaInmueble(inmueble2);
        sitio.darDeAltaInmueble(inmueble3);
        sitio.darDeAltaInmueble(inmueble4);
        
	
        assertEquals(2, sitio.cantidadDeInmueblesAlquilados());
	}
	@Test
	void testSacarCategoriaInmueble () {
		sitio.agregarCategoriaInmueble(categoriaI);
		sitio.sacarCategoriaInmueble(categoriaI);
		
		assertEquals(0, sitio.getInmueblesCategoria().size());
	}
	
	@Test
	void testAgregarCategoriaInmueble() {
		sitio.agregarCategoriaInmueble(categoriaI);
		
		assertEquals(1, sitio.getInmueblesCategoria().size());
	} 
	
	@Test
	void testSacarCategoriaUsuario () {
		sitio.agregarCategoriaUsuario(categoriaU);
		sitio.sacarCategoriaUsuario(categoriaU);
		
		assertEquals(0, sitio.getUsuariosCategoria().size());
	}
	
	@Test
	void testAgregarCategoriaUsuario () {
		sitio.agregarCategoriaUsuario(categoriaU);
		
		assertEquals(1, sitio.getUsuariosCategoria().size());
	}
	
	@Test
	void testSacarServicios () {
		sitio.agregarServicio(agua);
		sitio.sacarServicio(agua);
		
		assertEquals(0, sitio.getServicios().size());
	}
	
	@Test
	void testAgregarServicio () {
		sitio.agregarServicio(agua);
		sitio.agregarServicio(gas);
		
		assertEquals(2, sitio.getServicios().size());
	}
	
	@Test
	void testSacarTipos () {
		sitio.agregarTipoDeInmueble(tipo1);
		sitio.sacarTipoDeInmueble(tipo1);
		
		assertEquals(0, sitio.getTiposInmuebles().size());
	}
	
	@Test
	void testAgregarTipos () {
		sitio.agregarTipoDeInmueble(tipo1);
		sitio.agregarTipoDeInmueble(tipo2);
		
		assertEquals(2, sitio.getTiposInmuebles().size());
	}
	
	@Test
	void testInmueblesLibres() throws PropietarioNoRegistradoExcepcion, UsuarioYaExistenteException {
		
	    sitio.addUsuario(usuario1);
		sitio.darDeAltaInmueble(inmueble2);
        sitio.darDeAltaInmueble(inmueble3);
        sitio.darDeAltaInmueble(inmueble4);

        when(inmueble2.getReservas()).thenReturn(List.of());
        when(inmueble3.getReservas()).thenReturn(List.of());
        when(inmueble4.getReservas()).thenReturn(Arrays.asList(reserva3));
        
		List<Inmueble> inmueblesEsperados = Arrays.asList(inmueble2, inmueble3);
	        assertEquals(inmueblesEsperados, sitio.inmueblesLibres() );

	}
	
	@Test
	void testReservasDeUnUsuario() throws UsuarioYaExistenteException, PropietarioNoRegistradoExcepcion {
		when(reserva1.getInquilino()).thenReturn(usuarioI);
	    when(reserva2.getInquilino()).thenReturn(usuarioI);
	    when(reserva3.getInquilino()).thenReturn(usuarioI2);
	    
	    when(inmueble2.getReservas()).thenReturn(Arrays.asList(reserva1));
        when(inmueble3.getReservas()).thenReturn(Arrays.asList(reserva2));
        when(inmueble4.getReservas()).thenReturn(Arrays.asList(reserva3));
       
	     
	    sitio.addUsuario(usuario1);
		sitio.darDeAltaInmueble(inmueble2);
		sitio.darDeAltaInmueble(inmueble3);
		sitio.darDeAltaInmueble(inmueble4);
        
		List<Reserva> reservasEsperadas = Arrays.asList(reserva1, reserva2);
        assertEquals(reservasEsperadas, sitio.obtenerReservasDeUsuario(usuarioI));
        assertEquals(2, sitio.obtenerCantidadReservasDeUsuario(usuarioI));
	}
	
	@Test
	void testReservasDeUnUsuarioNoExistiendoReservas() throws UsuarioYaExistenteException, PropietarioNoRegistradoExcepcion {
        
	     
	    sitio.addUsuario(usuario1);
		sitio.darDeAltaInmueble(inmueble2);
		sitio.darDeAltaInmueble(inmueble3);
		sitio.darDeAltaInmueble(inmueble4);
        
		List<Reserva> reservasEsperadas = Arrays.asList();
        assertEquals(reservasEsperadas, sitio.obtenerReservasDeUsuario(usuarioI));
        assertEquals(0, sitio.obtenerCantidadReservasDeUsuario(usuarioI));
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
	void testFiltrosObligatorios() throws UsuarioYaExistenteException, PropietarioNoRegistradoExcepcion{
	     
	    sitio.addUsuario(usuario1);
		sitio.darDeAltaInmueble(inmueble2);
		sitio.darDeAltaInmueble(inmueble3);
		sitio.darDeAltaInmueble(inmueble4);
		
		filterManager = mock(Filtro.class);
		sitio.filtrarInmuebles(filterManager);
		
		verify(filterManager).filtrar(sitio.getInmuebles());
	}
	
}

