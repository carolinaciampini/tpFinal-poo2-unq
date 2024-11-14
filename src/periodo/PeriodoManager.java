package periodo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PeriodoManager {
	private List<Periodo> periodosDelAño;
	
	public PeriodoManager() {
		this.periodosDelAño = new ArrayList<> ();
	};
	
	public Double calcularPrecioPorDia(Double precioBase, LocalDate fechaEntrada, LocalDate fechaSalida) {
		Double total = precioBase;
        LocalDate factual = fechaEntrada;
        
        while (!factual.isAfter(fechaSalida)) {
            for (Periodo p : this.periodosDelAño) {
                if (p.aplicaPara(factual)) {
                    total += p.getIncremento();
                }
            }
            factual = sumarDia(factual); 
        }
        
        return total;
    }
    
    private LocalDate sumarDia(LocalDate fecha) {
        return fecha.plusDays(1);
    }
    
    public void agregarPeriodoEspecial(Periodo periodo) {
    	this.periodosDelAño.add(periodo);
    }
}
