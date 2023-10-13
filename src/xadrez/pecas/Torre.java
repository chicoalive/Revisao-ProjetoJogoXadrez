package xadrez.pecas;

import jogoTabuleiro.Posicao;
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
        boolean[][] matrizTemp = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao posicaoAux = new Posicao(0, 0);

        // Acima
        posicaoAux.novoValores(posicao.getLinha() - 1, posicao.getColuna());
        while (getTabuleiro().posicaoExistente(posicaoAux) && !getTabuleiro().haPecas(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            posicaoAux.setLinha(posicaoAux.getLinha() - 1);
        }
        if (getTabuleiro().posicaoExistente(posicaoAux) && haPecaAdversaria(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Esquerda
        posicaoAux.novoValores(posicao.getLinha(), posicao.getColuna() - 1);
        while (getTabuleiro().posicaoExistente(posicaoAux) && !getTabuleiro().haPecas(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            posicaoAux.setColuna(posicaoAux.getColuna() - 1);
        }
        if (getTabuleiro().posicaoExistente(posicaoAux) && haPecaAdversaria(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Direita
        posicaoAux.novoValores(posicao.getLinha(), posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExistente(posicaoAux) && !getTabuleiro().haPecas(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            posicaoAux.setColuna(posicaoAux.getColuna() + 1);
        }
        if (getTabuleiro().posicaoExistente(posicaoAux) && haPecaAdversaria(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // abaixo
        posicaoAux.novoValores(posicao.getLinha() + 1, posicao.getColuna());
        while (getTabuleiro().posicaoExistente(posicaoAux) && !getTabuleiro().haPecas(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            posicaoAux.setLinha(posicaoAux.getLinha() + 1);
        }
        if (getTabuleiro().posicaoExistente(posicaoAux) && haPecaAdversaria(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
        return matrizTemp;
    }

}
