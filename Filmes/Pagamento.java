package Filmes;

import java.time.LocalDate;
import Pessoas.Cliente;

public class Pagamento {
	
	Locacao locacao;
	double valor;
	Cliente cliente; 
	LocalDate dataPagamento;
	boolean status;
	
	public Pagamento(Locacao locacao, LocalDate dataPagamento) {

		this.locacao = locacao;
		this.dataPagamento = dataPagamento;
	}

	public double calcularMultaPorAtraso ( ){
		
		int multa = (dataPagamento.getDayOfYear() -  this.locacao.getDataDevolucaoPrevista().getDayOfYear()) * 5;
		return multa;
	}
	
	public double calculaValorFinal () {
		
		int periodoLocado = locacao.getDataDevolucaoPrevista().getDayOfYear() - locacao.getDataLocacao().getDayOfYear();
		
		double preco = periodoLocado * this.locacao.getFilme().getPreco();
		
		double multa = calcularMultaPorAtraso();
		
		double precoFinal = preco + multa;
		
		return precoFinal;
		
	}
	
	public boolean processarPagamento(double valor) {
		System.out.println("Processando...");
		if (valor == calculaValorFinal()) {
			System.out.println("Pagamento confirmado!");
			return true;
		}
		System.out.println("[ERRO] - Pagamento negado!");
		return false;
	}
}
