package ranking;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import categoria.Categoria;

class RankingTest {
	private Ranking ranking;
	private Categoria limpieza;

	@BeforeEach
	void setUp() throws Exception {
		limpieza = mock(Categoria.class);
		
		ranking = new Ranking(4, "Excelente servicio", limpieza);
		
		/*
		@BeforeEach
		void setUp() {
			tipo = new Categoria("Limpieza");
		}
		
		@Test
		void testGetNombre() {
			assertEquals("Limpieza", tipo.getNombre());
		}
	   }
		 */
		
	}

	@Test
	void testGetPuntaje() {
		assertEquals(4, ranking.getPuntaje());
	}

	@Test
	void testGetComentario() {
		assertEquals("Excelente servicio", ranking.getComentario());
	}

	@Test
	void testGetCategoria() {
		assertEquals(limpieza, ranking.getCategoria());
	}
}
