package estrategiaCancelacion;

import java.time.LocalDate;

import inmueble.Inmueble;
import reserva.Reserva;

public interface EstrategiaCancelacion {
	public Double calcularPenalizacion(LocalDate hoy, Reserva reserva, Inmueble posteo);
}
