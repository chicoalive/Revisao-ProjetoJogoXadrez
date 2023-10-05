package xadrez.pecas;

import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Torre extends PecaXadrez {

    public Torre(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
// Converter uma torre para string
        return "T ";
    }

    @Override
    public boolean[][] movimentosPossiveisMatriz() {
         // Provisório
        boolean [][] matrizTemp = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        return matrizTemp;
    }

}
