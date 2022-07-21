package xadrez;

import jogoDeTabuleiro.Posicao;

public class PosicaoNoTabuleiro {

	private char coluna;
	private int linha;
	
	public PosicaoNoTabuleiro(char coluna, int linha) {
		if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
			throw new ExcessaoDeXadrez("Erro instanciando PosicaoNoTabuleiro. Valores validos sao de a1 ate h8.");
		}
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}

	protected Posicao paraPosicao() {
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	protected static PosicaoNoTabuleiro dePosicao(Posicao posicao) {
		return new PosicaoNoTabuleiro((char)('a' + posicao.getColuna()), 8 - posicao.getLinha());
	}
	
	@Override
	public String toString() {
		return "" + coluna + linha;
	}
}
