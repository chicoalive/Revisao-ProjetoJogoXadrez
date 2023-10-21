package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Cavalo extends PecaXadrez {

    public Cavalo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "C";
    }

    private boolean podeMover(Posicao posicao) {
        PecaXadrez posicaoAux = (PecaXadrez) getTabuleiro().pecas(posicao);
        return posicaoAux == null || posicaoAux.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveisMatriz() {
        boolean[][] matrizTemp = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao posicaoAux = new Posicao(0, 0);

        posicaoAux.novoValores(posicao.getLinha() - 1, posicao.getColuna()-2);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        posicaoAux.novoValores(posicao.getLinha() -2, posicao.getColuna()-1);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        posicaoAux.novoValores(posicao.getLinha()-2, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        posicaoAux.novoValores(posicao.getLinha()-1, posicao.getColuna() + 2);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        posicaoAux.novoValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        posicaoAux.novoValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        posicaoAux.novoValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        posicaoAux.novoValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        return matrizTemp;
    }

}
