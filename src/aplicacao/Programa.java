package aplicacao;

import jogoTabuleiro.Posicao;
import jogoTabuleiro.Tabuleiro;

public class Programa {

    public static void main(String[] args) {

        System.out.println("Testes");
        Posicao pos = new Posicao(3, 5);
        System.out.println(pos);
        
        Tabuleiro tabuleiro = new Tabuleiro(8, 8);
        System.out.println(tabuleiro);

    }

}
