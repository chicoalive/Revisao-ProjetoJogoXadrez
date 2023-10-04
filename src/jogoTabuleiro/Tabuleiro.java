package jogoTabuleiro;

public class Tabuleiro {

    private int linhas;
    private int colunas;
// Criando uma matriz de peças
    private Pecas[][] pecasMatriz;

    public Tabuleiro(int linhas, int colunas) {
        // Aplicando a programação defensiva
        if (linhas < 1 || colunas < 1) {
            throw new TabuleiroExecao("Erro ao criar o tabuleiro: deve haver pelo menos 1 linha e 1 coluna.");
        }
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

    /*
    Uma vez iniciado o tabuleiro, não podemos deixar que modifiquem o número de linhas e colunas. 
    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }

    public void setColunas(int colunas) {
        this.colunas = colunas;
    } */
// Métodos 
    public Pecas pecas(int linha, int coluna) {
        // Aplicando a programação defensiva
        if (!posicaoExistente(linha, coluna)) {
            throw new TabuleiroExecao("Posição não existe no tabuleiro.");
        }
        return pecasMatriz[linha][coluna];
    }

    public Pecas pecas(Posicao posicao) {
        // Aplicando a programação defensiva
        if (!posicaoExistente(posicao)) {
            throw new TabuleiroExecao("Posição não existe no tabuleiro.");
        }
        return pecasMatriz[posicao.getLinha()][posicao.getColuna()];
    }
// Método para impressão das peças. O método vai na matriz da peça em x linha e x coluna e atribui a posição a peça que vira como argumento.

    public void lugarPecas(Pecas peca, Posicao posicao) {
        // Testando se já tem peça na posição
        if (haPecas(posicao)) {
            throw new TabuleiroExecao("Já existe uma peça nessa posição: " + posicao);
        }
        pecasMatriz[posicao.getLinha()][posicao.getColuna()] = peca;
        /* Preciso informar que a peça não está mais na posição nula. A posição da peça é acessada diretamente,
        pois na classe pecas colocamos o atribuito protected e como a classe tabuleiro é do mesmo pacote, eu consigo acessar livremente a posição dela. */
        peca.posicao = posicao;
    }

    // Método para remover uma peça
    public Pecas removPeca(Posicao posicao) {
        if (!posicaoExistente(posicao)) {
            throw new TabuleiroExecao("Já existe uma peça nessa posição: " + posicao);
        }
        if (pecas(posicao)== null) {
            return null;
        }
        Pecas auxPecas = pecas(posicao);
        auxPecas.posicao = null;
        pecasMatriz[posicao.getLinha()][posicao.getColuna()] = null;
        return auxPecas;
    }

// Verificando a existência de posições e peças.
    // Método auxiliar para o posicaoExistente.
    private boolean posicaoExistente(int linha, int coluna) {
        return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
    }

    public boolean posicaoExistente(Posicao posicao) {
        return posicaoExistente(posicao.getLinha(), posicao.getColuna());
    }

    // Verificar se na posição já existe uma peça. 
    public boolean haPecas(Posicao posicao) {
        // Aplicando a programação defensiva
        if (!posicaoExistente(posicao)) {
            throw new TabuleiroExecao("Posição não existe no tabuleiro.");
        }
        return pecas(posicao) != null;

    }
}
