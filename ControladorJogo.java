package mlptd;

import java.util.Vector;
import org.lwjgl.input.Mouse;

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
    }

    public void houveMouseDown() {
        boolean selecionouDesenho = false;

        Tela.getGuiBarraInferior().getControlador().houveMouseDown();
        
    }
}
