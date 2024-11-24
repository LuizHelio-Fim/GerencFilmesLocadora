package Filmes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
		long diasAtraso = ChronoUnit.DAYS.between(locacao.getDataDevolucaoPrevista(), dataPagamento);
		if (diasAtraso > 0) {
			return diasAtraso * 5;			// R$5 de multa por dia
		}
		return 0;							// sem multa
	}
	
	public double calculaValorFinal () {
		long periodoLocado = ChronoUnit.DAYS.between(locacao.getDataLocacao(), dataPagamento);
		
		if (periodoLocado < 0) {
			periodoLocado = 0;			// evitar valores negativos
		}
		double preco = periodoLocado * locacao.getFilme().getPreco();
		double multa = calcularMultaPorAtraso();
		
		return preco + multa;
	}
	
	public boolean processarPagamento(double valor) {
		System.out.println("Processando...");
		double valorFinal = calculaValorFinal();
		
		if (Math.abs(valor - valorFinal) < 0.01) {
			System.out.println("Pagamento confirmado!");
			this.status = true;
			return true;
		}
		System.out.println("[ERRO] - Pagamento negado!");
		this.status = false;
		return false;
	}
}
