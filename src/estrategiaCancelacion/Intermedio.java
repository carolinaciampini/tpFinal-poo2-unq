package estrategiaCancelacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import inmueble.Inmueble;
import reserva.Reserva;

public class Intermedio implements EstrategiaCancelacion{
	
	public Double calcularPenalizacion(Reserva reserva, Inmueble inmueble) {
		 
		int diasFaltantes = reserva.cantidadDiasFaltantes();
		if (diasFaltantes < 10) {
	            return inmueble.getPrecioParaReserva(reserva) ;
	        } else {
	            return (diasFaltantes < 20) ? inmueble.getPrecioParaReserva(reserva)*0.5 : 0;
	        }
	    }
	}