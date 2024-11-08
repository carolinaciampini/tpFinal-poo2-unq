package estrategiaCancelacion;

import java.time.LocalDate;

import inmuebless.InmueblessREEMPLAZO;
import reserva.Reserva;

public class SinCancelacion implements EstrategiaCancelacion {
	
	public Double calcularPenalizacion(LocalDate hoy, Reserva reserva, InmueblessREEMPLAZO posteo) {
		return posteo.getPrecioParaReserva(reserva);
	}
}
