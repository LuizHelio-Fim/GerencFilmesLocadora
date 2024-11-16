package Pessoas;

public class Pessoa {
	private int id;
	private String nome;
	private String email;
	private String senha;
	private String endereco;
	
	//Construtores
	public Pessoa(int id, String nome, String email, String senha, String endereco) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
	}
	
	public Pessoa() {
		this.id = 0;
		this.nome = "";
		this.email = "";
		this.senha = "";
		this.endereco = "";
	}
	
	//Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	
	@Override
	public String toString() {
		return "ID: " + id + "\nNome: " + nome + "\nEmail: "  + email 
				+ "\nSenha: " + senha + "\nEndereço: " + endereco;
	}
	
	
}
