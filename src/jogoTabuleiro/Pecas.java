package jogoTabuleiro;

public class Pecas {

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

}
