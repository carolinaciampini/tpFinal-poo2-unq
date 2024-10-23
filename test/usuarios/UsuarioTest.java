package usuarios;

import static org.junit.jupiter.api.Assertions.assertEquals;  

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioTest {
	private Propietario prop1;
	private Propietario prop2;
	private Inquilino inq2;
	private Inquilino inq1;

	@BeforeEach
	void setUp() {
		prop1 = new Propietario("carolina", "caro@gmail.com", "1111111");
		inq1 = new Inquilino("abril", "abril@gmail.com", "1111111");
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
}