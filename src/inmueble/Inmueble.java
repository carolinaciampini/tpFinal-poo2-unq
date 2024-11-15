package inmueble;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import categoria.Categoria;
import enums.FormaDePago;
import estadoReserva.Aprobada;
import estrategiaCancelacion.EstrategiaCancelacion;
import estrategiaCancelacion.Gratuito;
import excepciones.LimiteFotosAlcanzado;
import excepciones.NoHayPuntajesParaEstaCategoria;
import excepciones.NoHayPuntajesSobreEsteInmueble;
import excepciones.NoHayPuntajesSobreEsteUsuario;
import mailSender.MailSender;
import notificaciones.NotificadorManager;
import periodo.PeriodoManager;
import ranking.Ranking;
import reserva.Reserva;
import servicio.Servicio;
import usuarios.IInquilino;
import usuarios.IPropietario;
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
	private IPropietario propietario;
	private NotificadorManager notificador;
	private List<Ranking> rankings;

	public Inmueble(Double precio, MailSender mailSender, PeriodoManager periodo, String tipoDeInmueble, Double superficie, Integer capacidad, String pais, String ciudad, String direccion, LocalTime checkin, LocalTime checkout, IPropietario propietario, Double precioBase,  NotificadorManager notificador) {
		this.reservas = new ArrayList<>();
		this.precioBase = precio;
		this.periodoManager = periodo;
		this.colaDeEspera = new ArrayList<>();
		this.estrategiaCancelacion = new Gratuito();
		this.mailSender = mailSender;
		this.notificador = notificador;

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
		this.rankings = new ArrayList<>();

	}
	
	public EstrategiaCancelacion getEstrategia () {
		return estrategiaCancelacion;
	}
	
	public void setEstrategiaCancelacion(EstrategiaCancelacion estrategia) {
		this.estrategiaCancelacion = estrategia;
		}
	
	
	public void crearReserva (Reserva reserva) {
		if (estaDisponible (reserva.getFechaEntrada(), reserva.getFechaSalida())) {
			reservas.add(reserva);
			notificador.altaDeReserva(reserva);
			
		} else {
			Reserva reservaPendiente = reserva;
			colaDeEspera.addLast(reservaPendiente);
		}
	}
	
	public void seBajaElPrecioDelInmueble(Double precioNuevo) {
		if(precioNuevo < precioBase) {
			this.precioBase = precioNuevo;
			notificador.bajaDePrecio(this);
		}
	}
	
	public void procesarColaEspera() {
	    for (int i = 0; i < colaDeEspera.size(); i++) {
	        Reserva siguiente = colaDeEspera.get(i);

	        // Verifica si el producto está disponible
	        if (siguiente.esEstadoAprobado() && estaDisponible(siguiente.getFechaEntrada(), siguiente.getFechaSalida())) {
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
	
	public void notificarAInquilinoEnEspera(IInquilino inquilino) {
		mailSender.enviarMail(inquilino.getEmail(),
                "Tu reserva fue procesada",
                "Felicitaciones, como hubo una cancelación, tu reserva pudo ser realizada");
	}
	
	public Double precioSugeridoPara(LocalDate fechaEntrada, LocalDate fechaSalida) {
		return this.getPeriodoManager().calcularPrecioPorDia(precioBase, fechaEntrada, fechaSalida);
	}
	
	

	public Double calcularPenalizacion(Reserva reserva) {
		return estrategiaCancelacion.calcularPenalizacion(reserva, this);
	}
				

	public boolean estaDisponible(LocalDate fechaEntrada, LocalDate fechaSalida) {
	    for (Reserva reserva : reservas) {
	        if (reserva.sePisa(fechaEntrada, fechaSalida ) && reserva.esEstadoAprobado()) {
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
	
	
	public Integer getHuespedes() {
		return capacidad;
	}

	public Double getPrecioBase() {
		return this.precioBase; 
	}
	
	public PeriodoManager getPeriodoManager() {
		return this.periodoManager;
	}
	
	public Double getPrecioParaReserva(Reserva r) {
		return this.getPeriodoManager()
				.calcularPrecioPorDia(precioBase, r.getFechaEntrada(), r.getFechaSalida());
	}
	
	public List<Reserva> getColaDeEspera() {
		return colaDeEspera;
	}
	
	public List <Reserva> getReservas() {
		return reservas;
	}
	
	
	public IPropietario getPropietario () {
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
	
	

	public void addServicioo(Servicio servicio) {
		this.servicios.add(servicio);
	}
	
	public void sacarServicio(Servicio servicio) {
		servicios.remove(servicio);
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



	public String getEmailPropietario() {
		return getPropietario().getEmail();
	}

	public void recibirRanking(Ranking ranking) {
		rankings.add(ranking);
		
	}

	public List<String> visualizarComentarios() {
		List<String> comentarios = new ArrayList<>();
		for (Ranking ranking : rankings) {
			comentarios.add(ranking.getComentario());
		}
		return comentarios;
	}

	public List<Ranking> getRankings() {
		return this.rankings;
	}
	
	public Integer promedioPuntajeTotal() throws NoHayPuntajesSobreEsteInmueble{
		if (rankings.isEmpty()) {
			throw new NoHayPuntajesSobreEsteInmueble();
		}
		Integer total = 0;
		for (Ranking ranking : rankings) {
			total += ranking.getPuntaje();
		}
		
		return (total / rankings.size());
	}
	
	public Integer promedioPorCategoria(Categoria categoria) throws NoHayPuntajesParaEstaCategoria {
		Integer total = 0;
		Integer rankingsDeCategoria = 0;
		for (Ranking ranking : rankings) {
			if (ranking.getCategoria().equals(categoria)){
				total += ranking.getPuntaje();
				rankingsDeCategoria ++;
			}
		}
		if (rankingsDeCategoria == 0) {
			throw new NoHayPuntajesParaEstaCategoria();
		}
		
		return (total / rankingsDeCategoria); 
	}
	
	public Integer visualizarPromedioDePropietario() throws NoHayPuntajesSobreEsteUsuario {
		return (this.propietario.puntajePromedioComoPropietario());
	}
	
	public List<Ranking> visualizarPuntajesDePropietario() {
		return (this.propietario.getRankingsComoPropietario());
	}
	
	

}