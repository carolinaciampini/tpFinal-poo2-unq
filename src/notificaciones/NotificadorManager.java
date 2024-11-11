package notificaciones;

import java.util.ArrayList;
import java.util.List;

import inmueble.Inmueble;
import reserva.Reserva;

public class NotificadorManager {
	private List<Listener> listeners = new ArrayList<>();

    public void agregarListener(Listener listener) {
        listeners.add(listener);
        listeners.addAll(0, listeners);
    }

    public void removerListener(Listener listener) {
        listeners.remove(listener);
    }

    public void notificarBajaPrecio(Inmueble inmueble) {
        for (Listener listener : listeners) {
            listener.updateBajaPrecio(inmueble);
        }
    }

    public void notificarCancelacion(Reserva inmueble) {
        for (Listener listener : listeners) {
            listener.updateCancelacion(inmueble);
        }
    }

    public void notificarReserva(Reserva inmueble) {
        for (Listener listener : listeners) {
            listener.updateReserva(inmueble);
        }
    }
}
