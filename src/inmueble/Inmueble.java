package inmueble;

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
import usuarios.Usuario;

public class Inmueble {
	private List <Reserva> reservas;
	private PeriodoManager periodoManager;
	private Double precioBase;
	private List <Reserva> colaDeEspera;
	private EstrategiaCancelacion estrategiaCancelacion;
	private MailSender mailSender;
	
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
	private Usuario propietario;

	public Inmueble(Double precio, MailSender mailSender, PeriodoManager periodo, String tipoDeInmueble, Double superficie, Integer capacidad, String pais, String ciudad, String direccion, LocalTime checkin, LocalTime checkout, Usuario propietario, Double precioBase) {
		this.reservas = new ArrayList<>();
		this.precioBase = precio;
		this.periodoManager = periodo;
		this.colaDeEspera = new ArrayList<>();
		this.estrategiaCancelacion = new Gratuito();
		this.mailSender = mailSender;
		

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
	

	
	public void setEstrategiaCancelacion(EstrategiaCancelacion estrategia) {
		this.estrategiaCancelacion = estrategia;
		}
	
	
	public void crearReserva (Reserva reserva) {
		if (estaDisponible (reserva.getFechaEntrada(), reserva.getFechaSalida())) {
			reservas.add(reserva);
			reserva.setEstadoReserva(new Aprobada());
		} else {
			Reserva reservaPendiente = reserva;
			colaDeEspera.addLast(reservaPendiente);
		}
	}
	
	public void procesarColaEspera() {
	    for (int i = 0; i < colaDeEspera.size(); i++) {
	        Reserva siguiente = colaDeEspera.get(i);

	        // Verifica si el producto está disponible
	        if (estaDisponible(siguiente.getFechaEntrada(), siguiente.getFechaSalida())) {
	            reservas.add(siguiente); // Agregar la reserva a la lista de reservas
	            colaDeEspera.remove(i);  // Remover solo la reserva que se procesó correctamente

	            notificarAInquilinoEnEspera(siguiente.getInquilino());

	            break; 
	        }
	    }
	}
	
	public void eliminarReserva(Reserva reserva) {
		reservas.remove(reserva);
	}
	
	public void notificarAInquilinoEnEspera(Usuario inquilino) {
		mailSender.enviarMail(inquilino.getEmail(),
                "Tu reserva fue procesada",
                "Felicitaciones, como hubo una cancelación, tu reserva pudo ser realizada");
	}
	
	public Double precioSugeridoPara(LocalDate fechaEntrada, LocalDate fechaSalida) {
		return this.getPeiodoManager().calcularPrecioPorDia(precioBase, fechaEntrada, fechaSalida);
	}
	
	

	public Double calcularPenalizacion(LocalDate fecha, Reserva reserva) {
		return estrategiaCancelacion.calcularPenalizacion(fecha, reserva, this);
	}
				

	public boolean estaDisponible(LocalDate fechaEntrada, LocalDate fechaSalida) {
	    for (Reserva reserva : reservas) {
	        if (reserva.sePisa(fechaEntrada, fechaSalida)) {
	            return false; 
	        }
	    }
	    return true; 
	     
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
	
	public List<Reserva> getColaDeEspera() {
		return colaDeEspera;
	}
	
	public List <Reserva> getReservas() {
		return reservas;
	}
	
	
	public Usuario getPropietario () {
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

	public MailSender getMailSender() {
		return mailSender;
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



	public String mailPropietario() {
		return this.getPropietario().getEmail();
	}

	

}