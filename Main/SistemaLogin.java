package Main;

import java.util.ArrayList;
import java.util.Scanner;
import Filmes.Locadora;
import Pessoas.Pessoa;

public class SistemaLogin {
    private ArrayList<Pessoa> usuarios = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void registrarUsuario() {				//Método para Registrar novos usuários, tanto ADM quanto Clientes
    	System.out.println("Digite seu ID:");
    	int id = sc.nextInt();
    	sc.nextLine();
    	
        System.out.println("Digite seu nome:");
        String nome = sc.nextLine();

        System.out.println("Digite seu email:");
        String email = sc.nextLine();

        System.out.println("Digite sua senha:");
        String senha = sc.nextLine();

        System.out.println("Digite seu endereço:");
        String endereco = sc.nextLine();

        System.out.println("Se deseja ser admin, insira o código (ou pressione Enter para continuar como cliente):");
        String codigo = sc.nextLine();

        boolean isAdmin = "0000".equals(codigo);			//Código fornecido para criar um admin

        Pessoa novoUsuario = new Pessoa(id, nome, email, senha, endereco, isAdmin);
        usuarios.add(novoUsuario);
        Locadora locadora = new Locadora(new ArrayList<>(), usuarios);
        if (locadora.salvarDadosUsuarios()) {
            System.out.println("Usuário salvo com sucesso!");
        } else {
            System.out.println("Erro ao salvar usuário no arquivo.");
        }
    }

    public Pessoa realizarLogin() {					//Método para realizar login 
        System.out.println("Digite seu email:");
        String email = sc.nextLine();

        System.out.println("Digite sua senha:");
        String senha = sc.nextLine();

        for (Pessoa usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.verificarSenha(senha)) {
                System.out.println("Login realizado com sucesso! Bem-vindo(a), " + usuario.getNome() + ".");
                System.out.println(usuario.isAdmin() ? "Você é um administrador." : "Você é um cliente.");
                return usuario;
            }
        }
        System.out.println("Email ou senha incorretos.");
        return null;
    }

}
