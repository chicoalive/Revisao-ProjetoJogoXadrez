package jogoTabuleiro;

public class Tabuleiro {

    private int linhas;
    private int colunas;
// Criando uma matriz de peças
    private Pecas[][] pecas;

    public Tabuleiro(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
// Completando manualmente. A matriz de peças será instanciada com peças na quantidade de linhas e colunas informadas
        pecas = new Pecas[linhas][colunas];

// Get e Set
    }

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

// Metodos 
    public Pecas pecas(int linha, int coluna) {
        return pecas[linha][coluna];
    }

    public Pecas pecas(Posicao posicao) {
        return pecas[posicao.getLinha()][posicao.getColuna()];
    }
}
