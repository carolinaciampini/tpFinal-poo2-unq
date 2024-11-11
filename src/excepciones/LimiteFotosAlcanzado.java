package excepciones;

public class LimiteFotosAlcanzado extends Exception{

	public LimiteFotosAlcanzado() {
        super("Solo es posible agregar 5 fotos. El limite fue alcanzado");
    }
}
