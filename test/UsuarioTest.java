package usuarios;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;


public class UsuarioTest {
	private Propietario prop1;
	private Propietario prop2;
	private Inquilino inq2;
	private Inquilino inq1;
	private Potencial pot1;
	
	@BeforeEach
	void setUp() {
		prop1 = new Propietario("carolina", "caro@gmail.com", "1111111");
		inq1 = new Inquilino("abril", "abril@gmail.com", "1111111");
		pot1 = new Potencial("guada", "guada@gmail.com", "1111111");
	}
	
	
	@Test
	void getNombreTest() {
		assertEquals(prop1.getNombre(),"carolina");
		assertEquals(inq1.getNombre(),"abril");
		assertEquals(pot1.getNombre(),"guada");
		
	}
	
}
