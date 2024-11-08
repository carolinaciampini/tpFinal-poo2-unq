package estrategiaCancelacion;

import java.time.LocalDate;

import inmuebless.Inmuebless;
import reserva.Reserva;

public class SinCancelacion implements EstrategiaCancelacion {
	
	public Double calcularPenalizacion(LocalDate hoy, Reserva reserva, Inmuebless posteo) {
		return posteo.getPrecioParaReserva(reserva);
	}
}
