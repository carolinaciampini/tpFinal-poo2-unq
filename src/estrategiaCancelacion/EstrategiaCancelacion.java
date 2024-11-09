package estrategiaCancelacion;

import java.time.LocalDate;

import inmuebless.Inmueble;
import reserva.Reserva;

public interface EstrategiaCancelacion {
	public Double calcularPenalizacion(LocalDate hoy, Reserva reserva, Inmueble posteo);
}
