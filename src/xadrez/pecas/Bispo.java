package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Bispo extends PecaXadrez {

    public Bispo(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
// Converter uma torre para string
        return "B ";
    }

    @Override
    public boolean[][] movimentosPossiveisMatriz() {
        boolean[][] matrizTemp = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao posicaoAux = new Posicao(0, 0);

        // Noroeste 
        posicaoAux.novoValores(posicao.getLinha() - 1, posicao.getColuna()-1);
        while (getTabuleiro().posicaoExistente(posicaoAux) && !getTabuleiro().haPecas(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            posicaoAux.novoValores(posicaoAux.getLinha()-1, posicaoAux.getColuna()-1);
        }
        if (getTabuleiro().posicaoExistente(posicaoAux) && haPecaAdversaria(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Nordeste
        posicaoAux.novoValores(posicao.getLinha()-1, posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExistente(posicaoAux) && !getTabuleiro().haPecas(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            posicaoAux.novoValores(posicaoAux.getLinha()-1, posicaoAux.getColuna()+1);
        }
        if (getTabuleiro().posicaoExistente(posicaoAux) && haPecaAdversaria(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Sudeste
        posicaoAux.novoValores(posicao.getLinha()+1, posicao.getColuna() + 1);
        while (getTabuleiro().posicaoExistente(posicaoAux) && !getTabuleiro().haPecas(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
             posicaoAux.novoValores(posicaoAux.getLinha()+1, posicaoAux.getColuna()+1);
        }
        if (getTabuleiro().posicaoExistente(posicaoAux) && haPecaAdversaria(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Sudoeste
        posicaoAux.novoValores(posicao.getLinha() + 1, posicao.getColuna()-1);
        while (getTabuleiro().posicaoExistente(posicaoAux) && !getTabuleiro().haPecas(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            posicaoAux.novoValores(posicaoAux.getLinha()+1, posicaoAux.getColuna()-1);
        }
        if (getTabuleiro().posicaoExistente(posicaoAux) && haPecaAdversaria(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
        return matrizTemp;
    }
}
