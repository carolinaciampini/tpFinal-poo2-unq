package estrategiaCancelacion;

import java.time.LocalDate;

import inmueble.Inmueble;
import reserva.Reserva;

public class SinCancelacion implements EstrategiaCancelacion {
	
	public Double calcularPenalizacion(LocalDate hoy, Reserva reserva, Inmueble posteo) {
		return posteo.getPrecioParaReserva(reserva);
	}
}
