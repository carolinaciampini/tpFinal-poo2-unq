package reserva;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.FormaDePago;
import enums.Servicio;
import inmueble.Inmueble;
import posteo.Posteo;
import usuarios.Propietario;
import usuarios.Usuario;

class ReservaTest {
		private Posteo posteo;
		private Inmueble inmueble;
		private Usuario inquilino;
		private Propietario propietario;
		private LocalDate fechaEntrada;
		private LocalDate fechaSalida;
		private FormaDePago formaDePago;
		private Reserva reserva;
		private Servicio servicio;
		
	@BeforeEach
	void setUp() throws Exception {
		propietario = new Propietario("Abril", "abru@gmail.com", "1111111");
		inmueble = new Inmueble ("Quinta", (double) 123, "Argentina", "Hudson", "Calle 163 123", LocalTime.of(14,00), LocalTime.of(10,00), propietario, (double) 90000);
		servicio = Servicio.AGUA;
		formaDePago = FormaDePago.EFECTIVO;
		posteo = new Posteo(inmueble);
		reserva = new Reserva(posteo, inmueble, inquilino, fechaEntrada, fechaEntrada, formaDePago);
		
	}

	@Test
	void testGetPosteo() {
		assertEquals(reserva.getPosteo(), posteo);
	}
	
	@Test
	void testGetInmueble() {
		assertEquals(reserva.getInmueble(), inmueble);
	}
	
	@Test
	void testGetInquilino() {
		assertEquals(reserva.getInquilino(), inquilino);
	}
	
	@Test
	void testGetFechaEntrada() {
		assertEquals(reserva.getFechaEntrada(), fechaEntrada);
	}
	
	@Test
	void testGetFechaSalida() {
		assertEquals(reserva.getFechaSalida(), fechaSalida);
	}
	
	@Test
	void testGetFormaDePago() {
		assertEquals(reserva.getFormaDePago(), formaDePago);
	}



}
