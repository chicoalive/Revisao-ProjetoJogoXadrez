package xadrez;

import jogoTabuleiro.Pecas;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public abstract class PecaXadrez extends Pecas {

// Atributo
    private Cor cor;

    // Construtor
    public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    // Apenas o get, pois não queremos que a cor da peça seja modificada. 
    public Cor getCor() {
        return cor;
    }

    // Método para verificar se a peça adversária é diferente  
    protected boolean haPecaAdversaria(Posicao posicao) {
        PecaXadrez pecaAdversaria = (PecaXadrez) getTabuleiro().pecas(posicao);
        return pecaAdversaria != null && pecaAdversaria.getCor() != cor;
    }
}
