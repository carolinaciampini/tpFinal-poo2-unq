package posteo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.FormaDePago;
import enums.Servicio;
import inmueble.Inmueble;
import usuarios.Propietario;

class posteoTest {
	private Propietario propietario;
	private Inmueble inmueble;
	private Servicio servicio;
	private FormaDePago formaDePago;
	private Posteo posteo;
	private PeriodoManager periodo;

	@BeforeEach
	void setUp() throws Exception {
		propietario = new Propietario("Abril", "abru@gmail.com", "1111111");
		inmueble = new Inmueble ("Quinta", (double) 123, "Argentina", "Hudson", "Calle 163 123", LocalTime.of(14,00), LocalTime.of(10,00), propietario, (double) 90000);
		servicio = Servicio.AGUA;
		formaDePago = FormaDePago.EFECTIVO;
		posteo = new Posteo(inmueble, 9000, periodo);
	}

	@Test
	void testGetInmueble() {
		assertEquals(posteo.getInmueble(), inmueble);
	}
	
	@Test
	void testCrearReserva() {
		posteo.crearReserva(inmueble, propietario, LocalDate.of(2024, 12, 10), LocalDate.of(2024, 12, 15), formaDePago);
		assertEquals(posteo.getReservas().size(), 1);
	}

}
