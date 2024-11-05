package posteo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import enums.FormaDePago;
import inmueble.Inmueble;
import periodo.PeriodoManager;
import reserva.Reserva;
import usuarios.Usuario;

public class Posteo {
	private List <Reserva> reservas;
	private Inmueble inmueble;
	private PeriodoManager manager;
	private Double precioBase;

	public Posteo(Inmueble inmueble, Double precio, PeriodoManager manager) {
		this.reservas = new ArrayList<>();
		this.inmueble = inmueble;
		this.precioBase = precio;
		this.manager = manager;
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
	
	public void crearReserva (Inmueble inmueble, Usuario inquilino, LocalDate fechaEntrada, LocalDate fechaSalida, FormaDePago formaPago) {
		reservas.add(new Reserva(this, inmueble, inquilino, fechaEntrada, fechaSalida, formaPago));
	}

	public int getHuespedes() {
		return this.inmueble.getCapacidad();
	}

	public Double getPrecioBase() {
		return this.precioBase; 
	}
	
	public PeriodoManager getPeiodoManager() {
		return this.manager;
	}
	
	public Double getPrecioParaReserva(Reserva r) {
		return this.getPeiodoManager()
				.calcularPrecioPorDia(precioBase, r.getFechaEntrada(), r.getFechaSalida());
	}
}