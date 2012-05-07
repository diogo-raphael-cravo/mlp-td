/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author diogo
 */
public class Inimigo {


    public Inimigo(){
        
    }


    public void desenhar(float _x, float _y){
        glPushMatrix();
        glTranslatef(_x,_y,0.0f);
        glRotatef(0,0.0f,0.0f,1.0f);
        glTranslatef(-(100 >> 1),-(100 >> 1),0.0f);
        glColor3f(0.5f, 0.5f, 0.5f);
        glBegin(GL_QUADS);
            glTexCoord2f(0.0f,0.0f); glVertex2f(0.0f,0.0f);
            glTexCoord2f(1.0f,0.0f); glVertex2f(10, 0.0f);
            glTexCoord2f(1.0f,1.0f); glVertex2f(10, 10);
            glTexCoord2f(0.0f,1.0f); glVertex2f(0.0f, 10);
        glEnd();
        glPopMatrix();
    }

}
