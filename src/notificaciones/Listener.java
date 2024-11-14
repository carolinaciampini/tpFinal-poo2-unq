package notificaciones;

import inmueble.Inmueble;
import reserva.Reserva;

public interface Listener {
	void bajaDePrecio(Inmueble inmueble);
    void cancelacionDeReserva(Reserva reserva);
    void altaDeReserva(Reserva reserva);
}
