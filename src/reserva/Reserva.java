package reserva;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import enums.FormaDePago;
import estadoReserva.Cancelada;
import estadoReserva.EstadoReserva;
import estadoReserva.Finalizada;
import estadoReserva.Solicitada;
import inmueble.Inmueble;
import mailSender.MailSender;
import usuarios.Usuario;

public class Reserva {
	private Inmueble inmueble;
	private Usuario inquilino;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private FormaDePago formaDePago;
	private EstadoReserva estadoReserva;


	
	public Reserva(Inmueble inmueble, Usuario inquilino, LocalDate fechaEntrada, LocalDate fechaSalida, FormaDePago formaPago) {
		this.inmueble = inmueble;
		this.inquilino = inquilino;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.formaDePago = formaPago;
		this.estadoReserva = new Solicitada();
	}
	

	public void cancelarReserva(LocalDate fecha) {
		estadoReserva = new Cancelada();
		estadoReserva.cancelarReserva(this);
	}

	public void finalizarReserva() {
		estadoReserva.finalizarReserva(this);
	}
	
	public Double getPrecioTotal() {
		return getInmueble().getPrecioParaReserva(this);
	}
	
	public boolean sePisa(LocalDate fechaEntrada, LocalDate fechaSalida) {
	    return (fechaEntrada.isBefore(this.fechaSalida) && fechaSalida.isAfter(this.fechaEntrada));
	}

	public Inmueble getInmueble() {
		return inmueble;
	}

	public Usuario getInquilino() {
		return inquilino;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public FormaDePago getFormaDePago() {
		return formaDePago;
	}
	
	public EstadoReserva getEstadoReserva() {
		return estadoReserva;
	}
	
	public int cantidadDeDias() {
        return (int) ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
	}
	
	public void setEstadoReserva(EstadoReserva estadoNuevo) {
		this.estadoReserva = estadoNuevo;
	}
	
}

