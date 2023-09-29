package jogoTabuleiro;

public class Tabuleiro {

    private int linhas;
    private int colunas;
// Criando uma matriz de peças
    private Pecas[][] pecasMatriz;

    public Tabuleiro(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
// Completando manualmente. A matriz de peças será instanciada com peças na quantidade de linhas e colunas informadas
        pecasMatriz = new Pecas[linhas][colunas];

    }

// Get e Set
    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    }

// Métodos 
    public Pecas pecas(int linha, int coluna) {
        return pecasMatriz[linha][coluna];
    }

    public Pecas pecas(Posicao posicao) {
        return pecasMatriz[posicao.getLinha()][posicao.getColuna()];
    }
// Método para impressão das peças. O método vai na matriz da peça em x linha e x coluna e atribui a posição a peça que vira como argumento.

    public void lugarPecas(Pecas peca, Posicao posicao) {
        pecasMatriz[posicao.getLinha()][posicao.getColuna()] = peca;
        /* Preciso informar que a peça não está mais na posição nula. A posição da peça é acessada diretamente,
        pois na classe pecas colocamos o atribuito protected e como a classe tabuleiro é do mesmo pacote, eu consigo acessar livremente a posição dela. */
        peca.posicao = posicao;
    }

}
