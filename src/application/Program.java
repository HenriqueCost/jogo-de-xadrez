package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import xadrez.ExcessaoDeXadrez;
import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoNoTabuleiro;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		List<PecaDeXadrez> capturadas = new ArrayList<>();
		
		while (true) {
			try {
				InterfaceDeUsuario.limparTela();
				InterfaceDeUsuario.imprimirPartida(partidaDeXadrez, capturadas);
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
				
				if(pecaCapturada != null) {
					capturadas.add(pecaCapturada);
				}
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
