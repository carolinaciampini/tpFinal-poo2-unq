package inmueble;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import enums.FormaDePago;
import enums.Servicio;
import usuarios.Propietario;

public class Inmueble {
	private String tipoInmueble;
	private Double superficie;
	private String pais;
	private String ciudad;
	private String direccion;
	private List<Servicio> servicios;
	private List<String> fotos;
	private LocalTime checkin;
	private LocalTime checkout;
	private List<FormaDePago> formasDePago;
	private Propietario propietario;
	/*Falta el precio*/

	public Inmueble (String tipoDeInmueble, Double superficie, String pais, String ciudad, String direccion, LocalTime checkin, LocalTime checkout, Propietario propietario) { 
		this.tipoInmueble = tipoDeInmueble;
		this.superficie = superficie;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.servicios = new ArrayList<>();
		this.fotos = new ArrayList<>();
		this.checkin = checkin;
		this.checkout = checkout;
		this.formasDePago = new ArrayList<>();
		this.propietario = propietario;
		
	}

public Propietario getPropietario () {
	return this.propietario;
	}
}
