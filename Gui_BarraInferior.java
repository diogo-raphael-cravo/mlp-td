/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import org.lwjgl.util.Color;
import org.newdawn.slick.opengl.Texture;

/**
 * Elemento da gui que fica na parte inferior da tela.
 * @author diogo
 */
public class Gui_BarraInferior extends Desenho{
    /**
     * Retrato que mostra o elemento selecionado.
     */
    private Gui_Retrato gui_retrato;

    /**
     * Botões.
     */
    private Botao botao;

    public Gui_BarraInferior(){
        /*
         * Nesta hora você se pergunta:
         *      "Por que '+50'?"
         * A resposta é: mistério. Se descobrir, favor me dizer... Eu não consegui.
         * Acontece que, com este '+50' a barra fica na posição certa.
         */
        super(0, 0, Tela.WIDTH, 200, 100);
        mudarCor(new Color(Color.WHITE));
        gui_retrato = new Gui_Retrato(posX, posY, 100, 100, 100);
        adicionarFilho(gui_retrato, 30, 75);
        adicionarTextura(Arquivos.ARQUIVO_TEXTURA_MURO);

        botao = new Botao(0, 0, 30, 30, 100);
        botao.adicionarTextura(Arquivos.ARQUIVO_TEXTURA_CAVEIRA);
        adicionarFilho(botao, 200, 100);
    }

    /**
     * @return Retrato que mostra o elemento selecionado.
     */
    public Gui_Retrato getGuiRetrato(){
        return gui_retrato;
    }


}
