package estrategiaCancelacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import inmueble.Inmueble;
import reserva.Reserva;

public class Gratuito implements EstrategiaCancelacion{
	
	public Double calcularPenalizacion(Reserva reserva, Inmueble inmueble) {
		int diasFaltantes = reserva.cantidadDiasFaltantes();
		double precioReservaPorDia = inmueble.getPrecioParaReserva(reserva) / reserva.cantidadDeDias();
		if (diasFaltantes <= 10) {
			return precioReservaPorDia * 2; 
		}
		return (double) 0;
	}
}
