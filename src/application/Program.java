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
		
		while (!partidaDeXadrez.getCheckMate()) {
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
				
				if(partidaDeXadrez.getPromovido() != null) {
					System.out.print("Selecione a peca na qual gostaria de ser promovido (B/C/T/Q): ");
					String letra = sc.nextLine().toUpperCase();
					while (!letra.equals("B") && !letra.equals("C") && !letra.equals("T") && !letra.equals("Q")) {
						System.out.print("Valor invalido! Selecione uma peca valida para ser promovido (B/C/T/Q): ");
						letra = sc.nextLine().toUpperCase();
					}
					partidaDeXadrez.substituirPecaPromovida(letra);
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
		InterfaceDeUsuario.limparTela();
		InterfaceDeUsuario.imprimirPartida(partidaDeXadrez, capturadas);
	}
}
