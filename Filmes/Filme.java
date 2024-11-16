package Filmes;

public class Filme {
	private int id;
	private String nome;
	private String genero;
	private String diretor;
	private String dataLançamento;
	private boolean disponivel;
	
	//Construtores
	public Filme(int id, String nome, String genero, String diretor, String dataLançamento, boolean disponivel) {
		super();
		this.id = id;
		this.nome = nome;
		this.genero = genero;
		this.diretor = diretor;
		this.dataLançamento = dataLançamento;
		this.disponivel = disponivel;
	}
	
	public Filme() {
		super();
		this.id = 0;
		this.nome = "";
		this.genero = "";
		this.diretor = "";
		this.dataLançamento = "00/00/0000";
		this.disponivel = true;
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getDataLançamento() {
		return dataLançamento;
	}

	public void setDataLançamento(String dataLançamento) {
		this.dataLançamento = dataLançamento;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	@Override
	public String toString() {
		return "ID: " + id + "\nNome: " + nome + "\nGenero: " + genero + "\nDiretor: " + diretor
				+ "\n Lançamento: " + dataLançamento + "\n Disponivel: " + disponivel + "\n\n";
	}
}
