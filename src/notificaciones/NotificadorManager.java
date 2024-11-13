package notificaciones;

import java.util.ArrayList;
import java.util.List;

import inmueble.Inmueble;
import reserva.Reserva;

public class NotificadorManager implements Listener{
	private List<Listener> listeners;
	
	public NotificadorManager() {
		this.listeners = new ArrayList<>();
	}

    public void agregarListener(Listener listener) {
        listeners.add(listener);
    }

    public void removerListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void bajaDePrecio(Inmueble inmueble) {
        for (Listener listener : listeners) {
            listener.bajaDePrecio(inmueble);
        }
    }

    @Override
    public void cancelacionDeReserva(Reserva inmueble) {
        for (Listener listener : listeners) {
            listener.cancelacionDeReserva(inmueble);
        }	
    }

    @Override
    public void altaDeReserva(Reserva reserva) {
        for (Listener listener : listeners) {
            listener.altaDeReserva(reserva);
        }
    }


	
}
