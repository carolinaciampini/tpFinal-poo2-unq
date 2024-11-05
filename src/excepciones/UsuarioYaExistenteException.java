package excepciones;

public class UsuarioYaExistenteException extends Exception {
	
	public UsuarioYaExistenteException() {
        super("El usuario ya existe");
    }
}
