package Main;

import java.util.ArrayList;
import java.util.Scanner;
import Filmes.Locadora;
import Pessoas.Pessoa;
import Filmes.Filme;

public class ProgramaPrincipal {

	public static Scanner sc = new Scanner(System.in);
	public static SistemaLogin sistema = new SistemaLogin();
	public static char opCliente, opAdm, opSistema;
	public static Pessoa usuarioLogado = null;
	
	public static ArrayList<Filme> catalogoFilmes = new ArrayList<>();
	public static ArrayList<Pessoa> usuarios = new ArrayList<>();
	public static Locadora locadora = new Locadora(catalogoFilmes, usuarios);
	
	
	public static void main(String[] args) {
		locadora.iniciar();
		do {
			menuLogin();
			if (usuarioLogado != null) {
				if (usuarioLogado.isAdmin()) {
					do {
						menuAdm();						
					} while (opAdm != '0');
				} else {
					do {
						menuCliente();
					} while (opCliente != '0');
				}
			}
		} while (opSistema != '0');
		locadora.finalizar();
	}
	
	
	public static void menuCliente() {
		System.out.print("------ MENU ------"
					+ "\n 1. Ver Filmes"
					+ "\n 2. Alugar Filmes"
					+ "\n 3. Buscar Filme"
					+ "\n 4. Visualizar Histórico de locações"
					+ "\n 5. Reservar Filme"
					+ "\n 0. Sair "
					+ "\n > ");
		opCliente = sc.next().charAt(0);
		
		 switch (opCliente) {
         case '1':
            System.out.println(locadora.listarFilmes());
             break;
         case '2':
             System.out.println("Alugar filme...");
             // Lógica de aluguel
             break;
         case '3':
             buscarFilme();
             break;
         case '4':
             System.out.println("Histórico de locações...");
             // Mostrar histórico do cliente
             break;
         case '5':
        	 break;
         case '0':
             System.out.println("Saindo...");
             break;
         default:
             System.out.println("Opção inválida.");
		}
	}
	
	public static void menuAdm() {
		System.out.print("------ MENU ADM ------"
				+ "\n 1. Ver Filmes"
				+ "\n 2. Adicionar Filme"
				+ "\n 3. Remover Filme"
				+ "\n 4. Remover Cliente"
				+ "\n 5. Gerenciar Reserva"
				+ "\n 6. Calcular Multa"
				+ "\n 0. Sair "
				+ "\n > ");
		opAdm = sc.next().charAt(0);
		
		switch (opAdm) {
			case '1':
				break;
			case '2':
				break;
			case '3':
				break;
			case '4':
				break;
			case '5':
				break;
			case '6':
				break;
			case '0':
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida.");
		}
	}
	
	public static void menuLogin() {
		System.out.print("------- LOGIN ------"
					+ "\n 1. Registrar usuário"
					+ "\n 2. Realizar Login"
					+ "\n 0. Finalizar Programa"
					+ "\n > ");
		opSistema = sc.next().charAt(0);
		
		switch (opSistema) {
			case '1':
				sistema.registrarUsuario();
				break;
			case '2':
				usuarioLogado = sistema.realizarLogin();
				break;
			case '0':
				System.out.println("Encerrando o Sistema.");
				usuarioLogado = null;
				break;
			default:
				System.out.println("Opção Inválida. Tente Novamente.");
		}
	}

	public static void buscarFilme() {
		System.out.println("Digite o nome do filme: ");
		sc.nextLine();
		
		String filme = sc.nextLine();
		Filme filmeEncontrado = locadora.buscarFilme(filme);
		
		if (filmeEncontrado == null) {
			System.out.println("Filme não encontrado.");
		} else {
			System.out.println("Detalhes do Filme:");
			System.out.println(filmeEncontrado.toString());
		}
	}
	
	
}
