package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jogoDeTabuleiro.Peca;
import jogoDeTabuleiro.Posicao;
import jogoDeTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaDeXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean check;
	
	private List<Peca> pecasNoTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	
	public PartidaDeXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCA;
		inicioDePartida();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getCheck() {
		return check;
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
	
	public boolean[][] movimentoPossiveis(PosicaoNoTabuleiro posicaoDeOrigem){
		Posicao posicao = posicaoDeOrigem.paraPosicao();
		validandoPosicaoDeOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}
	
	public PecaDeXadrez realizeMovimentoDeXadrez(PosicaoNoTabuleiro posicaoDeOrigem, PosicaoNoTabuleiro posicaoDeDestino) {
		Posicao origem = posicaoDeOrigem.paraPosicao();
		Posicao destino = posicaoDeDestino.paraPosicao();
		validandoPosicaoDeOrigem(origem);
		validandoPosicaoDeDestino(origem, destino);
		Peca pecaCapturada = facaUmMovimento(origem, destino);
		
		if (testeDeCheck(jogadorAtual)) {
			desfacaUmMovimento(origem, destino, pecaCapturada);
			throw new ExcessaoDeXadrez("Voce nao pode se colocar em check!");
		}
		
		check = (testeDeCheck(oponente(jogadorAtual))) ? true : false;
		
		proximoTurno();
		return (PecaDeXadrez)pecaCapturada;
	}
	
	private Peca facaUmMovimento(Posicao origem, Posicao destino) {
		Peca p = tabuleiro.removerPeca(origem);
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.posicionarPeca(p, destino);
		
		if (pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		return pecaCapturada;
	}
	
	private void desfacaUmMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		Peca p = tabuleiro.removerPeca(destino);
		tabuleiro.posicionarPeca(p, origem);
		
		if (pecaCapturada != null) {
			tabuleiro.posicionarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	
	private void validandoPosicaoDeOrigem(Posicao posicao) {
		if(!tabuleiro.haUmaPeca(posicao)) {
			throw new ExcessaoDeXadrez("Nao existe peca na posicao de origem.");
		}
		if(jogadorAtual != ((PecaDeXadrez)tabuleiro.peca(posicao)).getCor()) {
			throw new ExcessaoDeXadrez("A peca escolhida nao eh sua.");
		}
		if(!tabuleiro.peca(posicao).haAlgumMovimentoPossivel()) {
			throw new ExcessaoDeXadrez("Nao existe movimentos possiveis para a peca escolhida.");
		}
	}
	
	private void validandoPosicaoDeDestino(Posicao origem, Posicao destino) {
		if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
			throw new ExcessaoDeXadrez("A peca escolhida nao pode se mover para a posicao de destino.");
		}
	}
	
	private void proximoTurno() {
		turno ++;
		jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private Cor oponente(Cor cor) {
		return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private PecaDeXadrez rei(Cor cor) {
		List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p : lista) {
			if (p instanceof Rei) {
				return (PecaDeXadrez)p;
			}
		}
		throw new IllegalStateException("Nao ha um rei na cor " + cor + " no tabuleiro.");
	}
	
	private boolean testeDeCheck(Cor cor) {
		Posicao posicaoDoRei = rei(cor).getPosicaoNoTabuleiro().paraPosicao();
		List<Peca> pecasDoOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaDeXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
		for (Peca p : pecasDoOponente) {
			boolean[][] mat = p.movimentosPossiveis();
			if (mat[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private void posicionarNovaPeca(char coluna, int linha, PecaDeXadrez peca) {
		tabuleiro.posicionarPeca(peca, new PosicaoNoTabuleiro(coluna, linha).paraPosicao());
		pecasNoTabuleiro.add(peca);
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
