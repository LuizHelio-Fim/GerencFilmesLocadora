package Main;

import java.util.ArrayList;
import java.util.Scanner;
import Filmes.Locadora;
import Pessoas.*;

public class SistemaLogin {

    private Scanner sc = new Scanner(System.in);

    public void registrarUsuario(Locadora t) {				//Método para Registrar novos usuários, tanto ADM quanto Clientes
    	Pessoa usuario = new Pessoa();
    	
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

        if (isAdmin) {
        	usuario = new Administrador(id, nome, email, senha, endereco, isAdmin);
        } else {
        	usuario = new Cliente(id, nome, email, senha, endereco, isAdmin);
        }
        if (t.adicionarCliente(usuario)) {
            System.out.println("Usuário salvo com sucesso!");
        } else {
            System.out.println("Erro ao salvar usuário no arquivo.");
        }
    }

    public Pessoa realizarLogin(ArrayList<Pessoa> usuarios) {					//Método para realizar login 
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
