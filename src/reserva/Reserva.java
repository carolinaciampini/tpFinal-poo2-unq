package reserva;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import enums.FormaDePago;
import estadoReserva.EstadoReserva;
import estadoReserva.Solicitada;
import inmueble.Inmueble;
import mailSender.MailSender;
import notificaciones.NotificadorManager;
import usuarios.Usuario;

public class Reserva {
	private Inmueble inmueble;
	private Usuario inquilino;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private FormaDePago formaDePago;
	private EstadoReserva estadoReserva;
	private MailSender mailSender;
	private NotificadorManager notificador;


	
	public Reserva(Inmueble inmueble, Usuario inquilino, LocalDate fechaEntrada, LocalDate fechaSalida, FormaDePago formaPago, MailSender mailSender, NotificadorManager notificador) {
		this.inmueble = inmueble;
		this.inquilino = inquilino;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.formaDePago = formaPago;
		this.estadoReserva = new Solicitada();
		this.mailSender = mailSender;
		this.notificador = notificador;
	}
	
	

	public void cancelarReserva() {
	    estadoReserva.cancelarReserva(this);
	    notificador.notificarCancelacion(this);
	}

	public void aceptarReserva() {
		estadoReserva.aceptarReserva(this);
	}
	
	public void rechazarReserva() {
		estadoReserva.rechazarReserva(this);
	}
	
	public void finalizarReserva() {
		estadoReserva.finalizarReserva(this);
	}
	
	public void enviarMail() {
		estadoReserva.enviarMail(this);
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
	public MailSender getMailSender() {
		return mailSender;
	}
	
	public int cantidadDeDias() {
        return (int) ChronoUnit.DAYS.between(fechaEntrada, fechaSalida);
	}
	
	public void setEstadoReserva(EstadoReserva estadoNuevo) {
		this.estadoReserva = estadoNuevo;
	}


	public void enviarMailDeAviso(String titulo, String cuerpo) {
		getMailSender().enviarMail(inmueble.getEmailPropietario(), titulo, cuerpo);
		
	}
	
	public void ejecutarColaEspera() {
		inmueble.procesarColaEspera();
	}
	
	public void borrarReserva(Reserva reserva) {
		inmueble.eliminarReserva(reserva);
    	
	}
	
	public void penalizacionDeInmueble() {
		inmueble.calcularPenalizacion(this);
	}
	
	public void enviarMailAInquilino(String titulo, String cuerpo) {
		getMailSender().enviarMail(inquilino.getEmail(), titulo, cuerpo);
	}
	
	
	public int cantidadDiasFaltantes() {
		return (int) ChronoUnit.DAYS.between(LocalDate.now(), getFechaEntrada());
		
	}



	public String getTipoInmueble() {
		return this.inmueble.getTipoInmueble();
	}
}








