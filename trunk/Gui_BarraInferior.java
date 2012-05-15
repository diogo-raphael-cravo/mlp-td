/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlp.td;

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
     * Gui para editar tiles (inserir, deletar...) torres.
     */
    private Gui_EdicaoTiles guiEdicaoTiles;
    
    /**
     * Controlador de eventos desta gui.
     */
    private ControladorGuiBarraInferior controlador;

    public Gui_BarraInferior(){
        super(0, 0, Tela.WIDTH, 200, 100);
        mudarCor(new Color(Color.WHITE));
        
        gui_retrato = new Gui_Retrato(posX, posY, 100, 100, 100);
        adicionarFilho(gui_retrato, 30, 75);
        
        guiEdicaoTiles = new Gui_EdicaoTiles(comprimento-150, largura);
        adicionarFilho(guiEdicaoTiles, 150, 0);
        guiEdicaoTiles.esconder();
        
        definirTextura(Texturas.MURO);

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
     * @return Gui que permite editar torres.
     */
    public Gui_EdicaoTiles getGuiEdicaoTiles(){
        return guiEdicaoTiles;
    }
}
