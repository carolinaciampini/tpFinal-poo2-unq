package notificaciones;

import java.util.ArrayList;
import java.util.List;

import inmueble.Inmueble;
import reserva.Reserva;

public class NotificadorManager implements Listener{
	private List<Listener> listeners = new ArrayList<>();

    public void agregarListener(Listener listener) {
        listeners.add(listener);
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

    public void notificarReserva(Reserva reserva) {
        for (Listener listener : listeners) {
            listener.updateReserva(reserva);
        }
    }

	@Override
	public void updateBajaPrecio(Inmueble inmueble) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCancelacion(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		
	}
}
