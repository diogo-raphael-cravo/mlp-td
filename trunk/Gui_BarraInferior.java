/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import org.lwjgl.util.Color;

/**
 * Elemento da gui que fica na parte inferior da tela.
 * @author NUTED
 */
public class Gui_BarraInferior extends Desenho{

    public Gui_BarraInferior(){
        /*
         * Nesta hora você se pergunta:
         *      "Por que '+50'?"
         * A resposta é: mistério. Se descobrir, favor me dizer... Eu não consegui.
         * Acontece que, com este '+50' a barra fica na posição certa.
         */
        super(Tela.xTelaParaGlobal(0) +50, Tela.yTelaParaGlobal(0), Tela.WIDTH, 200, 100);
        mudarCor(new Color(Color.WHITE));
    }
    

}
