package Filmes;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import Pessoas.Administrador;
import Pessoas.Pessoa;

public class Locadora {
    private ArrayList<Filme> catalogoFilmes = new ArrayList<>();
    private ArrayList<Pessoa> usuarios = new ArrayList<>();

    
    //Construtor
    public Locadora(ArrayList<Filme> catalogoFilmes, ArrayList<Pessoa> usuarios) {
		this.catalogoFilmes = catalogoFilmes;
		this.usuarios = usuarios;
	}
    
    
    //Getters e Setters
	public ArrayList<Filme> getCatalogoFilmes() {
		return catalogoFilmes;
	}

	public void setCatalogoFilmes(ArrayList<Filme> catalogoFilmes) {
		this.catalogoFilmes = catalogoFilmes;
	}

	public ArrayList<Pessoa> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Pessoa> usuarios) {
		this.usuarios = usuarios;
	}

	// Métodos de carregamento
    public boolean carregarDadosFilme() {
        try (BufferedReader br = new BufferedReader(new FileReader("filmes.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Filme filme = new Filme(
                        Integer.parseInt(dados[0]),
                        dados[1],
                        dados[2],
                        dados[3],
                        LocalDate.parse(dados[4], Filme.FORMATTER),
                        Boolean.parseBoolean(dados[5])
                );
                this.catalogoFilmes.add(filme);
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao carregar os dados dos filmes.");
            return false;
        }
    }

    public boolean carregarDadosUsuarios() {
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Pessoa usuario = new Pessoa(
                        Integer.parseInt(dados[0]),
                        dados[1],
                        dados[2],
                        dados[3],
                        dados[4]
                );
                this.usuarios.add(usuario);
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao carregar os dados dos usuários.");
            return false;
        }
    }

    // Métodos de salvamento
    public boolean salvarDadosFilme() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("filmes.txt"))) {
            for (Filme filme : this.catalogoFilmes) {
                String linha = filme.getId() + ";" + filme.getNome() + ";" + filme.getGenero() + ";" +
                        filme.getDiretor() + ";" + filme.getDataLancamentoFormatada() + ";" + filme.isDisponivel();
                bw.write(linha);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados dos filmes.");
            return false;
        }
    }

    public boolean salvarDadosUsuarios() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("usuarios.txt"))) {
            for (Pessoa usuario : this.usuarios) {
                String linha = usuario.getId() + ";" + usuario.getNome() + ";" + usuario.getEmail() + ";" +
                        usuario.getSenha() + ";" + usuario.getEndereco();
                bw.write(linha);
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados dos usuários.");
            return false;
        }
    }
    
    
    //métodos simplificados para o ProgramaPrincipal
    public void iniciar() {
    	carregarDadosFilme();
    	carregarDadosUsuarios();
    }

    public void finalizar() {
        salvarDadosFilme();
        salvarDadosUsuarios();
    }
	
    public boolean adicionarFilme(Filme filme) { //Adicionar um filme ao catálogo
        if (filme == null) {
        	return false;
        } else {
        	this.catalogoFilmes.add(filme);
        	return true;
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

	public String listarFilmes() { 						//Retorna uma Lista completa com os filmes
	    StringBuilder lista = new StringBuilder();
	    for (Filme filme : this.catalogoFilmes) {
	        lista.append(filme.toString()).append("\n");
	    }
	    return lista.toString();
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
