package periodo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PeriodoManagerTest {
	private PeriodoManager periodoManager;
	private Periodo periodo;
		 
    @BeforeEach
    void setUp() {
    	periodoManager = new PeriodoManager();
        
    	periodo = new Periodo(LocalDate.of(2023, 12, 24), LocalDate.of(2023, 12, 26), 50.0, "Fin de semana largo");
        periodoManager.agregarPeriodoEspecial(periodo);
        
        
    };
    
    @Test
    void testCalcularPrecioPorDiaConIncremento() {
    	
        LocalDate fechaEntrada = LocalDate.of(2023, 12, 24);
        LocalDate fechaSalida = LocalDate.of(2023, 12, 26);
        Double precioBase = 100.0;

        
        Double total = periodoManager.calcularPrecioPorDia(precioBase, fechaEntrada, fechaSalida);
        assertEquals(250.0, total);
    };
    @Test
    void testCalcularPrecioPorDiaSinIncremento() {
    	
        LocalDate fechaEntrada = LocalDate.of(2023, 12, 28);
        LocalDate fechaSalida = LocalDate.of(2023, 12, 29);
        Double precioBase = 100.0;

        
        Double total = periodoManager.calcularPrecioPorDia(precioBase, fechaEntrada, fechaSalida);
        assertEquals(100.0, total);
    };
}
