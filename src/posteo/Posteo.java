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

	public Posteo(Inmueble inmueble) {
		this.reservas = new ArrayList<>();
		this.inmueble = inmueble;
	}
	
	public Inmueble getInmueble() {
		return inmueble;
	}
	
	public List <Reserva> getReservas() {
		return reservas;
	}
	
	public void crearReserva (Inmueble inmueble, Usuario inquilino, LocalDate fechaEntrada, LocalDate fechaSalida, FormaDePago formaPago) {
		reservas.add(new Reserva(this, inmueble, inquilino, fechaEntrada, fechaSalida, formaPago));
	}
}