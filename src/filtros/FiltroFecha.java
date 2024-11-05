package filtros;

import java.time.LocalDate;
import java.util.List;

import Sitio.SitioWeb;
import inmueble.Inmueble;
import posteo.Posteo;
import reserva.Reserva;


public class FiltroFecha implements Criterio{
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	public FiltroFecha(LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
    
	@Override
    public Boolean cumple(Posteo p, SitioWeb sitio) {
        List<Reserva> reservas = sitio.getReservasDe(p); // filtra por posteo, y despues agarra las reservas 
        for (Reserva reserva : reservas) {
            LocalDate reservaInicio = reserva.getFechaEntrada();
            LocalDate reservaFin = reserva.getFechaSalida();

            
            if ((fechaInicio.isBefore(reservaFin) && fechaFin.isAfter(reservaInicio)) ||
                fechaInicio.equals(reservaInicio) || fechaFin.equals(reservaFin)) {
                return false; //  no está disponible en ese rango
            }
        }
        return true; // No hay solapamientos, está disponible
    }
}
