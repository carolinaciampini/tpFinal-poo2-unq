package estrategiaCancelacion;

import java.time.LocalDate;

import inmuebless.Inmuebless;
import reserva.Reserva;

public interface EstrategiaCancelacion {
	public Double calcularPenalizacion(LocalDate hoy, Reserva reserva, Inmuebless posteo);
}
