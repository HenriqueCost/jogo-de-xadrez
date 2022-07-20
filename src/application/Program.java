package application;

import java.util.Scanner;

import xadrez.PartidaDeXadrez;
import xadrez.PecaDeXadrez;
import xadrez.PosicaoNoTabuleiro;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PartidaDeXadrez partidaDeXadrez = new PartidaDeXadrez();
		
		while (true) {
			InterfaceDeUsuario.imprimirTabuleiro(partidaDeXadrez.getPecas());
			System.out.println();
			System.out.print("Origem: ");
			PosicaoNoTabuleiro origem = InterfaceDeUsuario.lerPosicaoNoTabuleiro(sc);
			
			System.out.println();
			System.out.print("Destino: ");
			PosicaoNoTabuleiro destino = InterfaceDeUsuario.lerPosicaoNoTabuleiro(sc);
			
			PecaDeXadrez pecaCapturada = partidaDeXadrez.realizeMovimentoDeXadrez(origem, destino);
		}
	}

}
