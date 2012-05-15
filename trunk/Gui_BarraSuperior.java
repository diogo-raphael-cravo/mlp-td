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

    /**
     * Desenho que indica vidas, ao lado do qual é impressa a quantidade de vidas.
     */
     private Desenho iconeVidas;

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

        iconeVidas = new Desenho(0, 0, 35, 40);
        iconeVidas.definirTextura(Texturas.CAVEIRA);
        iconeVidas.redimensionar(15, 15, 0);
        adicionarFilho(iconeVidas, 7.5f+comprimento/2, 7.5f);
    }

    
}
