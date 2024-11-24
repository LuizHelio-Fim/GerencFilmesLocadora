package Main;

import java.util.ArrayList;
import java.util.Scanner;
import Pessoas.*;
import Filmes.*;

public class ProgramaPrincipal {

	public static Scanner sc = new Scanner(System.in);
	public static SistemaLogin sistema = new SistemaLogin();
	public static char opCliente, opAdm, opSistema;
	public static Pessoa usuarioLogado = null;
	
	public static ArrayList<Filme> catalogoFilmes = new ArrayList<>();
	public static ArrayList<Pessoa> usuarios = new ArrayList<>();
	public static Locadora locadora = new Locadora(catalogoFilmes, usuarios);
	public static Cliente cliente = new Cliente();
	
	
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
	
	
	//MENUS
	public static void menuCliente() {
		System.out.print("------ MENU ------"
					+ "\n 1. Ver Filmes"
					+ "\n 2. Alugar Filmes"
					+ "\n 3. Buscar Filme"
					+ "\n 4. Visualizar Histórico de locações"
					+ "\n 5. Recomendações"
					+ "\n 0. Sair "
					+ "\n > ");
		opCliente = sc.next().charAt(0);
		
		 switch (opCliente) {
         case '1':
            System.out.println(locadora.listarFilmes());
             break;
         case '2':
             // Lógica de aluguel
             break;
         case '3':
             buscarFilme();
             break;
         case '4':
             // Mostrar histórico do cliente
             break;
         case '5':
        	 recomendacao();
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
				+ "\n 0. Sair "
				+ "\n > ");
		opAdm = sc.next().charAt(0);
		
		switch (opAdm) {
			case '1':
				if (locadora.listarFilmes().equals("")) {
					System.out.println("Nenhum filme encontrado.\n");
				} else {
					System.out.println(locadora.listarFilmes());
				}
				break;
			case '2':
				adicionarFilme(locadora);
				break;
			case '3':
				removerFilme(locadora);
				break;
			case '4':
				removerCliente(locadora);
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
	
	public static void menuLogin() {
		System.out.print("------- LOGIN ------"
					+ "\n 1. Registrar usuário"
					+ "\n 2. Realizar Login"
					+ "\n 0. Finalizar Programa"
					+ "\n > ");
		opSistema = sc.next().charAt(0);
		
		switch (opSistema) {
			case '1':
				sistema.registrarUsuario(locadora);
				break;
			case '2':
				usuarioLogado = sistema.realizarLogin(usuarios);
				break;
			case '0':
				System.out.println("Encerrando o Sistema.");
				usuarioLogado = null;
				break;
			default:
				System.out.println("Opção Inválida. Tente Novamente.");
		}
	}

	//Opções ADM
	public static void adicionarFilme(Locadora t) {
		Filme filme = new Filme();
		
		System.out.println("Digite o ID do filme: ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Digite o nome do filme: ");
		String nome = sc.nextLine();
		System.out.println("Digite o genero do filme: ");
		String genero = sc.nextLine();
		System.out.println("Digite o diretor do filme: ");
		String diretor = sc.nextLine();
		System.out.println("Digite a data de lançamento do filme: (DD/MM/AAAA)");
		String data = sc.nextLine();
		System.out.println("Digite o preço do filme: ");
		double preco = sc.nextDouble();
		
		filme = new Filme(id, nome, genero, diretor, data, true, preco);
		if (t.adicionarFilme(filme)){ 	
			System.out.println("Filme adicionado com sucesso.");
		} else {
			System.out.println("Filme inválido.");
		}
	}
	
	public static void removerFilme(Locadora t) {
		System.out.println(locadora.listarNomesFilmes());
		String titulo;
		sc.nextLine();
		System.out.println("\nDigite o Titulo do filme: ");
		titulo = sc.nextLine();
		
		if (t.removerFilme(titulo)) {
			System.out.println("Filme removido com sucesso.");
		} else {
			System.out.println("Filme não encontrado.");
		}
	}
	
	public static void removerCliente(Locadora t) {
		System.out.println(locadora.listarNomesClientes());
		String nome;
		sc.nextLine();
		System.out.println("\nDigite o nome do cliente: ");
		nome = sc.nextLine();
		
		if (t.removerCliente(nome)) {
			System.out.println("Cliente removido com sucesso.");
		} else {
			System.out.println("Cliente não encontrado.");
		}
	}
	
	public static void gerenciarReserva() {
		
	}
	
	public static void calcularMulta() {
		
	}
	
	//Opções cliente
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
	
	public static void alugarFilme() {
		if (catalogoFilmes.isEmpty()) {
			System.out.println("Não há filmes disponíveis no catálogo.");
			return;
		}
		
		System.out.println("Filmes Disponíveis para locação: ");
		System.out.println(locadora.listarFilmes());
		
		System.out.println("\nDigite o nome do filme que deseja alugar: ");
	    String nome = sc.nextLine();
	    
	    Filme filmeSelecionado = null;
	    for (Filme filme : catalogoFilmes) {
	    	if(filme.getNome().equalsIgnoreCase(nome)) {
	    		filmeSelecionado = filme;
	    		break;
	    	}
	    }
	    
	    if (filmeSelecionado == null) {
	    	System.out.println("O filme '" + nome + "' não foi encontrado no cátalogo. ");
	    	return;
	    }
	    
	    if (cliente.iniciarLocacao(filmeSelecionado)) {
	    	System.out.println("Locação realizada com sucesso!");
	    } else {
	    	System.out.println("Não foi possível realizar a locação");
	    }
		
	}
	
	public static void histFilmes() {
		cliente.visualizarHistorico();
	}
	
	public static void recomendacao() {
		Recomendacao rec = new Recomendacao((Cliente) usuarioLogado, catalogoFilmes);
		rec.gerarRecomendacoes();
	}
}
