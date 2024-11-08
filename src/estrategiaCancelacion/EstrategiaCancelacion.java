package estrategiaCancelacion;

import java.time.LocalDate;

import inmuebless.InmueblessREEMPLAZO;
import reserva.Reserva;

public interface EstrategiaCancelacion {
	public Double calcularPenalizacion(LocalDate hoy, Reserva reserva, InmueblessREEMPLAZO posteo);
}
