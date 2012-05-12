/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;
import org.lwjgl.opengl.GL11;
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
     * A camera que está sendo utilizada.
     */
    private static CAMERA cameraAtual;

    /**
     * Camera em perspectiva.
     */
    public static Camera perspectiva;

    /**
     * Camera ortograhfica.
     */
    public static Camera ortografica;

    /**
     * Camera ortograhfica estática, não se meche.
     */
    public static Camera ortografica_estatica;

    /**
     * Tipos de cameras.
     */
    public static enum CAMERA{PERSPECTIVA, ORTOGRAFICA, ORTOGRAFICA_ESTATICA};

    /**
     * Posiçao da camera.
     */
    private float posX;
    private float posY;

    /**
     * Rotação da camera.
     */
    private float rotacaoX;
    private float rotacaoY;
    private float rotacaoZ;

    /**
     * Inicializa todas as cameras, deixando-as prontas para uso.
     */
    public static void inicializar(){
        perspectiva = new Camera();
        ortografica = new Camera();
        ortografica_estatica = new Camera();
        cameraAtual = CAMERA.ORTOGRAFICA;
    }

    private Camera(){
        posX = 0;
        posY = 0;
        rotacaoX = 0;
        rotacaoY = 0;
        rotacaoZ = 0;
    }

    /**
     * Permite mover a camera para uma posição de destino.
     * @param _posX, _posY A posição de destino.
     */
    public void mover(float _posX, float _posY){
        System.out.println("para ("+_posX+","+_posY+")");
        posX = _posX;
        posY = _posY;
    }
    public static void moverCameras(float _posX, float _posY){
        System.out.println("moverCameras");
        ortografica.mover(_posX, _posY);
        perspectiva.mover(_posX, _posY);
        Camera.atualizar();
    }

    /**
     * Permite deslocar a camera.
     * @param _deslocamentoX, _deslocamentoY O deslocamento nestes eixos.
     */
    public void deslocar(float _deslocamentoX, float _deslocamentoY){
        posX += _deslocamentoX;
        posY += _deslocamentoY;
    }
    public static void deslocarCameras(float _deslocamentoX, float _deslocamentoY){
        ortografica.deslocar(_deslocamentoX, _deslocamentoY);
        perspectiva.deslocar(_deslocamentoX, _deslocamentoY);
        Camera.atualizar();
    }

    /**
     * Escolha da camera a ser utilizada.
     */
    public static void setCamera(CAMERA _cameraEscolhida){
        if(_cameraEscolhida == CAMERA.PERSPECTIVA){
            glViewport(0,0,Tela.WIDTH,Tela.HEIGHT);
            
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            gluPerspective(45f,Tela.WIDTH/Tela.HEIGHT,0.0f,100.0f);
            
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
            glRotatef(perspectiva.rotacaoX,1.0f,0.0f,0.0f);
            glRotatef(perspectiva.rotacaoY,0.0f,1.0f,0.0f);
            glRotatef(perspectiva.rotacaoZ,0.0f,0.0f,1.0f);
            glTranslatef(-perspectiva.posX-400,
                         -perspectiva.posY-400,
                         -1000);

            cameraAtual = CAMERA.PERSPECTIVA;
        } else if(_cameraEscolhida == CAMERA.ORTOGRAFICA){
            glViewport(0,0,Tela.WIDTH,Tela.HEIGHT);

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(ortografica.posX,ortografica.posX+Tela.WIDTH,
                    ortografica.posY,ortografica.posY+Tela.HEIGHT,
                    -100000f, 100000f);
            glPushMatrix();
            
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
            glRotatef(ortografica.rotacaoX,1.0f,0.0f,0.0f);
            glRotatef(ortografica.rotacaoY,0.0f,1.0f,0.0f);
            glRotatef(ortografica.rotacaoZ,0.0f,0.0f,1.0f);

            cameraAtual = CAMERA.ORTOGRAFICA;
        } else if(_cameraEscolhida == CAMERA.ORTOGRAFICA_ESTATICA){
            glViewport(0,0,Tela.WIDTH,Tela.HEIGHT);

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(ortografica_estatica.posX,ortografica_estatica.posX+Tela.WIDTH,
                    ortografica_estatica.posY,ortografica_estatica.posY+Tela.HEIGHT,
                    -100000f, 100000f);
            glPushMatrix();

            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();

            cameraAtual = CAMERA.ORTOGRAFICA;
        }
    }

    /**
     * @return Tipo da camera que está ativa.
     */
    public static CAMERA cameraAtiva(){
        return cameraAtual;
    }

    /**
     * Atualiza a camera utilizada para que esteja de acordo com seus atributos.
     */
    public static void atualizar(){
        setCamera(cameraAtual);
    }

    /**
     * Rotaciona a camera.
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
        if(45 < rotacaoY){
            rotacaoY = 45;
        } else if(rotacaoY < -45){
            rotacaoY = -45;
        }
    }
    public static void rotacionarCameras(float _rotacaoX, float _rotacaoY, float _rotacaoZ){
        ortografica.rotacionar(_rotacaoX, _rotacaoY, _rotacaoZ);
        perspectiva.rotacionar(_rotacaoX, _rotacaoY, _rotacaoZ);
        Camera.atualizar();
    }
}
