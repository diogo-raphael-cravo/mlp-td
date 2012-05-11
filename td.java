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
  public static final int DISPLAY_HEIGHT = 480;
  public static final int DISPLAY_WIDTH = 640;
  public static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  Jogo jogo;
  ControladorJogo controladorJogo;

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
      Inimigo inimigo = new Inimigo();
      Desenho quadroInimigo = new Desenho(0, 0, 10, 10, 100);
      Color corInimigo = new Color(Color.BLACK);
      for(int i=0; i<=255; i+=10){
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
      }
      Nivel niveis[] = new Nivel[1];
      niveis[0] = new Nivel(inimigo, 10);
      jogo = new Jogo(new Terreno(150, 50, 400, 400, 10, 10), niveis);
      controladorJogo = new ControladorJogo(jogo);
      create();
      run();
    } catch(Exception ex) {
      LOGGER.log(Level.SEVERE,ex.toString(),ex);
    }
  }

  public void create() throws LWJGLException {
    
    //Display
    Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH,DISPLAY_HEIGHT));
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

    Tela.inicializar();
    Camera.inicializar();
    Temporizador.inicializar();
  }

  public void processKeyboard() {
    //Square's Size
    if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
     
    }
  }

  public void processMouse() {
      int BOTAO_ESQUERDO_MOUSE = 0;
      if(Mouse.isButtonDown(BOTAO_ESQUERDO_MOUSE)){
        controladorJogo.houveMouseDown();
      }
  }

  public void render() {
    Temporizador.marcarAgoraPrincipal();

    glClear(GL_COLOR_BUFFER_BIT);
    glLoadIdentity();

    if(Mouse.getX() < 5){
        Camera.ortografica.deslocar(-1.0f, 0.0f);
    } else if(Mouse.getY() < 5){
        Camera.ortografica.deslocar(0.0f, -1.0f);
    } else if(DISPLAY_WIDTH - 5 < Mouse.getX()){
        Camera.ortografica.deslocar(1.0f, 0.0f);
    } else if(DISPLAY_HEIGHT - 5 < Mouse.getY()){
        Camera.ortografica.deslocar(0.0f, 1.0f);
    }
    jogo.desenhar();
    Tela.getTela().desenhar();

  }

  public void resizeGL() {
    //2D Scene
    Camera.setCamera(Camera.CAMERA.ORTOGRAFICA);
  }

  public void run() {
    while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
      if(Display.isVisible()) {
        processKeyboard();
        processMouse();
        update();
        render();
      }
      else {
        if(Display.isDirty()) {
          render();
        }
        try {
          Thread.sleep(100);
        }
        catch(InterruptedException ex) {
        }
      }
      Display.update();
      Display.sync(60);
    }
  }

  public void update() {
    jogo.atualizar();
  }



  
}