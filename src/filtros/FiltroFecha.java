package filtros;

import java.time.LocalDate;

import Sitio.SitioWeb;
import inmueble.Inmueble;


public class FiltroFecha {
	private LocalDate fechaInicio;
	private LocalDate fechaFin;

	public FiltroFecha(LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
    
	@Override
    public Boolean cumple(Inmueble inmueble, SitioWeb sitio) {
        List<Reserva> reservas = sitio.getReservas(inmueble);
        for (Reserva reserva : reservas) {
            LocalDate reservaInicio = reserva.getFechaInicio();
            LocalDate reservaFin = reserva.getFechaFin();

            
            if ((fechaInicio.isBefore(reservaFin) && fechaFin.isAfter(reservaInicio)) ||
                fechaInicio.equals(reservaInicio) || fechaFin.equals(reservaFin)) {
                return false; //  no está disponible en ese rango
            }
        }
        return true; // No hay solapamientos, está disponible
    }
}
