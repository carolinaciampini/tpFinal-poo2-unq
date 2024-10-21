package prueba;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;

public class PruebaTest {
	   @Test
	    public void testAdd() {
	        // Crear un mock de SimpleCalculator
	        SimpleCalculator calculator = mock(SimpleCalculator.class);

	        // Definir el comportamiento del mock: cuando se llame a add con (2, 3), devolverá 5
	        when(calculator.add(2, 3)).thenReturn(5);

	        // Llamar al método add del mock
	        int result = calculator.add(2, 3);

	        // Verificar que el resultado es 5
	        assert result == 5;

	        // Verificar que el método add fue llamado con los parámetros (2, 3)
	        verify(calculator).add(2, 3);
	    }
}
