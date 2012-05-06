/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import java.awt.Point;
import java.awt.Rectangle;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

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
    private Rectangle forma;

    /**
     * @param _posicao A posição do ponto superior esquerdo na tela.
     * @param _comprimento Comprimento da tile.
     * @param _largura Largura da tile.
     * @param _tamanhoEmPorcentagem Usado para resize.
     */
    public Tile(Point _posicao, int _comprimento, int _largura){
        forma = new Rectangle();
        forma.setLocation(_posicao);
        forma.setSize(_comprimento, _largura);
        tamanhoEmPorcentagem = 100;
    }
    public Tile(Point _posicao, int _tamanhoEmPorcentagem, int _comprimento, int _largura){
        forma = new Rectangle();
        forma.setLocation(_posicao);
        forma.setSize(_comprimento, _largura);
        tamanhoEmPorcentagem = _tamanhoEmPorcentagem;
    }

    /**
     * @param _destino Ponto para onde a tile deve ir.
     */
    public void mover(Point _destino){
        forma.setLocation(_destino);
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
        glTranslatef((float) forma.getX(), (float) forma.getY(),0.0f);
        glRotatef(0,0.0f,0.0f,1.0f);
        glTranslatef(-(tamanhoEmPorcentagem >> 1),-(tamanhoEmPorcentagem >> 1),0.0f);
        glColor3f(0.0f,0.5f,0.5f);
        glBegin(GL_QUADS);
            glTexCoord2f(0.0f,0.0f); glVertex2f(0.0f,0.0f);
            glTexCoord2f(1.0f,0.0f); glVertex2f((float) (tamanhoEmPorcentagem*forma.getWidth()),
                                                0.0f);
            glTexCoord2f(1.0f,1.0f); glVertex2f((float) (tamanhoEmPorcentagem*forma.getWidth()),
                                                (float) (tamanhoEmPorcentagem*forma.getHeight()));
            glTexCoord2f(0.0f,1.0f); glVertex2f(0.0f,
                                                (float) (tamanhoEmPorcentagem*forma.getHeight()));
        glEnd();
    }

}
