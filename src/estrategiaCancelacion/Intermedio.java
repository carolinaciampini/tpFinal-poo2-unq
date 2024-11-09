package estrategiaCancelacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import inmueble.Inmueble;
import reserva.Reserva;

public class Intermedio implements EstrategiaCancelacion{
	
	public Double calcularPenalizacion(LocalDate hoy, Reserva reserva, Inmueble posteo) {
		 
		int diasFaltantes = (int) ChronoUnit.DAYS.between(hoy, reserva.getFechaEntrada());
		if (diasFaltantes < 10) {
	            return posteo.getPrecioParaReserva(reserva) ;
	        } else {
	            return (diasFaltantes < 20) ? posteo.getPrecioParaReserva(reserva)*0.5 : 0;
	        }
	    }
	}