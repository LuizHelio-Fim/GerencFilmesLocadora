package Pessoas;

public class Pessoa {
	private int id;
	private String nome;
	private String email;
	private String senha;
	private String endereco;
	private boolean isAdmin;
	
	//Construtores
	public Pessoa(int id, String nome, String email, String senha, String endereco, boolean isAdmin) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.endereco = endereco;
		this.isAdmin = isAdmin;
	}
	
	public Pessoa() {
		this.id = 0;
		this.nome = "";
		this.email = "";
		this.senha = "";
		this.endereco = "";
		this.isAdmin = false;
	}
	
	//Getters e Setters
	public int getId() {
		return id;
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
	
	public boolean isAdmin() {
		return isAdmin;
	}
	
	public boolean verificarSenha(String senha) {
		return this.senha.equals(senha);
	}
	
	@Override
	public String toString() {
		return "ID: " + id + "\nNome: " + nome + "\nEmail: "  + email
				+ "\nEndereço: " + endereco + "\nAdmin: " + (isAdmin ? "Sim" : "Não");
	}
	
	
}
