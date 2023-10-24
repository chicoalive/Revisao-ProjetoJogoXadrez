package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jogoTabuleiro.Pecas;
import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

    private int turno;
    private Cor jogadorAtual;
    private Tabuleiro tabuleiro;
    // Por padrão o boolean começa com zero
    private boolean check;
    private boolean checkMate;

    // Listas para peças no tabuleiro e lista para peças capturadas. 
    private List<Pecas> pecasNoTabuleiro = new ArrayList<>();
    private List<Pecas> pecasCapturadas = new ArrayList<>();

    // Construtores. Coração do nosso programa. Quem tem que saber a dimensão do jogo é essa classe.
    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        turno = 1;
        jogadorAtual = Cor.BRANCO;
        configInicial();
    }

    // Apenas Get, pois não queremos modificar os atributos vez e jogador atual. 
    public int getTurno() {
        return turno;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
    }

    // Métodos
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

    // Mostrando os possíveis movimentos que a peça pode fazer!
    public boolean[][] possivelMovimento(XadrezPosicao origemPosicao) {
        // Convertendo a posição de Xadrez para posição de matriz normal
        Posicao posicao = origemPosicao.dePosicao();
        // Retornando os movimentos possíveis da posição.
        validarPosicaoOrigem(posicao);
        // Retornando os movimentos possiveis da peça
        return tabuleiro.pecas(posicao).movimentosPossiveisMatriz();
    }

    public PecaXadrez movimentoPecaXadrez(XadrezPosicao posicaoOrigem, XadrezPosicao posicaoDestino) {
        // Convertendo para posições da matriz. 
        Posicao origem = posicaoOrigem.dePosicao();
        Posicao destino = posicaoDestino.dePosicao();
        validarPosicaoOrigem(origem);
        validarposicaoDestino(origem, destino);
        // Necessário criar um dowcast para peça xadrez
        Pecas pecaCapturada = fazerMovimento(origem, destino);
        // Testando se o movimento colocará o jogador atual em check
        if (testeCheck(jogadorAtual)) {
            desFazerMovimento(origem, destino, pecaCapturada);
            throw new XadrezExcecao("Você não pode se colocar em check: ");
        }
        // Testando se o oponente atual ficou em check. Expresão condicional ternaria. 
        check = (testeCheck(oponente(jogadorAtual))) ? true : false;
        // Testando se o jogador está em checkmate
        if (testeCheckMate(oponente(jogadorAtual))) {
            checkMate = true;
        } else {
            // Troca de turno
            proximoTurno();
        }
        return (PecaXadrez) pecaCapturada;
    }

    private Pecas fazerMovimento(Posicao origem, Posicao destino) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removPeca(origem);
        p.contagemMovimentosMais();
        Pecas pecaCapturada = tabuleiro.removPeca(destino);
        tabuleiro.lugarPecas(p, destino);
        if (pecaCapturada != null) {
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }
        
        // Movimento especial Roque lado Rei
        if (p instanceof Rei && destino.getColuna() == origem.getColuna()+2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna()+3);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna()+1);
            PecaXadrez torre = (PecaXadrez)tabuleiro.removPeca(origemTorre);
            tabuleiro.lugarPecas(torre, destinoTorre);
            torre.contagemMovimentosMais();
        }
        
        // Movimento especial Roque lado Rainha
        if (p instanceof Rei && destino.getColuna() == origem.getColuna()-2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna()-4);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna()-1);
            PecaXadrez torre = (PecaXadrez)tabuleiro.removPeca(origemTorre);
            tabuleiro.lugarPecas(torre, destinoTorre);
            torre.contagemMovimentosMais();
        }
        
        return pecaCapturada;
    }

    private void desFazerMovimento(Posicao origem, Posicao destino, Pecas pecaCapturada) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removPeca(destino);
        p.contagemMovimentoMenos();
        tabuleiro.lugarPecas(p, origem);
        if (pecaCapturada != null) {
            tabuleiro.lugarPecas(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }
         // Movimento especial Roque lado Rei
        if (p instanceof Rei && destino.getColuna() == origem.getColuna()+2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna()+3);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna()+1);
            PecaXadrez torre = (PecaXadrez)tabuleiro.removPeca(destinoTorre);
            tabuleiro.lugarPecas(torre, origemTorre);
            torre.contagemMovimentoMenos();
        }
        
        // Movimento especial Roque lado Rainha
        if (p instanceof Rei && destino.getColuna() == origem.getColuna()-2) {
            Posicao origemTorre = new Posicao(origem.getLinha(), origem.getColuna()-4);
            Posicao destinoTorre = new Posicao(origem.getLinha(), origem.getColuna()-1);
            PecaXadrez torre = (PecaXadrez)tabuleiro.removPeca(destinoTorre);
            tabuleiro.lugarPecas(torre, origemTorre);
            torre.contagemMovimentoMenos();
        }
    }

    private void validarPosicaoOrigem(Posicao posicao) {
        // Se não existir uma peça nessa posição, o código lançara uma exceção.
        if (!tabuleiro.haPecas(posicao)) {
            throw new XadrezExcecao("Não existe peça na posição de origem");
        }
        // Verificado se o turno é do jogador atual, como o getCor é uma propriedade do PecaXadrez, preciso realizar o dowcast para PecaXadrez
        if (jogadorAtual != ((PecaXadrez) tabuleiro.pecas(posicao)).getCor()) {
            throw new XadrezExcecao("A peça escolhida não é sua, favor escolher outra");
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

    // Troca de turnos dos jogadores
    private void proximoTurno() {
        turno++;
        jogadorAtual = (jogadorAtual == Cor.BRANCO) ? Cor.PRETO : Cor.BRANCO;
    }

    // Criando oponente
    private Cor oponente(Cor cor) {
        return (cor == cor.BRANCO) ? cor.PRETO : Cor.BRANCO;
    }

    private PecaXadrez rei(Cor cor) {
        List<Pecas> lista = pecasNoTabuleiro.stream().filter(pecaX -> ((PecaXadrez) pecaX).getCor() == cor).collect(Collectors.toList());
        for (Pecas p : lista) {
            if (p instanceof Rei) {
                return (PecaXadrez) p;
            }
        }
        // O compilador informa que pode ser que o método não retorne nada, nesse caso devemos lançar uma exceção.
        throw new IllegalStateException("Não existe o Rei na cor " + cor + " no tabuleiro");
    }

    // Testando se uma peça está em check, recebe uma cor como argumento, pois estou testando se o rei de tal cor está em check. 
    private boolean testeCheck(Cor cor) {
        Posicao reiPosicao = rei(cor).getXadrezPosicao().dePosicao();
        // Lista de peças do oponente nas cores do parâmetro. Peças no tabaleiro filtrada com a cor do Rei. 
        List<Pecas> oponentePecas = pecasNoTabuleiro.stream().filter(pecaX -> ((PecaXadrez) pecaX).getCor() == oponente(cor)).collect(Collectors.toList());
        for (Pecas p : oponentePecas) {
            boolean[][] matriz = p.movimentosPossiveisMatriz();
            if (matriz[reiPosicao.getLinha()][reiPosicao.getColuna()]) {
                return true;
            }
        }
        return false;
    }

    private boolean testeCheckMate(Cor cor) {
        if (!testeCheck(cor)) {
            return false;
        }
        List<Pecas> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
        for (Pecas p : lista) {
            boolean[][] matriz = p.movimentosPossiveisMatriz();
            for (int i = 0; i < tabuleiro.getLinhas(); i++) {
                for (int j = 0; j < tabuleiro.getColunas(); j++) {
                    if (matriz[i][j]) {
                        Posicao origem = ((PecaXadrez) p).getXadrezPosicao().dePosicao();
                        Posicao destino = new Posicao(i, j);
                        Pecas pecaCapturada = fazerMovimento(origem, destino);
                        boolean testeCheck = testeCheck(cor);
                        desFazerMovimento(origem, destino, pecaCapturada);
                        if (!testeCheck) {
                            return false;
                        }
                    }
                }
            }

        }
        return true;
    }

    // Método para receber as coordenadas do xadrez.     
    private void colocandoNovaPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.lugarPecas(peca, new XadrezPosicao(coluna, linha).dePosicao());
        pecasNoTabuleiro.add(peca);
    }

    // Inicio da partida. Para testar eu preciso chamar configInicial no construto da partida. 
    private void configInicial() {
        colocandoNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCO, this));
        colocandoNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCO));
        colocandoNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCO));

        colocandoNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETO, this));
        colocandoNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETO));
        colocandoNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETO));

    }

}
