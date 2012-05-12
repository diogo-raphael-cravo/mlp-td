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

    /**
     * O desenho que foi selecionado pelo mouse.
     */
     Desenho desenhoSelecionado;

    public ControladorJogo(Jogo _jogo){
        jogo = _jogo;
        desenhoSelecionado = null;
    }

    public void houveMouseDown() {
        boolean selecionouDesenho = false;
        float mouseX = Tela.xTelaParaGlobal(Mouse.getX());
        float mouseY = Tela.yTelaParaGlobal(Mouse.getY());
        Vector<Desenho> todosDesenhosCriados = Desenho.getTodosDesenhosCriados();
        for(Desenho desenho : todosDesenhosCriados){
            if(desenho.contem(mouseX, mouseY)){
                if(desenhoSelecionado != null){
                    desenhoSelecionado.restaurarTransparencia();
                }
                desenhoSelecionado = desenho;
                desenhoSelecionado.tornarTransparente();
                selecionouDesenho = true;
                Tela.getGuiBarraInferior().getGuiRetrato().exibir(desenhoSelecionado);
            }
        }
        if(!selecionouDesenho && desenhoSelecionado != null){
            desenhoSelecionado.restaurarTransparencia();
            desenhoSelecionado = null;
            Tela.getTela().getGuiBarraInferior().getGuiRetrato().retirarDesenhoExibido();
        }
    }

}
