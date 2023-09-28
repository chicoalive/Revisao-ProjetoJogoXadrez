package xadrez;

import jogoTabuleiro.Pecas;
import jogoTabuleiro.Tabuleiro;

public class PecaXadrez extends Pecas {

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
    
 
}
