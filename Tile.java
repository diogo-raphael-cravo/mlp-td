/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

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
        super(_posX, _posY, _comprimento, _largura, 100);
    }
    public Tile(float _posX, float _posY, float _comprimento, float _largura, int _tamanhoEmPorcentagem){
        super(_posX, _posY, _comprimento, _largura, _tamanhoEmPorcentagem);
    }

    /**
     * Desenha a tile na tela com base em seu tamanho e posição.
     */
    @Override
    public void desenhar(){
        glPushMatrix();
        glTranslatef(posX,posY,0.0f);
        glRotatef(0,0.0f,0.0f,1.0f);
        glTranslatef(-(100 >> 1),-(100 >> 1),0.0f);
        glColor4f((float) (cor.getRed()/255.0),
                  (float) (cor.getGreen()/255.0),
                  (float) (cor.getBlue()/255.0),
                  (float) (cor.getAlpha()/255.0));
        glBegin(GL_QUADS);
            glTexCoord2f(0.0f,0.0f); glVertex2f(0.0f,0.0f);
            glTexCoord2f(1.0f,0.0f); glVertex2f(tamanhoEmPorcentagem*comprimento, 0.0f);
            glTexCoord2f(1.0f,1.0f); glVertex2f(tamanhoEmPorcentagem*comprimento, tamanhoEmPorcentagem*largura);
            glTexCoord2f(0.0f,1.0f); glVertex2f(0.0f, tamanhoEmPorcentagem*largura);
        glEnd();
        glPopMatrix();
    }

}
