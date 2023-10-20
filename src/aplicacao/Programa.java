package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
        List<PecaXadrez> capturada = new ArrayList<>();
        
        while (!partidaXadrez.getCheckMate()) {
            try {
                // limpando a tela
                UI.limparTela();
                UI.imprimirPartida(partidaXadrez, capturada);
                System.out.println("");
                System.out.print("Posição de origem da peça: ");
                XadrezPosicao origem = UI.leiaXadrezPosicao(teclado);
                
                boolean[][] movimentosPossiveis = partidaXadrez.possivelMovimento(origem);
                UI.limparTela();
                // Sobrecarga
                UI.imprimirTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);
                System.out.println("");
                System.out.print("Posição de destino da peça: ");
                XadrezPosicao destino = UI.leiaXadrezPosicao(teclado);
                PecaXadrez pecaCapturada = partidaXadrez.movimentoPecaXadrez(origem, destino);
                if (pecaCapturada != null) {
                    capturada.add(pecaCapturada);
                }
            } catch (XadrezExcecao erro) {
                System.out.println(erro.getMessage());
                teclado.nextLine();
            } catch (InputMismatchException erro) {
                System.out.println(erro.getMessage());
                teclado.nextLine();
            }
        }
        UI.limparTela();
        UI.imprimirPartida(partidaXadrez, capturada);
    }
}
