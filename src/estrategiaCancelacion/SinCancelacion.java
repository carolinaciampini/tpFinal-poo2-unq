package estrategiaCancelacion;

import java.time.LocalDate;

import posteo.Posteo;
import reserva.Reserva;

public class SinCancelacion implements EstrategiaCancelacion {
	
	public Double calcularPenalizacion(LocalDate hoy, Reserva reserva, Posteo posteo) {
		return posteo.getPrecioParaReserva(reserva);
	}
}
