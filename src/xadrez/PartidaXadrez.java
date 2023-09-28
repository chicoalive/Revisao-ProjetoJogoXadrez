package xadrez;

import jogoTabuleiro.Tabuleiro;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;

    // Coração do nosso programa. Quem tem que saber a dimensão do jogo é essa classe.
    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
    }

    public PecaXadrez[][] getPecas() {
        PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
// Necessário criar uma dowcast para a matriz entender que se trata de peças de xadrez
                matriz[i][j] = (PecaXadrez) tabuleiro.pecas(i, j);

            }

        }
        return matriz;
    }

}
