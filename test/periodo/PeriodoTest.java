package periodo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PeriodoTest {
	private Periodo periodo;
	
	@BeforeEach
	public void setUp() {
		 periodo = new Periodo(LocalDate.of(2023, 12, 24), LocalDate.of(2023, 12, 26), 50.0, "Navidad");
	}
	
	
	@Test
	public void testGetIncremento() {
        assertEquals(50.0, periodo.getIncremento());
    }
    
    @Test
    public void testAplicaPara_FechaDentroDelPeriodo() {
        assertTrue(periodo.aplicaPara(LocalDate.of(2023, 12, 25)));
    }
    
    @Test
    public void testAplicaPara_FechaFueraDelPeriodo() {
        assertFalse(periodo.aplicaPara(LocalDate.of(2023, 12, 27)));
        assertFalse(periodo.aplicaPara(LocalDate.of(2023, 12, 23)));
    }
    
    @Test
    public void testAplicaPara_FechaLimiteDelPeriodo() {
        assertTrue(periodo.aplicaPara(LocalDate.of(2023, 12, 24))); 
        assertTrue(periodo.aplicaPara(LocalDate.of(2023, 12, 26))); 
    }
    
    @Test
    public void testGetFechaInicio() {
        assertEquals(LocalDate.of(2023, 12, 24), periodo.getFechaInicio());
    }
    
    @Test
    public void testGetFechaFin() {
        assertEquals(LocalDate.of(2023, 12, 26), periodo.getFechaFin());
    }
}
