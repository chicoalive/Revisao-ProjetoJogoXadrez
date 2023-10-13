package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

    public Rei(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }

    @Override
    public String toString() {
        return "R ";
    }

    private boolean podeMover(Posicao posicao) {
        PecaXadrez posicaoAux = (PecaXadrez) getTabuleiro().pecas(posicao);
        return posicaoAux == null || posicaoAux.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveisMatriz() {
        boolean[][] matrizTemp = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        // Possíveis movimentos do Rei.
        Posicao posicaoAux = new Posicao(0, 0);

        // Norte - Acima
        posicaoAux.novoValores(posicao.getLinha() - 1, posicao.getColuna());
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Sul - Abaixo
        posicaoAux.novoValores(posicao.getLinha() + 1, posicao.getColuna());
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Oeste - Esquerda
        posicaoAux.novoValores(posicao.getLinha(), posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Leste - Direita
        posicaoAux.novoValores(posicao.getLinha(), posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Noroeste - Cima + Esquerda
        posicaoAux.novoValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Nordeste - Cima + Direita
        posicaoAux.novoValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }
        
         // Sudoeste - Baixo + Direita
        posicaoAux.novoValores(posicao.getLinha()+ 1, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Sudeste - Baixo + Esquerda
        posicaoAux.novoValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        return matrizTemp;
    }

}
