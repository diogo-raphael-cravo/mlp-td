/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import org.lwjgl.input.Mouse;

/**
 * Controla eventos passados à gui_BarraInferior.
 * @author diogo
 */
public class ControladorGuiBarraInferior
    implements MouseListener{
    /**
     * Gui controlada por este controlador.
     */
    private Gui_BarraInferior gui;

    /**
     * @param _gui A gui controlada por este controlador.
     */
    public ControladorGuiBarraInferior(Gui_BarraInferior _gui){
        gui = _gui;
    }

    public void houveMouseDown() {
        float mouseX = Mouse.getX();
        float mouseY = Mouse.getY();
        
    }

}
