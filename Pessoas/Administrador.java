package Pessoas;

import java.util.ArrayList;
import Filmes.Filme;
import Filmes.Locadora;

public class Administrador extends Pessoa {

	public Administrador(int id , String nome, String email, String senha, String endereco, boolean isAdmin) {
		super(id , nome, email, senha, endereco, isAdmin);
	}
	
    public static Locadora locadora = new Locadora();
	
	public boolean removerUsuario(Pessoa user, ArrayList<Pessoa> usuarios) {
		if (usuarios.remove(user)) {
			System.out.println("Usuário removido com sucesso: " + user.getNome());
			return true;
		} else {
			System.out.println("Usuário não encontrado.");
			return false;
		}
	}
	
	public void listarUsuarios(ArrayList<Pessoa> usuarios) {
		if (usuarios.isEmpty()) {
			System.out.println("Nenhum usuário encontrado.");
		} else {
			System.out.println("Lista de Usuários: ");
			for (Pessoa usuario : usuarios) {
				System.out.println(usuario);
			}
		}
	}
	
	public boolean adicionarFilme(Filme filme, ArrayList<Filme> catalogoFilmes) {
		if (filme != null) {
			catalogoFilmes.add(filme);
			System.out.println("Filme Adicionado com Sucesso:" + filme.getNome());
			return true;
		} else {
			System.out.println("Filme inválido.");
			return false;
		}
	}
	
	public boolean removerFilme(Filme filme, ArrayList<Filme> catalogoFilmes) {
		if (catalogoFilmes.remove(filme)) {
			System.out.println("Filme removido com sucesso:" + filme.getNome());
			return true;
		} else {
			System.out.println("Filme não encontrado.");
			return false;
		}
	}
	
	public boolean verificarHistoricoCliente(Cliente cliente) {
		if (cliente != null) {
			cliente.visualizarHistorico(locadora);
			return true;
		} else {
			System.out.println("Cliente inválido.");
			return false;
		}
	}

}
