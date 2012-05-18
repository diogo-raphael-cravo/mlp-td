/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import java.nio.FloatBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;

/**
 * Classe que descreve uma fonte de luz.
 * Tutorial de inspiração (e bastante cópia): http://forum.codecall.net/topic/66017-simple-lwjgl-lighting/
 * @author diogo
 */
public class Luz {
    /**
     * Array de coeficientes especulares da luz.
     */
    private FloatBuffer matSpecular;

    /**
     * Array da posição da luz.
     */
    private FloatBuffer lightPosition;

    /**
     * Array da cor da luz.
     */
    private FloatBuffer whiteLight;

    /**
     * Array de coeficientes ambientes da luz.
     */
    private FloatBuffer lModelAmbient;

    /**
     * Rotação da luz.
     */
    private float rotacaoX;
    private float rotacaoY;
    private float rotacaoZ;

    public Luz(){
        matSpecular = BufferUtils.createFloatBuffer(4);
        matSpecular.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();

        lightPosition = BufferUtils.createFloatBuffer(4);
        lightPosition.put(1.0f).put(1.0f).put(1.0f).put(0.0f).flip();

        whiteLight = BufferUtils.createFloatBuffer(4);
        whiteLight.put(1.0f).put(1.0f).put(1.0f).put(1.0f).flip();

        lModelAmbient = BufferUtils.createFloatBuffer(4);
        lModelAmbient.put(0.5f).put(0.5f).put(0.5f).put(1.0f).flip();

        rotacaoX = 0;
        rotacaoY = 0;
        rotacaoZ = 0;
    }


    public FloatBuffer getEspecular(){
        return matSpecular;
    }
    public FloatBuffer getPosicao(){
        return lightPosition;
    }
    public FloatBuffer getBranco(){
        return whiteLight;
    }
    public FloatBuffer getAmbiente(){
        return lModelAmbient;
    }

    /**
     * Aplica as modificações necessárias para que a luz fique em sua posição certa.
     */
    public void desenhar(){
        glPushMatrix();
        glLoadIdentity();

        //Sets the position of the light in the world
        //glTranslatef(this->transx, this->transy, this->transz);

        //Do translation around the origin
        glRotatef(rotacaoX,1.0f,0.0f,0.0f);
        glRotatef(rotacaoY,0.0f,1.0f,0.0f);
        glRotatef(rotacaoZ,0.0f,0.0f,1.0f);
        glLight(GL_LIGHT0, GL_POSITION, lightPosition);

        glPopMatrix();
    }

    /**
     * Rotaciona a luz.
     * @param _rotacaoX, _rotacaoY, _rotacaoZ Valor da rotação em [0,360).
     *        Valores maiores são truncados.
     */
    public void rotacionar(float _rotacaoX, float _rotacaoY, float _rotacaoZ){
        rotacaoX += _rotacaoX;
        rotacaoY += _rotacaoY;
        rotacaoZ += _rotacaoZ;

        if(45 < rotacaoX){
            rotacaoX = 45;
        } else if(rotacaoX < 0){
            rotacaoX = 0;
        }
        if(0 < rotacaoY){
            rotacaoY = 0;
        } else if(rotacaoY < -90){
            rotacaoY = -90;
        }

        System.out.println("y="+rotacaoY);
    }
}
