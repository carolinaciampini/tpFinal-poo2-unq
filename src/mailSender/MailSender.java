package mailSender;

public interface MailSender {
	
	 void enviarMail(String mailDestinatario, String titulo, String cuerpo);
}
