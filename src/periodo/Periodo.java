package periodo;

import java.time.LocalDate;

public class Periodo {
	public LocalDate inicio;
	public LocalDate fin;
	public Double incremento;
	public String nombreP;
	
	
	public Periodo(LocalDate inicio, LocalDate fin, Double incremento, String nombreP) {
		super();
		this.inicio = inicio;
		this.fin = fin;
		this.incremento = incremento;
		this.nombreP = nombreP;
	}

	public Double getIncremento() {
		return this.incremento;
	}
	
	public Boolean aplicaPara(LocalDate fecha) {
		return !fecha.isAfter(fin) && !fecha.isBefore(inicio);
	}
	
	public LocalDate getFechaInicio() {
		return this.inicio;
	}
	
	public LocalDate getFechaFin() {
		return this.fin;
	}
}
