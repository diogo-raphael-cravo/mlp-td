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
     * Velocidade do inimigo em Tiles por segundo.
     * Uma tile é medida pelo seu comprimento.
     * Assim, este é o número de tiles que o inimigo pode 
     * cruzar por segundo em rota horizontal.
     * Caso a rota possua componente no eixo y, o número de
     * tiles realmente passadas por segundo será um pouco menor.
     */
    private float velocidadeTilesSegundo;

    /**
     * @param _posX, _posY A posição do ponto superior esquerdo na tela.
     * @param _comprimento, _largura Comprimento e largura.
     */
    public Inimigo(){
        super(0, 0, 10, 10, 100);
        cor = new Color(Color.BLACK);
        velocidadeTilesSegundo = 3;
    }
    public Inimigo(float _posX, float _posY, float _comprimento, float _largura){
         super(_posX, _posY, _comprimento, _largura, 100);
    }
    public Inimigo(Inimigo _inimigo){
         super(_inimigo);
         velocidadeTilesSegundo = _inimigo.getVelocidadeTilesPorSegundo();
    }

    /**
     * @return Velocidade do inimigo em Tiles por segundo.
     */
     public float getVelocidadeTilesPorSegundo(){
         return velocidadeTilesSegundo;
     }

}
