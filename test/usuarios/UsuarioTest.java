package usuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.NoHayPuntajesSobreEsteUsuario;
import ranking.Ranking;

public class UsuarioTest {
	private Propietario prop1;
	private Propietario prop2;
	private Inquilino inq2;
	private Inquilino inq1;
	
	private Ranking ranking1;
	private Ranking ranking2;
	private Ranking ranking3;
	
	private Ranking ranking4;
	private Ranking ranking5;
	private Ranking ranking6;

	@BeforeEach
	void setUp() {
		prop1 = new Propietario("carolina", "caro@gmail.com", "1111111");
		inq1 = new Inquilino("abril", "abril@gmail.com", "1111111");
		
		ranking1 = mock(Ranking.class);
		ranking2 = mock(Ranking.class);
		ranking3 = mock(Ranking.class);
		
		ranking4 = mock(Ranking.class);
		ranking5 = mock(Ranking.class);
		ranking6 = mock(Ranking.class);
		
		when(ranking1.getPuntaje()).thenReturn(4);
		when(ranking2.getPuntaje()).thenReturn(3);
		when(ranking3.getPuntaje()).thenReturn(3);
		
		when(ranking4.getPuntaje()).thenReturn(4);
		when(ranking5.getPuntaje()).thenReturn(3);
		when(ranking6.getPuntaje()).thenReturn(3);
		
	}

	@Test
	void getNombreTest() {
		assertEquals("carolina", prop1.getNombre());
		assertEquals("abril", inq1.getNombre());
		assertEquals("caro@gmail.com",prop1.getEmail());
		assertEquals("abril@gmail.com",inq1.getEmail());
		assertEquals("1111111",prop1.getTelefono());
		assertEquals("1111111",inq1.getTelefono());
	}
	
	
	@Test 
	void getRankingsComoPropietario(){
		prop1.recibirRankingComoPropietario(ranking1);
		prop1.recibirRankingComoPropietario(ranking2);
		prop1.recibirRankingComoPropietario(ranking3);
		
		List<Ranking> expectedRankings = Arrays.asList(ranking1, ranking2, ranking3);
		
		assertEquals(expectedRankings, prop1.getRankingsComoPropietario());
	}
	
	
	@Test 
	void getRankingsComoInquilino(){
		inq1.recibirRankingComoInquilino(ranking4);
		inq1.recibirRankingComoInquilino(ranking5);
		inq1.recibirRankingComoInquilino(ranking6);
		
		List<Ranking> expectedRankings = Arrays.asList(ranking4, ranking5, ranking6);
		
		assertEquals(expectedRankings, inq1.getRankingsComoInquilino());
	}
	
	@Test 
	void testPuntajePromedioComoInquilino() throws NoHayPuntajesSobreEsteUsuario {
		inq1.recibirRankingComoInquilino(ranking4);
		inq1.recibirRankingComoInquilino(ranking5);
		inq1.recibirRankingComoInquilino(ranking6);
		
		Integer promedio = 3;
		
		assertEquals(promedio, inq1.puntajePromedioComoInquilino());
	}
	
	@Test 
	void testPuntajePromedioComoPropietario() throws NoHayPuntajesSobreEsteUsuario {
		prop1.recibirRankingComoPropietario(ranking1);
		prop1.recibirRankingComoPropietario(ranking2);
		prop1.recibirRankingComoPropietario(ranking3);
		
		Integer promedio = 3;
		
		assertEquals(promedio, prop1.puntajePromedioComoPropietario());
		
	}
}