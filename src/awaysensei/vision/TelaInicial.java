package awaysensei.vision;

import java.util.Scanner;

import awaysensei.core.Pupilo;
import awaysensei.core.Usuario;
import awaysensei.core.Visitante;

import awaysensei.tools.ControleDeUsuarios;
import awaysensei.tools.Autenticador;

public class TelaInicial {
	private boolean ativa;
	private Visitante visitante;
	private ControleDeUsuarios controlador;
	
	public TelaInicial() {
		this.visitante = new Visitante();
		this.controlador = new ControleDeUsuarios();
	}
	
	public void Executar() {
		ativa = true;
		Scanner leitor = new Scanner(System.in);
		System.out.println("Bem vindo ao AwaySensei.");
		System.out.println("1. Cadastrar ");
		System.out.println("2. Entrar ");
		System.out.println("3. Encerrar ");
		
			int token;
			while(ativa == true) {
				token = leitor.nextInt();
			switch (token) {
				case 1:
					this.Cadastrar(leitor.next(), leitor.next());
					break;
				case 2:
					this.Entrar(leitor.next(), leitor.next());
					ativa = false;
					break;
				case 3:
					this.Encerrar();
					ativa = false;
					break;
				}
			}
		}

	public void Cadastrar(String nomedeusuario_, String senha_) {
		System.out.println("Cadastrando usuario.");
		Usuario user_ = new Usuario(nomedeusuario_,senha_);
		controlador.addUsuario(user_);
		System.out.println("Usuario cadastrado com sucesso.");
		System.out.println("Lista de usuarios:");
		controlador.listarUsuarios();
		this.Executar();
	}

	public void Entrar(String nomeDeUsuario, String senha) {
		System.out.println("Logando usuario");
		Usuario user = new Usuario(nomeDeUsuario, senha);
		Autenticador aut = new Autenticador();
		String loginID = (aut.autenticar(controlador, user));
		if (loginID != null){
			System.out.println("Senha incorreta para o usuário " + nomeDeUsuario);
		} else {
			System.out.println(nomeDeUsuario + " logado com sucesso");
			controlador.setUsuarioOnline(loginID);
		}
	}

	public void Encerrar() {
		System.out.println("Encerrando sistema.");
		System.exit(0);
	}
}
