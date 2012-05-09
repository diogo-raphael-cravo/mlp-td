/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

/**
 * Cameras oferecidas.
 * Nao permite criacao de cameras, pois apenas uma pode ser usada por vez.
 * Ao inves disso, chamar o mehtodo de inicializacao.
 * @author drcravo
 */
public class Camera {
    /**
     * Camera em perspectiva.
     */
    private static Camera perspectiva;

    /**
     * Camera ortograhfica.
     */
    private static Camera ortografica;

    /**
     * Tipos de cameras.
     */
    public static enum CAMERA{PERSPECTIVA, ORTOGRAFICA};

    /**
     * Posiçao da camera.
     */
    private float _posX;
    private float _posY;

    /**
     * Inicializa todas as cameras, deixando-as prontas para uso.
     */
    public static void inicializacao(){
        perspectiva = new Camera();
        ortografica = new Camera();
    }

    private Camera(){
        _posX = 0;
        _posY = 0;
    }

    /**
     * Permite deslocar a camera.
     * @param _camera A camera que serah deslocada.
     * @param _deslocamentoX, _deslocamentoY O deslocamento nestes eixos.
     */
    public static void deslocar(CAMERA _camera, float _deslocamentoX, float _deslocamentoY){
        if(_camera == CAMERA.PERSPECTIVA){
            
        } else if(_camera == CAMERA.ORTOGRAFICA){
            ortografica._posX += _deslocamentoX;
            ortografica._posY += _deslocamentoY;
        }
    }

    /**
     * Escolha da camera a ser utilizada.
     */
    public static void setCamera(CAMERA _cameraEscolhida){
        if(_cameraEscolhida == CAMERA.PERSPECTIVA){

        } else if(_cameraEscolhida == CAMERA.ORTOGRAFICA){
            glViewport(0,0,Tela.WIDTH,Tela.HEIGHT);

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            gluOrtho2D(ortografica._posX,ortografica._posX+Tela.WIDTH,ortografica._posY,ortografica._posY+Tela.HEIGHT);
            //gluPerspective(angulo, aspect, 1000, 0);
            glPushMatrix();

            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
            glPushMatrix();
        }
    }

    
    
}
