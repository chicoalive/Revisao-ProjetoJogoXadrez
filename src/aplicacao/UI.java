package aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import xadrez.Cor;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;
import xadrez.XadrezPosicao;

public class UI {

    // Cores texto
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Cores fundo
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // Limpando a tela
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Lendo a posição que o usuário quer as peças do xadrez. 
    public static XadrezPosicao leiaXadrezPosicao(Scanner teclado) {
        try {
            String respostaUsuario = teclado.nextLine();
            char coluna = respostaUsuario.charAt(0);
            int linha = Integer.parseInt(respostaUsuario.substring(1));
            return new XadrezPosicao(coluna, linha);
        } catch (RuntimeException erro) {
            throw new InputMismatchException("Erro ao ler posição de xadrez: Valores validos são de a1 a h8");
        }
    }

    // Imprimindo a partida
    public static void imprimirPartida(PartidaXadrez partidaXadrez, List<PecaXadrez> capturada) {
        imprimirTabuleiro(partidaXadrez.getPecas());
        System.out.println(" ");
        imprimirPecaCapturadas(capturada);
        System.out.println(" ");
        System.out.println("Turno: " + partidaXadrez.getTurno());
        if (!partidaXadrez.getCheckMate()) {
            System.out.println("Proxima jogada: " + partidaXadrez.getJogadorAtual());
            if (partidaXadrez.getCheck()) {
                System.out.println("Você está em Check!");
            }
        } else {
            System.out.println("CheckMate!");
            System.out.println("parabéns ao vencendor: "+partidaXadrez.getJogadorAtual());
        }
    }

    public static void imprimirTabuleiro(PecaXadrez[][] pecas) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                imprimirPeca(pecas[i][j], false);
            }
            System.out.println(" ");
        }
        System.out.println("  abcdefgh");
    }

    // Sobrecarga
    public static void imprimirTabuleiro(PecaXadrez[][] pecas, boolean[][] movimentosPossiveis) {
        for (int i = 0; i < pecas.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pecas.length; j++) {
                imprimirPeca(pecas[i][j], movimentosPossiveis[i][j]);
            }
            System.out.println(" ");
        }
        System.out.println("  abcdefgh");
    }

    private static void imprimirPeca(PecaXadrez peca, boolean fundoPeca) {
        if (fundoPeca) {
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        } else {
            if (peca.getCor() == Cor.BRANCO) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            } else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
    }

    // Peças capturadas
    private static void imprimirPecaCapturadas(List<PecaXadrez> capturada) {
        List<PecaXadrez> branca = capturada.stream().filter(elemento -> elemento.getCor() == Cor.BRANCO).collect(Collectors.toList());
        List<PecaXadrez> preta = capturada.stream().filter(elemento -> elemento.getCor() == Cor.PRETO).collect(Collectors.toList());
        System.out.println("Peças capturadas");
        System.out.print("Brancas: ");
        System.out.print(ANSI_WHITE);
        System.out.print(Arrays.toString(branca.toArray()));
        System.out.println(ANSI_RESET);
        System.out.print("Pretas : ");
        System.out.print(ANSI_YELLOW);
        System.out.print(Arrays.toString(preta.toArray()));
        System.out.println(ANSI_RESET);

    }
}
