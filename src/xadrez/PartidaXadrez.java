package xadrez;

import jogoTabuleiro.Pecas;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;

    // Coração do nosso programa. Quem tem que saber a dimensão do jogo é essa classe.
    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        configInicial();
    }

    public PecaXadrez[][] getPecas() {
        PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
// Necessário criar uma dowcast para a matriz entender que se trata de peças de xadrez
                matriz[i][j] = (PecaXadrez) tabuleiro.pecas(i, j);
            }
        }
        return matriz;
    }

    public PecaXadrez movimentoPecaXadrez(XadrezPosicao posicaoOrigem, XadrezPosicao posicaoDestino) {
// Convertendo para posições da matriz. 
        Posicao origem = posicaoOrigem.dePosicao();
        Posicao destino = posicaoDestino.dePosicao();
        validarPosicaoOrigem(origem);
        validarposicaoDestino(origem, destino);
// Necessário criar um dowcast para peça xadrez
        Pecas pecaCapturada = fazerMovimento(origem, destino);
        return (PecaXadrez) pecaCapturada;
    }

    private Pecas fazerMovimento(Posicao origem, Posicao destino) {
        Pecas p = tabuleiro.removPeca(origem);
        Pecas pecaCapturada = tabuleiro.removPeca(destino);
        tabuleiro.lugarPecas(p, destino);
        return pecaCapturada;
    }

    private void validarPosicaoOrigem(Posicao posicao) {
// Se não existir uma peça nessa posição, o código lançara uma exceção.
        if (!tabuleiro.haPecas(posicao)) {
            throw new XadrezExcecao("Não existe peça na posição de origem");
        }
        if (!tabuleiro.pecas(posicao).existeMovimentoPossivel()) {
            throw new XadrezExcecao("Não há movimentos possivéis para a peça");
        }
    }

    private void validarposicaoDestino(Posicao origem, Posicao destino) {
        if (!tabuleiro.pecas(origem).movimentosPossiveis(destino)) {
            throw new XadrezExcecao("A peça escolhida não pode ser movida!");
        }

    }

// Método para receber as coordenadas do xadrez.     
    private void colocandoNovaPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.lugarPecas(peca, new XadrezPosicao(coluna, linha).dePosicao());
    }

// Inicio da partida. Para testar eu preciso chamar configInicial no construto da partida. 
    private void configInicial() {
        colocandoNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCO));

        colocandoNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETO));

    }

}
