package aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.XadrezExcecao;
import xadrez.XadrezPosicao;

public class Programa {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        PartidaXadrez partidaXadrez = new PartidaXadrez();

        while (true) {
            try {
                // limpando a tela
                UI.limparTela();
                UI.imprimirTabuleiro(partidaXadrez.getPecas());
                System.out.println("");
                System.out.print("Posição de origem da peça: ");
                XadrezPosicao origem = UI.leiaXadrezPosicao(teclado);
                System.out.println("");
                System.out.print("Posição de destino da peça: ");
                XadrezPosicao destino = UI.leiaXadrezPosicao(teclado);
                PecaXadrez pecaCapturada = partidaXadrez.movimentoPecaXadrez(origem, destino);
            } catch (XadrezExcecao erro) {
                System.out.println(erro.getMessage());
                teclado.nextLine();
            } catch (InputMismatchException erro) {
                System.out.println(erro.getMessage());
                teclado.nextLine();
            }
        }

    }
}
