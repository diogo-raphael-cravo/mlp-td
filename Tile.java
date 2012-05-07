/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import org.lwjgl.util.Color;
import static org.lwjgl.opengl.GL11.*;

/**
 * Representação genérica de uma tile.
 * Permite criação de tile em uma posição com um tamanho.
 * Permite desenho da tile na tela.
 * A posição é pode ser modificada com um ponto.
 * O tamanho é modificado em relação ao tamanho inicial, fornecido ao construtor.
 * @author diogo
 */
public class Tile {
    private int tamanhoEmPorcentagem;
    private float posX;
    private float posY;
    private float comprimento;
    private float largura;
    private Color cor;

    /**
     * @param _posX, _posY A posição do ponto superior esquerdo na tela.
     * @param _comprimento, _largura Comprimento e largura.
     * @param _tamanhoEmPorcentagem Usado para resize.
     */
    public Tile(float _posX, float _posY, float _comprimento, float _largura){
        cor = new Color(Color.GREEN);
        posX = _posX;
        posY = _posY;
        comprimento = _comprimento;
        largura = _largura;
        tamanhoEmPorcentagem = 100;
        redimensionar(1);
    }
    public Tile(float _posX, float _posY, float _comprimento, float _largura, int _tamanhoEmPorcentagem){
        cor = new Color(Color.GREEN);
        posX = _posX;
        posY = _posY;
        comprimento = _comprimento;
        largura = _largura;tamanhoEmPorcentagem = _tamanhoEmPorcentagem;
        redimensionar(1);
    }

    /**
     * Modifica a cor desta tile.
     * @param _cor Cor da tile.
     */
    public void mudarCor(Color _cor){
        cor = new Color(_cor);
    }

    /**
     * @param _destino Ponto para onde a tile deve ir.
     */
    public void mover(float _posX, float _posY){
        posX = _posX;
        posY = _posY;
    }

    /**
     * @param _tamanhoEmPorcentagem O tamanho em relação ao tamanho inicial (de criação) da tile.
     */
    public void redimensionar(int _tamanhoEmPorcentagem){
        tamanhoEmPorcentagem = _tamanhoEmPorcentagem;
    }

    /**
     * Desenha a tile na tela com base em seu tamanho e posição.
     */
    public void desenhar(){
        glPushMatrix();
        glTranslatef(posX,posY,0.0f);
        glRotatef(0,0.0f,0.0f,1.0f);
        glTranslatef(-(100 >> 1),-(100 >> 1),0.0f);
        glColor3f((float) (cor.getRed()/255.0),
                  (float) (cor.getGreen()/255.0),
                  (float) (cor.getBlue()/255.0));
        glBegin(GL_QUADS);
            glTexCoord2f(0.0f,0.0f); glVertex2f(0.0f,0.0f);
            glTexCoord2f(1.0f,0.0f); glVertex2f(tamanhoEmPorcentagem*comprimento, 0.0f);
            glTexCoord2f(1.0f,1.0f); glVertex2f(tamanhoEmPorcentagem*comprimento, tamanhoEmPorcentagem*largura);
            glTexCoord2f(0.0f,1.0f); glVertex2f(0.0f, tamanhoEmPorcentagem*largura);
        glEnd();
        glPopMatrix();
    }

}
