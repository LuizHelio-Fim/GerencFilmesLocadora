package Main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        locadora.iniciar();
        do {
            menuLogin();
            if (usuarioLogado != null) {
                if (usuarioLogado.isAdmin()) {
                    do {
                        menuAdm();
                    } while (opAdm != '0');
                } else if (usuarioLogado instanceof Cliente) {
                    do {
                        menuCliente();
                    } while (opCliente != '0');
                }
            }
        } while (opSistema != '0');
        locadora.finalizar();
    }

    // MENUS
    public static void menuCliente() {
        System.out.print("------ MENU ------"
                + "\n 1. Ver Filmes"
                + "\n 2. Alugar Filmes"
                + "\n 3. Buscar Filme"
                + "\n 4. Visualizar Histórico de locações"
                + "\n 5. Recomendações"
                + "\n 6. Devolver Filme"
                + "\n 0. Sair "
                + "\n > ");
        opCliente = sc.next().charAt(0);

        switch (opCliente) {
            case '1':
                System.out.println(locadora.listarFilmes());
                break;
            case '2':
                alugarFilme();
                break;
            case '3':
                buscarFilme();
                break;
            case '4':
                visualizarHistorico();
                break;
            case '5':
                recomendacao();
                break;
            case '6':
                devolucaoFilme();
                break;
            case '0':
                System.out.println("Saindo...");
                usuarioLogado = null;
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
                + "\n 0. Sair "
                + "\n > ");
        opAdm = sc.next().charAt(0);

        switch (opAdm) {
            case '1':
                if (locadora.listarFilmes().isEmpty()) {
                    System.out.println("Nenhum filme encontrado.\n");
                } else {
                    System.out.println(locadora.listarFilmes());
                }
                break;
            case '2':
                adicionarFilme();
                break;
            case '3':
                removerFilme();
                break;
            case '4':
                removerCliente();
                break;
            case '0':
                System.out.println("Saindo...");
                usuarioLogado = null;
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    public static void menuLogin() {
        System.out.print("------- LOGIN ------"
                + "\n 1. Registrar Usuário"
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
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    // Opções Cliente
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
        Cliente cliente = (Cliente) usuarioLogado;
        System.out.println("Filmes Disponíveis para locação: ");
        System.out.println(locadora.listarFilmesDisponiveis());

        System.out.println("\nDigite o nome do filme que deseja alugar: ");
        sc.nextLine();
        String nome = sc.nextLine();

        Filme filmeSelecionado = locadora.buscarFilme(nome);
        if (filmeSelecionado == null || !filmeSelecionado.isDisponivel()) {
            System.out.println("O filme '" + nome + "' não está disponível para locação.");
            return;
        }

        if (cliente.iniciarLocacao(filmeSelecionado, locadora)) {
            System.out.println("Locação realizada com sucesso!");
        } else {
            System.out.println("Não foi possível realizar a locação.");
        }
    }

    public static void visualizarHistorico() {
        Cliente cliente = (Cliente) usuarioLogado;
        cliente.visualizarHistorico(locadora);
    }

    public static void devolucaoFilme() {
        Cliente cliente = (Cliente) usuarioLogado;

        System.out.println("Digite o nome do filme: ");
        sc.nextLine();
        String nomeFilme = sc.nextLine();

        System.out.println("Digite a data de devolução (AAAA-MM-DD): ");
        String dataDevolucaoStr = sc.nextLine();
        LocalDate dataDevolucao = LocalDate.parse(dataDevolucaoStr);

        Locacao locacao = cliente.devolverFilme(nomeFilme, dataDevolucao, locadora);

        if (locacao == null) {
            System.out.println("[ERRO] Filme não encontrado na lista de locações.");
            return;
        }

        Pagamento pagamento = new Pagamento(locacao, dataDevolucao);
        System.out.println("Valor total a ser pago: " + pagamento.calculaValorFinal());

        System.out.println("Digite o valor do pagamento: ");
        double valorPagamento = sc.nextDouble();

        if (pagamento.processarPagamento(valorPagamento)) {
            cliente.removerLocacao(locacao, locadora);
            System.out.println("Filme devolvido com sucesso!");
        } else {
            System.out.println("Pagamento insuficiente. Tente novamente.");
        }
    }

    public static void recomendacao() {
        Cliente cliente = (Cliente) usuarioLogado;
        Recomendacao rec = new Recomendacao(cliente, catalogoFilmes);
        rec.gerarRecomendacoes();
    }

    // Opções ADM
    public static void adicionarFilme() {
        System.out.println("Digite os detalhes do filme:");
        System.out.print("ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Gênero: ");
        String genero = sc.nextLine();
        System.out.print("Diretor: ");
        String diretor = sc.nextLine();
        System.out.print("Data de Lançamento (DD/MM/AAAA): ");
        String data = sc.nextLine();
        System.out.print("Preço: ");
        double preco = sc.nextDouble();

        Filme filme = new Filme(id, nome, genero, diretor, data, true, preco);
        if (locadora.adicionarFilme(filme)) {
            System.out.println("Filme adicionado com sucesso!");
        } else {
            System.out.println("Erro ao adicionar filme.");
        }
    }

    public static void removerFilme() {
        System.out.println(locadora.listarNomesFilmes());
        System.out.println("\nDigite o título do filme a ser removido: ");
        sc.nextLine();
        String titulo = sc.nextLine();

        if (locadora.removerFilme(titulo)) {
            System.out.println("Filme removido com sucesso.");
        } else {
            System.out.println("Filme não encontrado.");
        }
    }

    public static void removerCliente() {
        System.out.println(locadora.listarNomesClientes());
        System.out.println("\nDigite o nome do cliente a ser removido: ");
        sc.nextLine();
        String nome = sc.nextLine();

        if (locadora.removerCliente(nome)) {
            System.out.println("Cliente removido com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}
