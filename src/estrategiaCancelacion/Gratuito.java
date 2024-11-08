package estrategiaCancelacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import inmuebless.Inmuebless;
import reserva.Reserva;

public class Gratuito implements EstrategiaCancelacion{
	
	public Double calcularPenalizacion(LocalDate hoy, Reserva reserva, Inmuebless posteo) {
		long diasFaltantes = ChronoUnit.DAYS.between(hoy, reserva.getFechaEntrada());
		double precioReservaPorDia = posteo.getPrecioParaReserva(reserva) / reserva.getCantidadDeDias();
		if (diasFaltantes <= 10) {
			return precioReservaPorDia * 2; 
		}
		return (double) 0;
	}
}
