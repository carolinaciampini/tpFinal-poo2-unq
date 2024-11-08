package reserva;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import enums.FormaDePago;
import estadoReserva.EstadoReserva;
import estadoReserva.Solicitada;
import inmuebless.Inmuebless;
import mailSender.MailSender;
import usuarios.Usuario;

public class Reserva {
	private Inmuebless posteo;
	private Usuario inquilino;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private FormaDePago formaDePago;
	private EstadoReserva estadoReserva;


	
	public Reserva(Inmuebless posteo, Usuario inquilino, LocalDate fechaEntrada, LocalDate fechaSalida, FormaDePago formaPago) {
		this.posteo = posteo;
		this.inquilino = inquilino;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.formaDePago = formaPago;
		this.estadoReserva = new Solicitada();
	}

	public Inmuebless getPosteo() {
		return posteo;
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

