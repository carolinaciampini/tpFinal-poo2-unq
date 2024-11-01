package usuarios;

public abstract class Usuario {
	private String nombreCompleto;
	private String email;
	private String telefono;

	public Usuario(String nombre, String mail, String tel) {
		this.nombreCompleto = nombre;
		this.email = mail;
		this.telefono = tel;
	}
	
	public String getNombre() {
		return nombreCompleto;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getTelefono() {
		return telefono;
	}
}
