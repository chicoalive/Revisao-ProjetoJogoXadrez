package jogoTabuleiro;

public abstract class Pecas {

    protected Posicao posicao;
    private Tabuleiro tabuleiro;

// Construtores
    public Pecas(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        posicao = null;
    }

// Apenas o get, pois não posso deixar que modifiquem meu tabuleiro
    protected Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    // Posiveis movimentos de uma peça 
    public abstract boolean[][] movimentosPossiveisMatriz();

    public boolean movimentosPossiveis(Posicao posicao) {
        return movimentosPossiveisMatriz()[posicao.getLinha()][posicao.getColuna()];
    }

    public boolean existeMovimentoPossivel() {
        boolean[][] matrizAuxiliar = movimentosPossiveisMatriz();
        for (int i = 0; i < matrizAuxiliar.length; i++) {
            for (int j = 0; j < matrizAuxiliar.length; j++) {
                if (matrizAuxiliar[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
