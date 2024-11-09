package reserva;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import enums.FormaDePago;
import estadoReserva.EstadoReserva;
import estadoReserva.Solicitada;
import inmuebless.Inmueble;
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
		return getInmueble().getPrecioParaReserva(this);
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

