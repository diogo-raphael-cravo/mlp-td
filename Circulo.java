/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import static org.lwjgl.opengl.GL11.*;

/**
 * Desenha um círculo, que é utilizado principalmente para demonstrar
 * o alcance das torres, quando são selecionadas.
 * @author diogo
 */
public class Circulo extends Desenho {
    /**
     * Raio do círculo.
     */
    private float raio;


    /**
     * @param _raio O raio do círculo.
     */
    public Circulo(float _raio){
        super(0, 0, 10, 10);
        raio = _raio;
    }

    /**
     * Modifica o pai deste desenho.
     * @param _pai O pai do desenho.
     * IMPORTANTE: esta função SÓ pode ser usada quando o desenho é adicionado
     * como filho de outro! Se souber outra forma de modelar este comportamento,
     * faça-a, por favor!
     */
    @Override
    protected void setPai(Desenho _pai){
        super.setPai(_pai);
        
    }

    /**
     * Desenha na tela com base em seu tamanho e posicao.
     */
    public void desenhar(){
        double angle;
        glPushMatrix();
        glLoadIdentity();
        
        glColor4f((float) (cor.getRed()/255.0),
                  (float) (cor.getGreen()/255.0),
                  (float) (cor.getBlue()/255.0),
                  (float) (cor.getAlpha()/255.0));

        glLineWidth(5.0f);

        glBegin(GL_LINE_LOOP);
            for(int i = 0; i < 100; i++) {
                angle = i*2*Math.PI/100;
                glVertex2f((float) (getPosX() + (Math.cos(angle) * raio) + raio),
                           (float) (getPosY() + (Math.sin(angle) * raio) + 2*raio));
            }
        glEnd();

        glPopMatrix();
    }

}
