/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

/**
 * Representa um jogo de mlptd.
 * Todo jogo é composto por muitos níveis, todos em um mesmo terreno.
 * @author diogo
 */
public class Jogo {
    /**
     * Tempo em milissegundos entre o nascimento de dois inimigos.
     * Como cada tile passadouro só pode ter um inimigo, este tempo precisa
     * ser suficientemente grande para que dois inimigos nunca se encontrem em
     * uma mesma tile, o que daria erro.
     */
    private static float INTERVALO_NASCIMENTO_INIMIGOS_MILISSEGUNDOS = 1500;

    /**
     * O único terreno utilizado neste jogo.
     */
    private Terreno terreno;

    /**
     * Array com todos níveis deste jogo.
     * Os níveis começam no índice 0.
     * Desta forma, niveis[0] contém os dados do primeiro nível.
     */
    private Nivel[] niveis;

    /**
     * Indica o índice em niveis[] do nível que está acontecendo.
     */
    private int nivelAtual;

    /**
     * Marca os nascimentos.
     */
    private Temporizador temporizadorNascimentos;

    public Jogo(Terreno _terreno, Nivel[] _niveis){
        terreno = _terreno;
        niveis = _niveis;
        nivelAtual = -1;
        temporizadorNascimentos = new Temporizador();
        iniciarProximoNivel();
    }

    /**
     * Inicia o próximo nível, mandando os inimigos andarem.
     */
    public void iniciarProximoNivel(){
        nivelAtual++;
        boolean alcancouFimNiveis = (nivelAtual == niveis.length);
        if(!alcancouFimNiveis){
            temporizadorNascimentos = new Temporizador();
            niveis[nivelAtual].criarInimigo(terreno);
            temporizadorNascimentos.marcarAgora();
        }
    }

    /**
     * Atualiza o jogo, adicionando inimigos quando necessário.
     */
    public void atualizar(){
        if(INTERVALO_NASCIMENTO_INIMIGOS_MILISSEGUNDOS
                <= temporizadorNascimentos.tempoDesdeUltimaMarcacao()){
            niveis[nivelAtual].criarInimigo(terreno);
            temporizadorNascimentos.marcarAgora();
        }
        terreno.moverInimigos();
    }

    /**
     * Desenha o jogo.
     */
    public void desenhar(){
        terreno.desenhar();
    }
    
}
