package inmuebless;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import enums.FormaDePago;
import enums.Servicio;
import estadoReserva.Aprobada;
import estrategiaCancelacion.EstrategiaCancelacion;
import estrategiaCancelacion.Gratuito;
import excepciones.LimiteFotosAlcanzado;
import mailSender.MailSender;
import periodo.PeriodoManager;
import reserva.Reserva;
import usuarios.Propietario;
import usuarios.Usuario;

public class Inmuebless {
	private List <Reserva> reservas;
	private PeriodoManager periodoManager;
	private Double precioBase;
	private List <Reserva> colaDeEspera;
	private EstrategiaCancelacion estrategiaCancelacion;
	private MailSender mailSender;
	
//atributos inmuebleviejo
	private String tipoInmueble;
	private Integer capacidad;
	private Double superficie;
	private String pais;
	private String ciudad;
	private String direccion;
	private List<Servicio> servicios;
	private String[] fotos;
	private LocalTime checkin;
	private LocalTime checkout;
	private List<FormaDePago> formasDePago;
	private Propietario propietario;

	public Inmuebless(Double precio, MailSender mailSender, PeriodoManager periodo, String tipoDeInmueble, Double superficie, Integer capacidad, String pais, String ciudad, String direccion, LocalTime checkin, LocalTime checkout, Propietario propietario, Double precioBase) {
		this.reservas = new ArrayList<>();
		this.precioBase = precio;
		this.periodoManager = periodo;
		this.colaDeEspera = new ArrayList<>();
		this.estrategiaCancelacion = new Gratuito(); ;
		this.mailSender = mailSender;
		
//constructor  inmuebleviejo
		this.tipoInmueble = tipoDeInmueble;
		this.superficie = superficie;
		this.capacidad = capacidad;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.servicios = new ArrayList<>();
		this.fotos = new String[5];
		this.checkin = checkin;
		this.checkout = checkout;
		this.formasDePago = new ArrayList<>();
		this.propietario = propietario;

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
		return capacidad;
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
	
	public Propietario getPropietario () {
		return this.propietario;
	}

	public Integer getCapacidad() {
		return this.capacidad;
	}

	public String getTipoInmueble() {
		return tipoInmueble;
	}

	public Double getSuperficie() {
		return superficie;
	}

	public String getPais() {
		return pais;
	}
	
	public String getCiudad() {
		return ciudad;
	}


	public String getDireccion() {
		return direccion;
	}

	// Servicios
	public List<Servicio> getServicios() {
		return servicios;
	}

	public void addServicio(Servicio servicio) {
		this.servicios.add(servicio);
	}
//--


	public LocalTime getCheckin() {
		return checkin;
	}

	public LocalTime getCheckout() {
		return checkout;
	}	

// formas de pago
	public List<FormaDePago> getFormasDePago() {
		return formasDePago;
	}

	public void addFormaDePago(FormaDePago formadepago) {
		this.formasDePago.add(formadepago);
	}
//--
	



	public String[] getFotos() {
	return fotos;
}

	public void addFoto(String foto) throws LimiteFotosAlcanzado{
// Busco la primera posición vacía (0) para insertar la foo
	for (int i = 0; i < fotos.length; i++) {
   	 if (fotos[i] == null) {  // Si encuentro una posición vacía
        	fotos[i] = foto;  // agrego a la foto en esa posición
       	 return;
    	}
    
	}
  	// No se pudo agregar
	throw new LimiteFotosAlcanzado();

}

}