package posteo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.FormaDePago;
import estadoReserva.Aprobada;
import estrategiaCancelacion.EstrategiaCancelacion;
import inmueble.Inmueble;
import mailSender.MailSender;
import periodo.PeriodoManager;
import reserva.Reserva;
import usuarios.Usuario;

public class Posteo {
	private List <Reserva> reservas;
	private Inmueble inmueble;
	private PeriodoManager periodoManager;
	private Double precioBase;
	private List <Reserva> colaDeEspera;
	private EstrategiaCancelacion estrategiaCancelacion;
	private MailSender mailSender;

	public Posteo(Inmueble inmueble, Double precio, MailSender mailSender, PeriodoManager periodo, EstrategiaCancelacion estrategia) {
		this.reservas = new ArrayList<>();
		this.inmueble = inmueble;
		this.precioBase = precio;
		this.periodoManager = periodo;
		this.colaDeEspera = new ArrayList<>();
		this.estrategiaCancelacion = estrategia;
		this.mailSender = mailSender;
	}
	
	public MailSender getMailSender() {
		return mailSender;
	}
	
	public void setEstrategiaCancelacion(EstrategiaCancelacion estrategia) {
		this.estrategiaCancelacion = estrategia;
		}
	
	public List<Reserva> getColaDeEspera() {
		return colaDeEspera;
	}
	
	public Inmueble getInmueble() {
		return inmueble;
	}
	
	public String getCiudad() {
		return inmueble.getCiudad();
	}
	
	public List <Reserva> getReservas() {
		return reservas;
	}
	
	public void crearReserva (Reserva reserva) {
		if (estaDisponible (reserva.getFechaEntrada(), reserva.getFechaSalida())) {
			reservas.add(reserva);
			reserva.setEstadoReserva(new Aprobada());
		} else {
			Reserva reservaPendiente = reserva;
			colaDeEspera.add(reservaPendiente);
		}
	}
	
	/*if (estaDisponible (fechaEntrada,fechaSalida)) {
			reservas.add(new Reserva(this, inmueble, inquilino, fechaEntrada, fechaSalida, formaPago));
		} else {
			Reserva reservaPendiente = new Reserva (this, inmueble, inquilino, fechaEntrada, fechaSalida, formaPago);
			colaDeEspera.add(reservaPendiente); 
	}
}*/
				

	public boolean estaDisponible(LocalDate fechaEntrada, LocalDate fechaSalida) {
	    for (Reserva reserva : reservas) {
	        if (reserva.sePisa(fechaEntrada, fechaSalida)) {
	            return false; 
	        }
	    }
	    return true;  
	}
	
	
	public int getHuespedes() {
		return this.inmueble.getCapacidad();
	}

	public Double getPrecioBase() {
		return this.precioBase; 
	}
	
	public PeriodoManager getPeiodoManager() {
		return this.periodoManager;
	}
	
	public Double getPrecioParaReserva(Reserva r) {
		return this.getPeiodoManager()
				.calcularPrecioPorDia(precioBase, r.getFechaEntrada(), r.getFechaSalida());
	}
	
	public void procesarColaEspera() {
	    for (int i = 0; i < colaDeEspera.size(); i++) {
	        Reserva siguiente = colaDeEspera.get(i);

	        // Verifica si el producto está disponible
	        if (estaDisponible(siguiente.getFechaEntrada(), siguiente.getFechaSalida())) {
	            reservas.add(siguiente); // Agregar la reserva a la lista de reservas
	            colaDeEspera.remove(i);  // Remover solo la reserva que se procesó correctamente

	            // Enviar un correo de confirmación
	            mailSender.enviarMail(siguiente.getInquilino().getEmail(),
	                                  "Tu reserva fue procesada",
	                                  "Felicitaciones, como hubo una cancelación, tu reserva pudo ser realizada");

	            break; // Detener el proceso al encontrar la primera reserva disponible
	        }
	    }
	}
	
	

	public Double calcularPenalizacion(LocalDate hoy, Reserva reserva) {
		return estrategiaCancelacion.calcularPenalizacion(hoy, reserva, this);
	}
	
}