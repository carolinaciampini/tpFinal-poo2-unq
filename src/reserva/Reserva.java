package reserva;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import enums.FormaDePago;
import estadoReserva.EstadoReserva;
import estadoReserva.Solicitada;
import excepciones.EstadoInvalidoParaRankear;
import inmueble.Inmueble;
import mailSender.MailSender;
import notificaciones.NotificadorManager;
import ranking.Ranking;
import usuarios.IInquilino;
import usuarios.IPropietario;
import usuarios.Usuario;

public class Reserva {
	private Inmueble inmueble;
	private IInquilino inquilino;
	private LocalDate fechaEntrada;
	private LocalDate fechaSalida;
	private FormaDePago formaDePago;
	private EstadoReserva estadoReserva;
	private MailSender mailSender;
	private NotificadorManager notificador;


	
	public Reserva(Inmueble inmueble, IInquilino inquilino, LocalDate fechaEntrada, LocalDate fechaSalida, FormaDePago formaPago, MailSender mailSender, NotificadorManager notificador) {
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
	    notificador.cancelacionDeReserva(this);
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

	public IInquilino getInquilino() {
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
	
	public void ejecutarPenalizacionPorCancelacion() {
		inmueble.calcularPenalizacion(this);
	} 
	
	public void enviarMailAInquilino(String titulo, String cuerpo) {
		getMailSender().enviarMail(inquilino.getEmail(), titulo, cuerpo);
	}
	
	
	public Integer cantidadDiasFaltantes() {
		return (int) ChronoUnit.DAYS.between(LocalDate.now(), getFechaEntrada());
		
	}
	
	public void rankearInmueble(Ranking ranking) throws EstadoInvalidoParaRankear {
		this.estadoReserva.rankearInmueble(ranking, this);
	}
	
	public void rankearPropietario(Ranking ranking) throws EstadoInvalidoParaRankear {
		this.estadoReserva.rankearPropietario(ranking, this);
	}
	
	public void rankearInquilino(Ranking ranking) throws EstadoInvalidoParaRankear {
		this.estadoReserva.rankearInquilino(ranking, this);
	}
	
	public boolean esEstadoAprobado(){
		return estadoReserva.estaAprobada();
	}

	public boolean esEstadoRechazado(){
		return estadoReserva.estaRechazada();
		}

	public String getTipoInmueble() {
		return this.inmueble.getTipoInmueble();
	}



	public IPropietario getPropietario() {
		return this.inmueble.getPropietario();
	}
	
	public boolean esMismoInquilino(Usuario usuario) {
	    return this.getInquilino().esMismoUsuarioQue(usuario);
	}
}








