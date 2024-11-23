package Filmes;

import java.io.*;
import java.util.ArrayList;
import Pessoas.Pessoa;

public class Locadora {
    private ArrayList<Filme> catalogoFilmes = new ArrayList<>();
    private ArrayList<Pessoa> usuarios = new ArrayList<>();

    
    //Construtores
    public Locadora(ArrayList<Filme> catalogoFilmes, ArrayList<Pessoa> usuarios) {
		this.catalogoFilmes = catalogoFilmes;
		this.usuarios = usuarios;
	}
    
    public Locadora() {
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
    	File arquivoFilme = new File("filmes.txt");
    	
    	if (!arquivoFilme.exists()) {
    		try {
    			if (arquivoFilme.createNewFile()) {
    				System.out.println("Arquivo filmes.txt criado.");
    			} 
    		} catch (IOException e) {
    			System.err.println("Erro ao criar o arquivo filmes.txt: " + e.getMessage());
    			return false;
    		}
    	}
    	
        try (BufferedReader br = new BufferedReader(new FileReader("filmes.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Filme filme = new Filme(
                        Integer.parseInt(dados[0]),
                        dados[1],
                        dados[2],
                        dados[3],
                        dados[4],
                        Boolean.parseBoolean(dados[5]),
                        Double.parseDouble(dados[6])
                );
                this.catalogoFilmes.add(filme);
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao carregar os dados dos filmes: " + e.getMessage());
            return false;
        } catch (Exception e) {
        	System.err.println("Erro ao processar os dados dos filmes: " + e.getMessage());
        	return false;
        }
    }

    public boolean carregarDadosUsuarios() {
    	File arquivoUsuarios = new File("usuarios.txt");
    	
    	if (!arquivoUsuarios.exists()) {
    		try {
    			if (arquivoUsuarios.createNewFile()) {
    				System.out.println("Arquivo usuarios.txt criado.");
    			} 
    		} catch (IOException e) {
    			System.err.println("Erro ao criar o arquivo usuarios.txt: " + e.getMessage());
    			return false;
    		}
    	}
        try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Pessoa usuario = new Pessoa(
                        Integer.parseInt(dados[0]),
                        dados[1],
                        dados[2],
                        dados[3],
                        dados[4],
                        Boolean.parseBoolean(dados[5])
                );
                this.usuarios.add(usuario);
            }
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao carregar os dados dos usuários: " + e.getMessage());
            return false;
        } catch (Exception e) {
        	System.err.println("Erro ao processar os dados dos usuarios: " + e.getMessage());
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
                        usuario.getSenha() + ";" + usuario.getEndereco() + ";" + usuario.isAdmin();
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
    
    public boolean adicionarCliente(Pessoa cliente) { 
        if (cliente == null) {
        	return false;
        } else {
        	this.usuarios.add(cliente);
        	return true;
        }
    }

	public boolean removerFilme(String filme) { 			//Remove um filme do catálogo
		if (this.catalogoFilmes.size() == 0) {
			return false;							//Retorna false se não houver filmes para remover
		} else {
			int i=0;
			while (i < this.catalogoFilmes.size() && !this.catalogoFilmes.get(i).getNome().equals(filme)) {
				i++;
			}
			
			if (i == this.catalogoFilmes.size()) {
				return false;						//Retorna false se o filme não for encontrado
			} else {
				this.catalogoFilmes.remove(i);
				return true;						//Remove o filme e retorna true para avisar o usuário posteriormente
			}
		}
	}
	
	public boolean removerCliente(String nome) {
		if (this.usuarios.size() == 0) {
			return false;
		} else {
			int i=0;
			while (i < this.usuarios.size() && !this.usuarios.get(i).getNome().equalsIgnoreCase(nome)) {
				i++;
			}
			
			if (i == this.usuarios.size()) {
				return false;
			} else {
				this.usuarios.remove(i);
				return true;
			}
		}
	}

	public String listarFilmes() { 						//Retorna uma Lista completa com os filmes
	    StringBuilder lista = new StringBuilder();
	    for (Filme filme : this.catalogoFilmes) {
	        lista.append(filme.toString()).append("\n");
	    }
	    return lista.toString();
	}
	
	public String listarNomesFilmes() {
		StringBuilder lista = new StringBuilder();
		for (Filme filme : this.catalogoFilmes) {
			lista.append("- " + filme.getNome()).append("\n");
		}
		return lista.toString();
	}
	
	public String listarNomesClientes() {
		StringBuilder lista = new StringBuilder();
		for (Pessoa cliente : this.usuarios) {
			lista.append(cliente.getNome()).append("\n");
		}
		return lista.toString();
	}

	public Filme buscarFilme(String nome) { 					// Busca um filme pelo nome no catálogo
	    for (Filme filme : this.catalogoFilmes) {				// Usando um loop for-each para maior clareza
	        if (filme.getNome().equalsIgnoreCase(nome)) {		// Ignora diferenças entre maiúsculas e minúsculas
	            return filme; 									// Retorna o objeto Filme correspondente
	        }
	    }
	    return null; 											// Retorna null se o filme não for encontrado
	}

	
}
