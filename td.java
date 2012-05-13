package mlptd;

import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * @author jediTofu
 * @see <a href="http://lwjgl.org/">LWJGL Home Page</a>
 */
public class td {
  public static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  Jogo jogo;
  ControladorJogo controladorJogo;
  Temporizador temporizadorMouse; //Garante que o mouse não seja clicado com freqüência muito alta.

  static {
    try {
      LOGGER.addHandler(new FileHandler("errors.log",true));
    }
    catch(IOException ex) {
      LOGGER.log(Level.WARNING,ex.toString(),ex);
    }
  }

  public td() {
    try {
      create();
      Inimigo inimigo = new Inimigo();
      Desenho quadroInimigo = new Desenho(0, 0, 35, 40, 100);
      Desenho quadro2Inimigo = new Desenho(0, 0, 35, 40, 100);
      quadroInimigo.adicionarTextura(Texturas.CAVEIRA);
      quadro2Inimigo.adicionarTextura(Texturas.CAVEIRA2);
      Color corInimigo = new Color(Color.BLACK);
      for(int i=0; i<100; i++){
          inimigo.adicionarQuadro(quadroInimigo);
      }
      for(int i=0; i<100; i++){
          inimigo.adicionarQuadro(quadro2Inimigo);
      }
      
      /*for(int i=0; i<=255; i+=10){
        corInimigo.setBlue(i);
        corInimigo.setRed(i);
        corInimigo.setGreen(i);
        quadroInimigo.mudarCor(corInimigo);
        inimigo.adicionarQuadro(quadroInimigo);
      }
      for(int i=255; 0<=i; i-=10){
        corInimigo.setBlue(i);
        corInimigo.setRed(i);
        corInimigo.setGreen(i);
        quadroInimigo.mudarCor(corInimigo);
        inimigo.adicionarQuadro(quadroInimigo);
      }*/
      Nivel niveis[] = new Nivel[1];
      niveis[0] = new Nivel(inimigo, 10);
      jogo = new Jogo(new Terreno(-Tela.WIDTH/2, -Tela.WIDTH/2, Tela.WIDTH, Tela.WIDTH, 10, 10), niveis);
      Camera.moverCameras(jogo.getTerreno().getPosX(), jogo.getTerreno().getPosY());
      controladorJogo = new ControladorJogo(jogo);
      run();
    } catch(Exception ex) {
      LOGGER.log(Level.SEVERE,ex.toString(),ex);
    }
  }

  public void create() throws LWJGLException {
    
    //Display
    Display.setDisplayMode(new DisplayMode(Tela.WIDTH,Tela.HEIGHT));
    Display.setFullscreen(false);
    Display.setTitle("MLP Tower Defense!");
    Display.create();
    Display.setVSyncEnabled(true);

    //Keyboard
    Keyboard.create();

    //Mouse
    Mouse.setGrabbed(false);
    Mouse.create();

    //OpenGL
    initGL();
    resizeGL();
  }

  public void destroy() {
    //Methods already check if created before destroying.
    Mouse.destroy();
    Keyboard.destroy();
    Display.destroy();
  }

  public void initGL() {
    //2D Initialization
    glClearColor(0.0f,0.0f,0.0f,0.0f);
    glDisable(GL_DEPTH_TEST);
    glDisable(GL_LIGHTING);
    GL11.glEnable(GL11.GL_BLEND);
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    GL11.glEnable(GL11.GL_TEXTURE_2D);

    Texturas.inicializar(); //Tela.inicializar() utiliza texturas.
    Tela.inicializar();
    Camera.inicializar();
    Temporizador.inicializar();

    temporizadorMouse = new Temporizador();
    temporizadorMouse.marcarAgora();
  }

  public void processKeyboard() {
    //Square's Size
    if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            Camera.deslocarCamerasNaDirecaoQueOlham(-5.0f, 5.0f);
        } else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            Camera.deslocarCamerasNaDirecaoQueOlham(5.0f, 5.0f);
        } else {
            Camera.deslocarCamerasNaDirecaoQueOlham(0.0f, 5.0f);
        }
    } else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            Camera.deslocarCamerasNaDirecaoQueOlham(-5.0f, -5.0f);
        } else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            Camera.deslocarCamerasNaDirecaoQueOlham(5.0f, -5.0f);
        } else {
            Camera.deslocarCamerasNaDirecaoQueOlham(0.0f, -5.0f);
        }
    } else if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
        Camera.deslocarCamerasNaDirecaoQueOlham(-5.0f, 0.0f);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
        Camera.deslocarCamerasNaDirecaoQueOlham(5.0f, 0.0f);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_O)){
        Camera.setCamera(Camera.CAMERA.ORTOGRAFICA);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_P)){
        Camera.setCamera(Camera.CAMERA.PERSPECTIVA);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_F)){
        Camera.moverCameras(jogo.getTerreno().getPosX(), jogo.getTerreno().getPosY());
    } else if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
        Camera.rotacionarCameras(-1, 0, 0);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_E)){
        Camera.rotacionarCameras(1, 0, 0);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_A)){
        Camera.rotacionarCameras(0, -1, 0);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_D)){
        Camera.rotacionarCameras(0, 1, 0);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
        Camera.rotacionarCameras(0, 0, -1);
        jogo.getTerreno().rotacionarInimigosEmY(-1);
        jogo.getTerreno().rotacionarTorresEmY(-1);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_C)){
        Camera.rotacionarCameras(0, 0, 1);
        jogo.getTerreno().rotacionarInimigosEmY(1);
        jogo.getTerreno().rotacionarTorresEmY(1);
    }
  }

  public void processMouse() {
      int BOTAO_ESQUERDO_MOUSE = 0;

      if(Mouse.isButtonDown(BOTAO_ESQUERDO_MOUSE)
              && 200 < temporizadorMouse.tempoDesdeUltimaMarcacao()){
          temporizadorMouse.marcarAgora();
          controladorJogo.houveMouseDown();
      }
  }

  public void render() {
    Temporizador.marcarAgoraPrincipal();

    glClear(GL_COLOR_BUFFER_BIT);

    if(Tela.HEIGHT - 5 < Mouse.getY()){
        if(Mouse.getX() < 5){
            Camera.deslocarCamerasNaDirecaoQueOlham(-1.0f, 1.0f);
        } else if(Tela.WIDTH - 5 < Mouse.getX()){
            Camera.deslocarCamerasNaDirecaoQueOlham(1.0f, 1.0f);
        } else {
            Camera.deslocarCamerasNaDirecaoQueOlham(0.0f, 1.0f);
        }
    } else if(Mouse.getY() < 5){
        if(Mouse.getX() < 5){
            Camera.deslocarCamerasNaDirecaoQueOlham(-1.0f, -1.0f);
        } else if(Tela.WIDTH - 5 < Mouse.getX()){
            Camera.deslocarCamerasNaDirecaoQueOlham(1.0f, -1.0f);
        } else {
            Camera.deslocarCamerasNaDirecaoQueOlham(0.0f, -1.0f);
        }
    } else if(Mouse.getX() < 5){
        Camera.deslocarCamerasNaDirecaoQueOlham(-1.0f, 0.0f);
    } else if(Tela.WIDTH - 5 < Mouse.getX()){
        Camera.deslocarCamerasNaDirecaoQueOlham(1.0f, 0.0f);
    }
    jogo.desenhar();
    Tela.getTela().desenhar();
  }

  public void resizeGL() {
    //2D Scene
    Camera.setCamera(Camera.CAMERA.PERSPECTIVA);
    Camera.rotacionarCameras(-45, 0, 0);
  }

  public void run() {
    while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
      if(Display.isVisible()) {
        processKeyboard();
        processMouse();
        update();
        render();
      } else {
        if(Display.isDirty()) {
          render();
        }
        try {
          Thread.sleep(100);
        } catch(InterruptedException ex) { }
      }
      Display.update();
      Display.sync(60);
    }
  }

  public void update() {
    jogo.atualizar();
  }



  
}