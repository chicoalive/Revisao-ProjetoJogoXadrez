package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Peao extends PecaXadrez {

// Colocando uma dependência para a partida por causa do movimento especial enpassant
    private PartidaXadrez partidaXadrez;

    public Peao(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
        super(tabuleiro, cor);
        this.partidaXadrez = partidaXadrez;
    }

    @Override
    public boolean[][] movimentosPossiveisMatriz() {
        boolean[][] matrizTemp = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

        Posicao posicaoAux = new Posicao(0, 0);

        // Movimentos possíveis peão branco. 
        if (getCor() == Cor.BRANCO) {
            posicaoAux.novoValores(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(posicaoAux) && !getTabuleiro().haPecas(posicaoAux)) {
                matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            }
            posicaoAux.novoValores(posicao.getLinha() - 2, posicao.getColuna());
            Posicao posicaoAux2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(posicaoAux) && !getTabuleiro().haPecas(posicaoAux) && getTabuleiro().posicaoExistente(posicaoAux2) && !getTabuleiro().haPecas(posicaoAux2) && getContagemMovimentos() == 0) {
                matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            }
            // Conferindo se tem peças a esquerda e direita do peão
            // Esquerda
            posicaoAux.novoValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExistente(posicaoAux) && haPecaAdversaria(posicaoAux)) {
                matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            }
            // Direita
            posicaoAux.novoValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExistente(posicaoAux) && haPecaAdversaria(posicaoAux)) {
                matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            }
            // Movimento especial en passant branco
            if (posicao.getLinha() == 3) {
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().posicaoExistente(esquerda) && haPecaAdversaria(esquerda) && getTabuleiro().pecas(esquerda) == partidaXadrez.getEnPassantVuneravel()) {
                    matrizTemp[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().posicaoExistente(direita) && haPecaAdversaria(direita) && getTabuleiro().pecas(direita) == partidaXadrez.getEnPassantVuneravel()) {
                    matrizTemp[direita.getLinha() - 1][direita.getColuna()] = true;
                }
            }

            // Movimentos possíveis peão preto
        } else {
            posicaoAux.novoValores(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(posicaoAux) && !getTabuleiro().haPecas(posicaoAux)) {
                matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            }
            posicaoAux.novoValores(posicao.getLinha() + 2, posicao.getColuna());
            Posicao posicaoAux2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
            if (getTabuleiro().posicaoExistente(posicaoAux) && !getTabuleiro().haPecas(posicaoAux) && getTabuleiro().posicaoExistente(posicaoAux2) && !getTabuleiro().haPecas(posicaoAux2) && getContagemMovimentos() == 0) {
                matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            }
            // Conferindo se tem peças a esquerda e direita do peão
            // Esquerda
            posicaoAux.novoValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
            if (getTabuleiro().posicaoExistente(posicaoAux) && haPecaAdversaria(posicaoAux)) {
                matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            }
            // Direita
            posicaoAux.novoValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
            if (getTabuleiro().posicaoExistente(posicaoAux) && haPecaAdversaria(posicaoAux)) {
                matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
            }
            
            // Movimento especial en passant Preta
            if (posicao.getLinha() == 4) {
                Posicao esquerda = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                if (getTabuleiro().posicaoExistente(esquerda) && haPecaAdversaria(esquerda) && getTabuleiro().pecas(esquerda) == partidaXadrez.getEnPassantVuneravel()) {
                    matrizTemp[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
                }
                Posicao direita = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                if (getTabuleiro().posicaoExistente(direita) && haPecaAdversaria(direita) && getTabuleiro().pecas(direita) == partidaXadrez.getEnPassantVuneravel()) {
                    matrizTemp[direita.getLinha() + 1][direita.getColuna()] = true;
                }
            }
        }

        return matrizTemp;
    }

    @Override
    public String toString() {
        return "P";
    }

}
