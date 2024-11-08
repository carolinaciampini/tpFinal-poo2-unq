package reserva;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import enums.FormaDePago;
import estadoReserva.EstadoReserva;
import estadoReserva.Solicitada;
import inmueble.Inmueble;
import mailSender.MailSender;
import posteo.Posteo;
import usuarios.Usuario;

public class Reserva {
	private Posteo posteo;
	private Inmueble inmueble;
	private Usuario inquilino;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private FormaDePago formaDePago;
	private EstadoReserva estadoReserva;


	
	public Reserva(Posteo posteo, Inmueble inmueble, Usuario inquilino, LocalDate fechaEntrada, LocalDate fechaSalida, FormaDePago formaPago) {
		this.posteo = posteo;
		this.inmueble = inmueble;
		this.inquilino = inquilino;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.formaDePago = formaPago;
		this.estadoReserva = new Solicitada();
	}

	public Posteo getPosteo() {
		return posteo;
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
	
	public Double getPrecioTotal() {
		return getPosteo().getPrecioParaReserva(this);
	}
	
	public boolean sePisa(LocalDate fechaEntrada, LocalDate fechaSalida) {
	    // Verificamos que no haya solapamiento
		return (fechaEntrada. isBefore(this.fechaSalida)
				&&
				fechaSalida.isAfter(this.fechaEntrada));
	}
	
	public EstadoReserva getEstadoReserva() {
		return estadoReserva;
	}
	
	public void setEstadoReserva(EstadoReserva estadoNuevo) {
		this.estadoReserva = estadoNuevo;
	}
	
	public int getCantidadDeDias() {
        return (int) ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
	}
	
	public void cancelarReserva(LocalDate fecha) {
		estadoReserva.cancelarReserva(this);
	}
}

