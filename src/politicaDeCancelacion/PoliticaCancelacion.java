package politicaDeCancelacion;

import java.time.LocalDate;
import java.time.LocalTime;

public interface PoliticaCancelacion {
	public void calcularPenalizacion(LocalDate fecha, LocalTime checkIn, Double precio);
}
