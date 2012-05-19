/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlp.td;

import org.lwjgl.util.Color;

/**
 * Um botão simples.
 * @author diogo
 */
public class Botao extends Gui {
    
    
    /**
     * @param _posX, _posY A posi��o do ponto superior esquerdo na tela.
     * @param _comprimento, _largura, _altura Comprimento, largura e altura.
     * @param _tamanhoEmPorcentagem Usado para resize.
     */
    public Botao(float _comprimento, float _largura){
        super(_comprimento, _largura);
    }

    /**
     * Pressiona o botão, mudando sua aparência.
     */
    public void pressionar(){
        mudarCor(new Color(Color.DKGREY));
    }
    
    /**
     * Desfaz o pressionamento do botão, caso esteja pressionado.
     */
    public void soltar(){
        mudarCor(new Color(Color.WHITE));
    }
    
}

