package notificaciones;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inmueble.Inmueble;
import reserva.Reserva;

public class SuscriptorMobileTest {
	private Reserva reserva;
	private PopUpWindow popUpWindow;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private Inmueble inmueble;
    private SuscriptorMobile suscriptorMobile;
    
    @BeforeEach
    public void setUp() {
    	reserva = mock(Reserva.class);
    	popUpWindow = mock(PopUpWindow.class);
        fechaEntrada = LocalDate.of(2024, 11, 1);
        fechaSalida = LocalDate.of(2024, 11, 15);
        suscriptorMobile = new SuscriptorMobile(popUpWindow, fechaEntrada, fechaSalida);
        inmueble = mock(Inmueble.class);
    }
    
    @Test
    public void testUpdateCancelacionDentroDeFechasInteres() {
        
        when(reserva.getFechaEntrada()).thenReturn(LocalDate.of(2024, 11, 5));
        when(reserva.getFechaSalida()).thenReturn(LocalDate.of(2024, 11, 10));
        when(reserva.getTipoInmueble()).thenReturn("Duplex");
        
        suscriptorMobile.cancelacionDeReserva(reserva);
        
        
        verify(popUpWindow).popUp(
            "El Duplex que te interesa se ha liberado. ¡Corre a reservarlo!",
            "blue",
            14
        );
        
        
    }
    
    @Test
    public void testUpdateCancelacionFueraDeRango() {
        when(reserva.getFechaEntrada()).thenReturn(LocalDate.of(2024, 12, 1));
        when(reserva.getFechaSalida()).thenReturn(LocalDate.of(2024, 12, 10));
        
        suscriptorMobile.cancelacionDeReserva(reserva);
        
        verifyNoInteractions(popUpWindow);
    }
    
    @Test
    public void testElSuscriptorNoHaceUpdate() {

    	suscriptorMobile.altaDeReserva(reserva);
    	suscriptorMobile.bajaDePrecio(inmueble);
        verifyNoInteractions(popUpWindow);
        
    }
    
    
}
