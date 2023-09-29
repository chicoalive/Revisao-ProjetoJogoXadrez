package xadrez;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;

    // Coração do nosso programa. Quem tem que saber a dimensão do jogo é essa classe.
    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        configInicial();
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
// Inicio da partida. Para testar eu preciso chamar configInicial no construto da partida. 
  private void configInicial() {
      tabuleiro.lugarPecas(new Torre(tabuleiro, Cor.BRANCO ), new Posicao(2, 1));
      tabuleiro.lugarPecas(new Rei(tabuleiro, Cor.PRETO ), new Posicao(0, 4));
      tabuleiro.lugarPecas(new Rei(tabuleiro, Cor.BRANCO ), new Posicao(7, 4));
  }   

}
