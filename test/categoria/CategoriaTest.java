package categoria;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class CategoriaTest {
		private Categoria tipo;
		
		@BeforeEach
		void setUp() {
			tipo = new Categoria("Limpieza");
		}
		
		@Test
		void testGetNombre() {
			assertEquals("Limpieza", tipo.getNombre());
		}
	}

