package notificaciones;

import java.util.ArrayList;
import java.util.List;

import inmueble.Inmueble;
import reserva.Reserva;

public class NotificadorManager {
	private List<Listener> listeners = new ArrayList<>();

    public void agregarListener(Listener listener) {
        listeners.add(listener);
    }

    public void removerListener(Listener listener) {
        listeners.remove(listener);
    }

    public void notificarBajaPrecio(Inmueble propiedad) {
        for (Listener listener : listeners) {
            listener.updateBajaPrecio(propiedad);
        }
    }

    public void notificarCancelacion(Reserva propiedad) {
        for (Listener listener : listeners) {
            listener.updateCancelacion(propiedad);
        }
    }

    public void notificarReserva(Reserva propiedad) {
        for (Listener listener : listeners) {
            listener.updateReserva(propiedad);
        }
    }
}
