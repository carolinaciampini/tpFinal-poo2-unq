package reserva;

import java.time.LocalDate;

import enums.FormaDePago;
import inmueble.Inmueble;
import posteo.Posteo;
import usuarios.Usuario;

public class Reserva {
	private Posteo posteo;
	private Inmueble inmueble;
	private Usuario inquilino;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private FormaDePago formaDePago;

	
	public Reserva(Posteo posteo, Inmueble inmueble, Usuario inquilino, LocalDate fechaEntrada, LocalDate fechaSalida, FormaDePago formaPago) {
		this.posteo = posteo;
		this.inmueble = inmueble;
		this.inquilino = inquilino;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.formaDePago = formaPago;
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


}

