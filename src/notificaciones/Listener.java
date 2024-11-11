package notificaciones;

import inmueble.Inmueble;
import reserva.Reserva;

public interface Listener {
	void updateBajaPrecio(Inmueble inmueble);
    void updateCancelacion(Reserva reserva);
    void updateReserva(Reserva reserva);
}
