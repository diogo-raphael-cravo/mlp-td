/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlp.td;
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
    public static enum CAMERA{PERSPECTIVA, ORTOGRAFICA, ORTOGRAFICA_ESTATICA, ORTOGRAFICA_ESTATICA_INVERTIDA_Y};

    /**
     * Posiçao da camera.
     */
    private float posX;
    private float posY;
    private float posZ;

    /**
     * Rotação da camera.
     */
    private float rotacaoX;
    private float rotacaoY;
    private float rotacaoZ;

    /**
     * Deslocamento em um eixo, não na direção para a qual a camera olha.
     */
    private float deslocamentoX;
    private float deslocamentoY;
    private float deslocamentoZ;

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
        posZ = 0;
        rotacaoX = 0;
        rotacaoY = 0;
        rotacaoZ = 0;
        deslocamentoX = 0;
        deslocamentoY = 0;
        deslocamentoZ = 0;
    }

    public float getX(){
        return posX;
    }
    public float getY(){
        return posY;
    }
    public float getZ(){
        return posZ;
    }
    public float getRotacaoX(){
        return rotacaoX;
    }
    public float getRotacaoY(){
        return rotacaoY;
    }
    public float getRotacaoZ(){
        return rotacaoZ;
    }
    public static float xCameraAtual(){
        if(cameraAtual == CAMERA.ORTOGRAFICA){
            return ortografica.posX;
        } else if(cameraAtual == CAMERA.PERSPECTIVA){
            return perspectiva.posX;
        } else {
            return 0;
        }
    }
    public static float yCameraAtual(){
        if(cameraAtual == CAMERA.ORTOGRAFICA){
            return ortografica.posY;
        } else if(cameraAtual == CAMERA.PERSPECTIVA){
            return perspectiva.posY;
        } else {
            return 0;
        }
    }
    public static float zCameraAtual(){
        if(cameraAtual == CAMERA.ORTOGRAFICA){
            return ortografica.posZ;
        } else if(cameraAtual == CAMERA.PERSPECTIVA){
            return perspectiva.posZ;
        } else {
            return 0;
        }
    }
    
    /**
     * Permite mover a camera para uma posição de destino.
     * @param _posX, _posY A posição de destino.
     */
    public void mover(float _posX, float _posY){
        posX = _posX;
        posY = _posY;
    }
    public static void moverCameras(float _posX, float _posY){
        ortografica.mover(_posX, _posY);
        perspectiva.mover(_posX, _posY);
        Camera.atualizar();
    }

    /**
     * Permite deslocar a camera.
     * @param _deslocamentoX, _deslocamentoY O deslocamento nestes eixos.
     */
    public void deslocarNaDirecaoQueOlha(float _deslocamentoX, float _deslocamentoY){
        posX += _deslocamentoX;
        posY += _deslocamentoY;
    }
    public void deslocarNoEixoY(float _deslocamento){
        deslocamentoY = _deslocamento;
    }
    public static void deslocarCamerasNaDirecaoQueOlham(float _deslocamentoX, float _deslocamentoY){
        ortografica.deslocarNaDirecaoQueOlha(_deslocamentoX, _deslocamentoY);
        perspectiva.deslocarNaDirecaoQueOlha(_deslocamentoX, _deslocamentoY);
        Camera.atualizar();
    }
    public static void deslocarCamerasNoEixoY(float _deslocamento){
        ortografica.deslocarNoEixoY(_deslocamento);
        perspectiva.deslocarNoEixoY(_deslocamento);
        Camera.atualizar();
    }

    /**
     * Modifica o zoom.
     * @param _zoom Se um número positivo, é zoom in.
     *              Se um número negativo, é zoom out.
     */
    public static void zoom(float _zoom){
        ortografica.posZ += _zoom;
        perspectiva.posZ += _zoom;
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
            glTranslatef(-perspectiva.posX-400,
                         -perspectiva.posY-400,
                         -1000-perspectiva.posZ);

            glRotatef(perspectiva.rotacaoX,1.0f,0.0f,0.0f);
            glTranslatef(perspectiva.deslocamentoY, perspectiva.deslocamentoY, perspectiva.deslocamentoZ);
            glRotatef(-perspectiva.rotacaoX,1.0f,0.0f,0.0f);
            //glTranslatef(0,0,-1000);

            //glTranslatef(perspectiva.posX, perspectiva.posY, 0);
            glRotatef(perspectiva.rotacaoX,1.0f,0.0f,0.0f);
            glRotatef(perspectiva.rotacaoY,0.0f,1.0f,0.0f);
            glRotatef(perspectiva.rotacaoZ,0.0f,0.0f,1.0f);
            //glTranslatef(-perspectiva.posX, -perspectiva.posY, 0);

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
            
            //glTranslatef(ortografica.posX, ortografica.posY, 0);
            glRotatef(ortografica.rotacaoX,1.0f,0.0f,0.0f);
            glRotatef(ortografica.rotacaoY,0.0f,1.0f,0.0f);
            glRotatef(ortografica.rotacaoZ,0.0f,0.0f,1.0f);
            //glTranslatef(-ortografica.posX, -ortografica.posY, 0);

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
        } else if(_cameraEscolhida == CAMERA.ORTOGRAFICA_ESTATICA_INVERTIDA_Y){
            glViewport(0,0,Tela.WIDTH,Tela.HEIGHT);

            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(ortografica_estatica.posX,ortografica_estatica.posX+Tela.WIDTH,
                    ortografica_estatica.posY+Tela.HEIGHT,ortografica_estatica.posY,
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

        if(0 < rotacaoX){
            rotacaoX = 0;
        } else if(rotacaoX < -45){
            rotacaoX = -45;
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

    /**
     * @param _xTela Uma coordenada X em coordenadas de tela.
     * @return Coordenada X passada em coordenada globais.
     */
     public static float xTelaParaGlobal(float _xTela){
         return Tela.getTela().getPosX() + _xTela + perspectiva.posX;
     }

    /**
     * @param _yTela Uma coordenada Y em coordenadas de tela.
     * @return Coordenada Y passada em coordenada globais.
     */
     public static float yTelaParaGlobal(float _yTela){
         return Tela.getTela().getPosY() + _yTela + perspectiva.posY;
     }

    /**
     * Converte um ponto de coordenadas globais para as coordenadas de camera.
     * Na prática, retorna a posição global em que o ponto é visto, não necessariamente
     * a posição em que ele está.
     * @param _posX A coordenada x do ponto a ser convertido.
     * @return A coordenada x do ponto convertido.
     */
     public static float xGlobalParaCamera(float _posX, float _posY){
         float xConvertido = _posX;
         float yConvertido = _posY;
         float hipotenusaMouseRotacionado = (float) Math.sqrt( Math.pow(xConvertido, 2) + Math.pow(yConvertido, 2));

        //rotacionar no plano xy
        hipotenusaMouseRotacionado = (float) Math.sqrt( Math.pow(xConvertido, 2) + Math.pow(yConvertido, 2));
        xConvertido = (float) (hipotenusaMouseRotacionado*Math.cos(Camera.perspectiva.getRotacaoZ()));
        yConvertido = (float) (hipotenusaMouseRotacionado*Math.cos(Camera.perspectiva.getRotacaoZ()));
        //rotacionar no plano xz
        hipotenusaMouseRotacionado = (float) Math.sqrt( Math.pow(xConvertido, 2) + Math.pow(yConvertido, 2));
        xConvertido = (float) (hipotenusaMouseRotacionado*Math.cos(Camera.perspectiva.getRotacaoY()));
        //rotacionar no plano yz
        hipotenusaMouseRotacionado = (float) Math.sqrt( Math.pow(xConvertido, 2) + Math.pow(yConvertido, 2));
        yConvertido = (float) (hipotenusaMouseRotacionado*Math.cos(Camera.perspectiva.getRotacaoX()));
        return xConvertido;
     }

    /**
     * Converte um ponto de coordenadas globais para as coordenadas de camera.
     * Na prática, retorna a posição global em que o ponto é visto, não necessariamente
     * a posição em que ele está.
     * @param _posY A coordenada y do ponto a ser convertido.
     * @return A coordenada y do ponto convertido.
     */
     public static float yGlobalParaCamera(float _posX, float _posY){
         float xConvertido = _posX;
         float yConvertido = _posY;
         float hipotenusaMouseRotacionado = (float) Math.sqrt( Math.pow(xConvertido, 2) + Math.pow(yConvertido, 2));

        //rotacionar no plano xy
        hipotenusaMouseRotacionado = (float) Math.sqrt( Math.pow(xConvertido, 2) + Math.pow(yConvertido, 2));
        xConvertido = (float) (hipotenusaMouseRotacionado*Math.cos(Camera.perspectiva.getRotacaoZ()));
        yConvertido = (float) (hipotenusaMouseRotacionado*Math.cos(Camera.perspectiva.getRotacaoZ()));
        //rotacionar no plano xz
        hipotenusaMouseRotacionado = (float) Math.sqrt( Math.pow(xConvertido, 2) + Math.pow(yConvertido, 2));
        xConvertido = (float) (hipotenusaMouseRotacionado*Math.cos(Camera.perspectiva.getRotacaoY()));
        //rotacionar no plano yz
        hipotenusaMouseRotacionado = (float) Math.sqrt( Math.pow(xConvertido, 2) + Math.pow(yConvertido, 2));
        yConvertido = (float) (hipotenusaMouseRotacionado*Math.cos(Camera.perspectiva.getRotacaoX()));
        return yConvertido;
     }

}
