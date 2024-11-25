package Filmes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Pessoas.Administrador;
import Pessoas.Cliente;
import Pessoas.Pessoa;

public class Locadora {
    private ArrayList<Filme> catalogoFilmes = new ArrayList<>();
    private ArrayList<Pessoa> usuarios = new ArrayList<>();
    private ArrayList<String> historicoLocacoes = new ArrayList<>();
    
    //Construtores
    public Locadora(ArrayList<Filme> catalogoFilmes, ArrayList<Pessoa> usuarios, ArrayList<String> historicoLocacoes) {
		this.catalogoFilmes = catalogoFilmes;
		this.usuarios = usuarios;
		this.historicoLocacoes = historicoLocacoes;
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
                
                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                String email = dados[2];
                String senha = dados[3];
                String endereco = dados[4];
                boolean isAdmin = Boolean.parseBoolean(dados[5]);

                Pessoa usuario;
                if (isAdmin) {
                    usuario = new Administrador(id, nome, email, senha, endereco, true);
                } else {
                    usuario = new Cliente(id, nome, email, senha, endereco, false);
                }
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
                        filme.getDiretor() + ";" + filme.getDataLancamentoFormatada() + ";" + "true" + ";" + filme.getPreco();
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
    	carregarHistoricoLocacoes();
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

	public String listarFilmes() { 						//exibe todos os filmes da locadora
	    StringBuilder lista = new StringBuilder();
	    for (Filme filme : this.catalogoFilmes) {
	        lista.append(filme.toString()).append("\n");
	    }
	    return lista.toString();
	}
	
	public String listarFilmesDisponiveis() { 						//exibe apenas os filmes que não estão alugados
	    StringBuilder lista = new StringBuilder();
	    for (Filme filme : this.catalogoFilmes) {
	    	if (filme.isDisponivel()) {
	    		lista.append("- " + filme.getNome() + ", diaria: " + filme.getPreco()).append("\n");
	    	}
	     
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
			lista.append("- " + cliente.getNome()).append("\n");
		}
		return lista.toString();
	}

	public Filme buscarFilme(String nome) { 					
	    for (Filme filme : this.catalogoFilmes) {				
	        if (filme.getNome().equalsIgnoreCase(nome)) {		
	            return filme; 									
	        }
	    }
	    return null; 											
	}

	public void carregarHistoricoLocacoes() { 
	    File arquivoLocacoes = new File("historicoLocacoes.txt");
	    
	    historicoLocacoes.clear();

	    if (!arquivoLocacoes.exists()) {
	        try {
	            if (arquivoLocacoes.createNewFile()) {
	                System.out.println("Arquivo historicoLocacoes.txt criado.");
	            }
	        } catch (IOException e) {
	            System.err.println("Erro ao criar o arquivo historicoLocacoes.txt: " + e.getMessage());
	            return;
	        }
	    }
	    
	    try (BufferedReader br = new BufferedReader(new FileReader(arquivoLocacoes))) {
	        String linha;
	        while ((linha = br.readLine()) != null) {
	            historicoLocacoes.add(linha);
	        }
	       
	    } catch (IOException e) {
	        System.err.println("Erro ao carregar os dados do histórico de locações: " + e.getMessage());
	    }
	}



}