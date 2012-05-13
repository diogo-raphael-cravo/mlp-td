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
    private Botao btAdicionarTorre;

    /**
     * Controlador de eventos desta gui.
     */
    private ControladorGuiBarraInferior controlador;

    public Gui_BarraInferior(){
        super(0, 0, Tela.WIDTH, 200, 100);
        mudarCor(new Color(Color.WHITE));
        gui_retrato = new Gui_Retrato(posX, posY, 100, 100, 100);
        adicionarFilho(gui_retrato, 30, 75);
        adicionarTextura(Texturas.MURO);

        btAdicionarTorre = new Botao(0, 0, 100, 200, 100);
        btAdicionarTorre.adicionarTextura(Texturas.TORRE);
        adicionarFilho(btAdicionarTorre, 200, 100);
        btAdicionarTorre.redimensionar(30, 30, 0);

        controlador = new ControladorGuiBarraInferior(this);
    }

    /**
     * @return Retrato que mostra o elemento selecionado.
     */
    public Gui_Retrato getGuiRetrato(){
        return gui_retrato;
    }

    /**
     * @return Controlador de eventos de mouse desta gui.
     */
    public ControladorGuiBarraInferior getControlador(){
        return controlador;
    }


    /**
     * @return Botão que adiciona torres.
     */
    public Botao getBtAdicionarTorre(){
        return btAdicionarTorre;
    }
}
