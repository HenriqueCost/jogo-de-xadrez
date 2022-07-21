package xadrez;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;

public abstract class PecaDeXadrez extends Peca {

	private Cor cor;
	private int quantidadeDeMovimentos;

	public PecaDeXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public int getQuantidadeDeMovimentos() {
		return quantidadeDeMovimentos;
	}
	
	public void aumentarQuantidadeDeMovimentos() {
		quantidadeDeMovimentos ++;
	}
	
	public void diminuirQuantidadeDeMovimentos() {
		quantidadeDeMovimentos --;
	}
	
	public PosicaoNoTabuleiro getPosicaoNoTabuleiro() {
		return PosicaoNoTabuleiro.dePosicao(posicao);
	}
	
	protected boolean haAlgumaPecaDoOponente(Posicao posicao) {
		PecaDeXadrez p = (PecaDeXadrez)getTabuleiro().peca(posicao);
		return p != null && p.getCor() != cor;
	}
}
