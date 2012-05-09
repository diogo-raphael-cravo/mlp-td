package mlptd;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

import java.io.IOException;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * @author jediTofu
 * @see <a href="http://lwjgl.org/">LWJGL Home Page</a>
 */
public class td {
  public static final int DISPLAY_HEIGHT = 480;
  public static final int DISPLAY_WIDTH = 640;
  public static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  Terreno terrenoExemplo;

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
      terrenoExemplo = new Terreno(150, 50, 400, 400, 10, 10);
      create();
      run();
    } catch(Exception ex) {
      LOGGER.log(Level.SEVERE,ex.toString(),ex);
    }
  }

  public void create() throws LWJGLException {
    Tela.inicializar();
    Camera.inicializar();

    //Display
    Display.setDisplayMode(new DisplayMode(DISPLAY_WIDTH,DISPLAY_HEIGHT));
    Display.setFullscreen(false);
    Display.setTitle("MLP Tower Defense!");
    Display.create();

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
  }

  public void processKeyboard() {
    //Square's Size
    if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
     
    }
  }

  public void processMouse() {
      int BOTAO_ESQUERDO_MOUSE = 0;
      float mouseX = Tela.xTelaParaGlobal(Mouse.getX());
      float mouseY = Tela.yTelaParaGlobal(Mouse.getY());
      if(Mouse.isButtonDown(BOTAO_ESQUERDO_MOUSE)){
        Vector<Desenho> todosDesenhosCriados = Desenho.getTodosDesenhosCriados();
        for(Desenho desenho : todosDesenhosCriados){
            if(desenho.contem(mouseX, mouseY)){
                desenho.houveMouseDown();
            }
        }
      }
  }

  public void render() {
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
    terrenoExemplo.desenhar();
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
      try {
          terrenoExemplo.moverInimigos();
      } catch (InterruptedException ex) {
          Logger.getLogger(td.class.getName()).log(Level.SEVERE, null, ex);
      }
     /*try {
          Thread.sleep(500);
      } catch (InterruptedException ex) {
          Logger.getLogger(td.class.getName()).log(Level.SEVERE, null, ex);
      }*/
  }



  
}