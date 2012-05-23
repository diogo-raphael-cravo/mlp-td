package mlptd;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Controla eventos que pertençam a um jogo.
 * @author drcravo
 */
public class ControladorJogo
    implements MouseListener {

    /**
     * O jogo do controlador.
     */
     Jogo jogo;

    public ControladorJogo(Jogo _jogo){
        jogo = _jogo;
        Tela.getGuiBarraInferior().getControlador().definirControladorTerreno(jogo.getTerreno().getControlador());
        jogo.getTerreno().getControlador().definirControladorGuiEdicaoTerreno(Tela.getGuiBarraInferior().getControlador());
    }

    @Override
    public void houveMouseDown() {
        Tela.getGuiBarraInferior().getControlador().houveMouseDown();
        if(jogo.getTerreno().getControlador() != null){
            jogo.getTerreno().getControlador().houveMouseDown();
        }
    }

    @Override
    public void mouseMoveu() {
        Tela.getGuiBarraInferior().getControlador().mouseMoveu();
        if(jogo.getTerreno().getControlador() != null){
            jogo.getTerreno().getControlador().mouseMoveu();
        }
    }
}
