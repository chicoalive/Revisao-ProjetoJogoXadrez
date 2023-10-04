package xadrez;

import jogoTabuleiro.Posicao;

public class XadrezPosicao  {

    private char coluna;
    private int linha;

// Construtores    
    public XadrezPosicao(char coluna, int linha) {
        // Programação defensiva
        if (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
            throw new XadrezExcecao("Erro ao instanciar a posição de xadrez: Os valores válidos são de a1 a h8");
        }
        this.coluna = coluna;
        this.linha = linha;
    }
// Get e Set - Pegar e modificar

    public char getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }

    /* Não podemos deixar que coluna e linha sejam livremente modificadas. 
    public void setColuna(char coluna) {
        this.coluna = coluna;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }
     */
// Métodos.
    protected Posicao dePosicao() {
        return new Posicao(8 - linha, coluna - 'a');
    }

    protected static XadrezPosicao dePosicao(Posicao posicao) {
        return new XadrezPosicao((char) ('a' - posicao.getColuna()), 8 - posicao.getLinha());
    }

    @Override
    public String toString() {
        return "" + coluna + linha;
    }

}
