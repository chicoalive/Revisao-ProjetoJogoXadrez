package jogoTabuleiro;

public class Posicao {

    private int linha;
    private int coluna;

// Construtores
    public Posicao() {
    }

    public Posicao(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }
//Get e Set

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
// Imprimindo uma posição na tela. 

    @Override
    public String toString() {
        return linha + ", " + coluna;
    }

    
    
}
