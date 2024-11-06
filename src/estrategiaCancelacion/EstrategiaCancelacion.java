package estrategiaCancelacion;

import java.time.LocalDate;

import posteo.Posteo;
import reserva.Reserva;

public interface EstrategiaCancelacion {
	public Double calcularPenalizacion(LocalDate hoy, Reserva reserva, Posteo posteo);
}
