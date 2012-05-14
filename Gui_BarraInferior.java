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
     * Botões.
     */
    private Botao btAdicionarTorreMadeira;
    private Botao btAdicionarTorreCanhao;

    /**
     * Controlador de eventos desta gui.
     */
    private ControladorGuiBarraInferior controlador;

    public Gui_BarraInferior(){
        super(0, 0, Tela.WIDTH, 200, 100);
        mudarCor(new Color(Color.WHITE));
        gui_retrato = new Gui_Retrato(posX, posY, 100, 100, 100);
        adicionarFilho(gui_retrato, 30, 75);
        definirTextura(Texturas.MURO);

        btAdicionarTorreMadeira = new Botao(0, 0, 100, 200, 100);
        btAdicionarTorreMadeira.definirTextura(Texturas.TORRE);
        adicionarFilho(btAdicionarTorreMadeira, 200, 100);
        btAdicionarTorreMadeira.redimensionar(30, 30, 0);

        btAdicionarTorreCanhao = new Botao(0, 0, 480, 220, 100);
        btAdicionarTorreCanhao.definirTextura(Texturas.CANHAO);
        adicionarFilho(btAdicionarTorreCanhao, 300, 100);
        btAdicionarTorreCanhao.redimensionar(30, 30, 0);
        
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
    public Botao getBtAdicionarTorreMadeira(){
        return btAdicionarTorreMadeira;
    }
    public Botao getBtAdicionarTorreCanhao(){
        return btAdicionarTorreCanhao;
    }
}
