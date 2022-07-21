package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import xadrez.ExcessaoDeXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoNoTabuleiro;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while (true) {
			try {
				InterfaceDeUsuario.limparTela();
				InterfaceDeUsuario.imprimirTabuleiro(partidaDeXadrez.getPecas());
				System.out.println();
				System.out.print("Origem: ");
				PosicaoNoTabuleiro origem = InterfaceDeUsuario.lerPosicaoNoTabuleiro(sc);
			
				boolean[][] movimentosPossiveis = partidaDeXadrez.movimentoPossiveis(origem);
				InterfaceDeUsuario.limparTela();
				InterfaceDeUsuario.imprimirTabuleiro(partidaDeXadrez.getPecas(), movimentosPossiveis);
				System.out.println();
				System.out.print("Destino: ");
				PosicaoNoTabuleiro destino = InterfaceDeUsuario.lerPosicaoNoTabuleiro(sc);
			
				PecaDeXadrez pecaCapturada = partidaDeXadrez.realizeMovimentoDeXadrez(origem, destino);
			}
			catch (ExcessaoDeXadrez e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
	}

}
