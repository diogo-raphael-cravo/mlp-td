/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlp.td;

import static org.lwjgl.opengl.GL11.*;

/**
 * Representação genérica de uma tile.
 * Permite criação de tile em uma posição com um tamanho.
 * Permite desenho da tile na tela.
 * A posição é pode ser modificada com um ponto.
 * O tamanho é modificado em relação ao tamanho inicial, fornecido ao construtor.
 * @author diogo
 */
public class Tile extends Desenho {
    /**
     * @param _posX, _posY A posição do ponto superior esquerdo na tela.
     * @param _comprimento, _largura Comprimento e largura.
     * @param _tamanhoEmPorcentagem Usado para resize.
     */
    public Tile(float _posX, float _posY, float _comprimento, float _largura){
        super(_posX, _posY, _comprimento, _largura);
        definirTextura(Texturas.GRAMA);
    }
    public Tile(Tile _tile){
        super(_tile);
    }

}
