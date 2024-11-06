package inmueble;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import enums.FormaDePago;
import enums.Servicio;
import politicaDeCancelacion.PoliticaCancelacion;
import usuarios.Propietario;

public class Inmueble {
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

	public Inmueble (String tipoDeInmueble, Double superficie, String pais, String ciudad, String direccion, LocalTime checkin, LocalTime checkout, Propietario propietario, Double precioBase) { 
		this.tipoInmueble = tipoDeInmueble;
		this.superficie = superficie;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.servicios = new ArrayList<>();
		this.fotos = new String[5];
		this.checkin = checkin;
		this.checkout = checkout;
		this.formasDePago = new ArrayList<>();
		this.propietario = propietario;
		//this.precioBase = precioBase;
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

//public Double getPrecioBase() {
	//return precioBase;
//}


/* fotos
public String[] getFotos() {
	return fotos;
}

public boolean addFoto(String foto) {
	// Busco la primera posición vacía (0) para insertar la foo
    for (int i = 0; i < fotos.length; i++) {
        if (fotos[i] == null) {  // Si encuentro una posición vacía
            fotos[i] = foto;  // agrego a la foto en esa posición
            return true;  // Se agrego
        }
    }
    System.out.println("Límite de usuarios alcanzado. No se puede agregar más usuarios.");
    return false;  // No se pudo agregar
}
*/

}
