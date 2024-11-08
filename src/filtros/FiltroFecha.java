package filtros;

import java.time.LocalDate;
import java.util.List;

import Sitio.SitioWeb;
import inmueble.Inmueble;
import inmuebless.InmueblessREEMPLAZO;
import reserva.Reserva;


public class FiltroFecha implements Criterio{
	private LocalDate fechaEntrada;
    private LocalDate fechaSalida;

    public FiltroFecha(LocalDate fechaEntrada, LocalDate fechaSalida) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    @Override
    public Boolean cumple(InmueblessREEMPLAZO posteo) {
        return posteo.estaDisponible(fechaEntrada, fechaSalida);
    }
}
