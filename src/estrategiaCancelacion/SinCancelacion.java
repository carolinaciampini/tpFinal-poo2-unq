package estrategiaCancelacion;

import java.time.LocalDate;

import inmueble.Inmueble;
import reserva.Reserva;

public class SinCancelacion implements EstrategiaCancelacion {
	
	public Double calcularPenalizacion(Reserva reserva, Inmueble inmueble) {
		return inmueble.getPrecioParaReserva(reserva);
	}
}
