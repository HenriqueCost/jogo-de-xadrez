package xadrez;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private Tabuleiro tabuleiro;
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		inicioDePartida();
	}
	
	public PecaDeXadrez[][] getPecas() {
		PecaDeXadrez[][] mat = new PecaDeXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i ++) {
			for (int j = 0; j < tabuleiro.getColunas(); j ++) {
				mat[i][j] = (PecaDeXadrez) tabuleiro.peca(i, j);
			}
		}
		
		return mat;
	}
	
	public PecaDeXadrez realizeMovimentoDeXadrez(PosicaoNoTabuleiro posicaoDeOrigem, PosicaoNoTabuleiro posicaoDeDestino) {
		Posicao origem = posicaoDeOrigem.paraPosicao();
		Posicao destino = posicaoDeDestino.paraPosicao();
		validandoPosicaoDeOrigem(origem);
		Peca pecaCapturada = facaUmMovimento(origem, destino);
		return (PecaDeXadrez)pecaCapturada;
	}
	
	private Peca facaUmMovimento(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.posicionarPeca(p, destino);
		return pecaCapturada;
	}
	
	private void validandoPosicaoDeOrigem(Posicao posicao) {
		if(!tabuleiro.haUmaPeca(posicao)) {
			throw new ExcessaoDeXadrez("Nao existe peca na posicao de origem");
		}
		if(!tabuleiro.peca(posicao).haAlgumMovimentoPossivel()) {
			throw new ExcessaoDeXadrez("Nao existe movimentos possiveis para a peca escolhida");
		}
	}
	
	private void posicionarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.posicionarPeca(peca, new PosicaoNoTabuleiro(coluna, linha).paraPosicao());
	}
	
	private void inicioDePartida() {
		posicionarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCA));
		posicionarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCA));
		posicionarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCA));
		posicionarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCA));
		posicionarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCA));
		posicionarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCA));
		
		posicionarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETA));
		posicionarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETA));
		posicionarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETA));
		posicionarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETA));
		posicionarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETA));
		posicionarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETA));
	}
}
