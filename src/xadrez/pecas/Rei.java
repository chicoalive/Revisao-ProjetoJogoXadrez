package xadrez.pecas;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

    //Dependência para a partida, jogada especial Roque
    private PartidaXadrez partidaXadrez;

    public Rei(Tabuleiro tabuleiro, Cor cor, PartidaXadrez partidaXadrez) {
        super(tabuleiro, cor);
        this.partidaXadrez = partidaXadrez;
    }

    @Override
    public String toString() {
        return "R";
    }

    // Informar se nessa posição existe uma torre e se ela estará habita a realizar o roque.
    private boolean testTorreRoque(Posicao posicao) {
        PecaXadrez p = (PecaXadrez) getTabuleiro().pecas(posicao);
        return p != null && p instanceof Torre && p.getCor() == getCor() && p.getContagemMovimentos() == 0;
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
        posicaoAux.novoValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Sudeste - Baixo + Esquerda
        posicaoAux.novoValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExistente(posicaoAux) && podeMover(posicaoAux)) {
            matrizTemp[posicaoAux.getLinha()][posicaoAux.getColuna()] = true;
        }

        // Movimento especial Roque
        if (getContagemMovimentos() == 0 && !partidaXadrez.getCheck()) {
            // Roque lado do Rei
            Posicao posicaoTorreRei = new Posicao(posicao.getLinha(), posicao.getColuna() + 3);
            if (testTorreRoque(posicaoTorreRei)) {
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() + 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() + 2);
                if (getTabuleiro().pecas(p1) == null && getTabuleiro().pecas(p2) == null) {
                    matrizTemp[posicao.getLinha()][posicao.getColuna() + 2] = true;
                }
            }
            // Roque lado do Rainha
            Posicao posicaoTorreRainha = new Posicao(posicao.getLinha(), posicao.getColuna() - 4);
            if (testTorreRoque(posicaoTorreRainha)) {
                Posicao p1 = new Posicao(posicao.getLinha(), posicao.getColuna() - 1);
                Posicao p2 = new Posicao(posicao.getLinha(), posicao.getColuna() - 2);
                Posicao p3 = new Posicao(posicao.getLinha(), posicao.getColuna() - 3);
                if (getTabuleiro().pecas(p1) == null && getTabuleiro().pecas(p2) == null && getTabuleiro().pecas(p3) == null) {
                    matrizTemp[posicao.getLinha()][posicao.getColuna() - 2] = true;
                }
            }
        }

        return matrizTemp;
    }

}
