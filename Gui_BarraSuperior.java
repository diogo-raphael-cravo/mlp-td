/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import org.lwjgl.util.Color;

/**
 * Elemento da gui que fica na parte superior da tela.
 * Fornece dados de vida do jogador e dinheiro.
 * @author drcravo
 */
public class Gui_BarraSuperior extends Desenho {
    /**
     * Desenho que indica ouro, ao lado do qual é impressa a quantidade de ouro.
     */
     private Desenho iconeOuro;
     private CaixaDeTexto ctOuro;

    /**
     * Desenho que indica vidas, ao lado do qual é impressa a quantidade de vidas.
     */
     private Desenho iconeVidas;
     private CaixaDeTexto ctVidas;

    /**
     * Desenho que indica a quantidade de inimigos mortos.
     */
     private Desenho iconeInimigos;
     private CaixaDeTexto ctInimigos;

    /**
     * Desenho que indica unidades de tempo.
     */
     private Desenho iconeRelogio;
     private CaixaDeTexto ctTempo;

    /**
     * Quantidade de vidas informada pela gui.
     */
    private float vidasInformadas;

    /**
     * Quantidade de ouro informada pela gui.
     */
    private float ouroInformado;

    public Gui_BarraSuperior(){
        super(0, Tela.HEIGHT-30, Tela.WIDTH, 30);
        mudarCor(new Color(Color.WHITE));
        definirTextura(Texturas.MURO);

        iconeOuro = new Desenho(0, 0, 200, 200);
        iconeOuro.definirTextura(Texturas.OURO);
        iconeOuro.redimensionar(15, 15, 0);
        adicionarFilho(iconeOuro, 7.5f, 7.5f);

        iconeInimigos = new Desenho(0, 0, 35, 40);
        iconeInimigos.definirTextura(Texturas.CAVEIRA);
        iconeInimigos.redimensionar(15, 15, 0);
        adicionarFilho(iconeInimigos, 7.5f+comprimento/4, 7.5f);

        iconeVidas = new Desenho(0, 0, 250, 250);
        iconeVidas.definirTextura(Texturas.CORACAO);
        iconeVidas.redimensionar(15, 15, 0);
        adicionarFilho(iconeVidas, 7.5f+comprimento/2, 7.5f);

        iconeRelogio = new Desenho(0, 0, 470, 470);
        iconeRelogio.definirTextura(Texturas.RELOGIO);
        iconeRelogio.redimensionar(15, 15, 0);
        adicionarFilho(iconeRelogio, 7.5f+3*comprimento/4, 7.5f);
        
        /**
         * As caixas de texto devem ser adicionados DEPOIS dos ícones.
         * Assim, elas não interferem no desenhar() dos ícones.
         */
        ctOuro = new CaixaDeTexto(50, 0);
        ctOuro.setTexto("0");
        adicionarFilho(ctOuro, 7.5f+20, 7.5f);

        ctInimigos = new CaixaDeTexto(50, 0);
        ctInimigos.setTexto("0");
        adicionarFilho(ctInimigos, 7.5f+comprimento/4+20, 7.5f);

        ctVidas = new CaixaDeTexto(50, 0);
        ctVidas.setTexto("30");
        adicionarFilho(ctVidas, 7.5f+comprimento/2+20, 7.5f);

        ctTempo = new CaixaDeTexto(50, 0);
        ctTempo.setTexto("0");
        adicionarFilho(ctTempo, 7.5f+3*comprimento/4+20, 7.5f);
    }

    
}
