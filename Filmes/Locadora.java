package Filmes;

import java.util.ArrayList;    // Para usar o ArrayList

import Pessoas.Administrador;
import Pessoas.Pessoa;

import java.io.BufferedReader; // Para ler arquivos linha por linha
import java.io.BufferedWriter; // Para escrever em arquivos linha por linha
import java.io.FileReader;     // Para abrir um arquivo para leitura
import java.io.FileWriter;     // Para abrir um arquivo para escrita
import java.io.IOException;    // Para tratar exceções de I/O



public class Locadora {
	private ArrayList<Filme> catalogoFilmes = new ArrayList<>();
	private ArrayList<Pessoa> usuarios = new ArrayList<>();
	
	//Construtor
	public Locadora(ArrayList<Filme> catalogoFilmes, ArrayList<Pessoa> usuarios) {
		this.catalogoFilmes = catalogoFilmes;
		this.usuarios = usuarios;
	}
	
	//Getters e Setters
	public Filme getCatalogoFilmes(int index) {
		return catalogoFilmes.get(index);
	}

	public void setCatalogoFilmes(Filme filme) {
		this.catalogoFilmes.add(filme);
	}

	public Pessoa getUsuarios(int index) {
		return usuarios.get(index);
	}

	public void setUsuarios(Pessoa pessoa) {
		this.usuarios.add(pessoa);
	}
	
	//métodos Locadora
	public boolean carregarDadosFilme() { //Carrega os dados dos filmes
	    try (BufferedReader br = new BufferedReader(new FileReader("filmes.txt"))) { //Lê os dados do arquivo filmes.txt
	        String linha;
	        while ((linha = br.readLine()) != null) {
	            String[] dados = linha.split(";");
	            
	            Filme filme = new Filme( 											//instancia um filme
	                Integer.parseInt(dados[0]),
	                dados[1],
	                dados[2],
	                dados[3],
	                dados[4],
	                Boolean.parseBoolean(dados[5])
	            );
	            this.catalogoFilmes.add(filme);										// adiciona o filme tirado do arquivo em catalogoFilmes
	        }
	        return true;
	    } catch (IOException e) {													//pega as exceções e printa na tela sem deixar o programa quebrar
	        e.printStackTrace();
	        return false;
	    }
	}

	
	public boolean carregarDadosUsuarios() { //Carrega os dados dos usuários
		try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))){ //Lê os dados do arquivo usuarios.txt
			String linha;
			while ((linha = br.readLine()) != null) {
				String[] dados = linha.split(";");
				
				Pessoa usuario = new Pessoa(										//instancia um usuário
						Integer.parseInt(dados[0]),
						dados[1],
						dados[2],
						dados[3],
						dados[4]
				);
				this.usuarios.add(usuario);											// adiciona o usuário tirado do arquivo em usuarios
			}
			return true;
		} catch (IOException e) {													//pega as exceções e printa na tela sem deixar o programa quebrar
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean salvarDadosFilme() { //Salva os dados dos filmes 
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("filmes.txt"))){		//Pega cada filme do ArrayList catalogoFilmes e salva de linha em linha no arquivo
			for (Filme filme : this.catalogoFilmes) {
				String linha = filme.getId() + ";" + filme.getNome() + ";" + filme.getGenero() + ";" +
							   filme.getDiretor() + ";" + filme.getDataLançamento() + ";" + filme.isDisponivel();
				bw.write(linha);
				bw.newLine();
			}
			return true;
		} catch (IOException e) {														//pega as exceções e printa na tela sem deixar o programa quebrar
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean salvarDadosUsuarios() { //Salva os dados dos usuários
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt"))){		//Pega cada usuario do ArrayList usuarios e salva de linha em linha no arquivo
			for (Pessoa usuario : this.usuarios) {
				String linha = usuario.getId() + ";" + usuario.getNome() + ";" + usuario.getEmail() + ";" +
							   usuario.getSenha() + ";" + usuario.getEndereco();
				
				bw.write(linha);
				bw.newLine();
			}
			return true;
		} catch (IOException e) {															//pega as exceções e printa na tela sem deixar o programa quebrar
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean adicionarFilme(Filme novo, Pessoa usuario) { //Adiciona um filme ao catálogo
		if (usuario instanceof Administrador) {					//Verifica se o usuário é um administrador
			this.catalogoFilmes.add(novo);
			return true;
		} else {
			return false;										//Retorna false se um cliente tentar adicionar um filme
		}
		
	}
	
	public boolean removerFilme(int id, Pessoa usuario) { //Remove um filme do catálogo
		if (usuario instanceof Administrador) {
			if (this.catalogoFilmes.size() == 0) {
				return false;							//Retorna false se não houver filmes para remover
			} else {
				int i=0;
				while (i < this.catalogoFilmes.size() && this.catalogoFilmes.get(i).getId() != id) {
					i++;
				}
				
				if (i == this.catalogoFilmes.size()) {
					return false;						//Retorna false se o filme não for encontrado
				} else {
					this.catalogoFilmes.remove(i);
					return true;						//Remove o filme e retorna true para avisar o usuário posteriormente
				}
			}
		} else {
			return false;								//Retorna false se o usuário não for um ADM
		}
	}

	public String listarFilmes() { // Retorna a lista completa de filmes
		return "Filmes: \n" + catalogoFilmes;
	}
	
	public int buscarFilme(String nome) { // Busca um filme pelo nome no catálogo
		for (int i=0; i <this.catalogoFilmes.size(); i++) {
			if (this.catalogoFilmes.get(i).getNome().equals(nome)) {
				return i;				//retorna o indice do filme para posteriormente mostrar as informações em tela
			}
		}
		return -1;						//se não encontra volta -1 para posteriormente mostrar que não foi encontrado
	}
	
}
