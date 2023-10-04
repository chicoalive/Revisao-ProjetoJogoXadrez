package aplicacao;

import java.util.Scanner;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.XadrezPosicao;

public class Programa {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        PartidaXadrez partidaXadrez = new PartidaXadrez();

        while (true) {
            UI.imprimirTabuleiro(partidaXadrez.getPecas());
            System.out.println("");
            System.out.print("Posição de origem da peça: ");
            XadrezPosicao origem = UI.leiaXadrezPosicao(teclado);
            System.out.println("");
            System.out.print("Posição de destino da peça: ");
            XadrezPosicao destino = UI.leiaXadrezPosicao(teclado);
            PecaXadrez pecaCapturada = partidaXadrez.movimentoPecaXadrez(origem, destino);
        }
    }
}
