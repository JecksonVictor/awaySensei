package core;

public class ErroNoCadastroException extends Exception{
	

	public String getMessageSenha() {
		return ("A senha precisa ter 4 ou mais caracteres.");
	}
	
	public String getMessageUsuario() {
		return ("A senha precisa ter 4 ou mais caracteres.");
	}
}
