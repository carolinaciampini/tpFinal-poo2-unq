package filtros;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;

public class FilterManagerTest {
	private Filtro filtro;
	
	@BeforeEach
	void setUp() {
		LocalDate fechaEntrada = LocalDate.of(2024, 11, 10);
	    LocalDate fechaSalida = LocalDate.of(2024, 11, 15);
	    String ciudad = "Buenos Aires";
		filtro = new Filtro(fechaEntrada, fechaSalida, ciudad);
	}

	
}
