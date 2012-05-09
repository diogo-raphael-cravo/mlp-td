/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import org.lwjgl.util.Color;

/**
 *
 * @author diogo
 */
public class Inimigo extends Desenho {
    /**
     * @param _posX, _posY A posição do ponto superior esquerdo na tela.
     * @param _comprimento, _largura Comprimento e largura.
     */
    public Inimigo(){
        super(0, 0, 10, 10, 100);
        cor = new Color(Color.BLACK);
    }
    public Inimigo(float _posX, float _posY, float _comprimento, float _largura){
         super(_posX, _posY, _comprimento, _largura, 100);
    }
    public Inimigo(Inimigo _inimigo){
         super(_inimigo.getPosX(), _inimigo.getPosY(),
               _inimigo.getComprimento(), _inimigo.getLargura(),
               _inimigo.getTamanhoEmPorcentagem());
    }



}
