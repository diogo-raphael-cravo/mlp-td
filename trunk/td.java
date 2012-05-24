package mlptd;



import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.BufferUtils;
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

  /**
   * Posição do mouse, para checagem de mudança.
   * Gera o evento mouseMove.
   */
  float xMouse;
  float yMouse;

  Luz luz;
  BarraCarregamento carregamentoJogo;
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
      quadroInimigo.definirTextura(Texturas.CAVEIRA);
      quadro2Inimigo.definirTextura(Texturas.CAVEIRA2);
      Color corInimigo = new Color(Color.BLACK);
      inimigo.adicionarQuadro(quadroInimigo);
      inimigo.adicionarQuadro(quadro2Inimigo);
      inimigo.setQuadrosPorSegundo(2);

      Inimigo segundoInimigo = new Inimigo();
      Desenho quadro1SegundoInimigo = new Desenho(0, 0, 35, 40, 100);
      Desenho quadro2SegundoInimigo = new Desenho(0, 0, 35, 40, 100);
      Desenho quadro3SegundoInimigo = new Desenho(0, 0, 35, 40, 100);
      Desenho quadro4SegundoInimigo = new Desenho(0, 0, 35, 40, 100);
      Desenho quadro5SegundoInimigo = new Desenho(0, 0, 35, 40, 100);
      Desenho quadro6SegundoInimigo = new Desenho(0, 0, 35, 40, 100);
      Desenho quadro7SegundoInimigo = new Desenho(0, 0, 35, 40, 100);
      Desenho quadro8SegundoInimigo = new Desenho(0, 0, 35, 40, 100);
      Desenho quadro9SegundoInimigo = new Desenho(0, 0, 35, 40, 100);
      quadro1SegundoInimigo.definirTextura(Texturas.SLIMEKING[0]);
      quadro2SegundoInimigo.definirTextura(Texturas.SLIMEKING[1]);
      quadro3SegundoInimigo.definirTextura(Texturas.SLIMEKING[2]);
      quadro4SegundoInimigo.definirTextura(Texturas.SLIMEKING[3]);
      quadro5SegundoInimigo.definirTextura(Texturas.SLIMEKING[4]);
      quadro6SegundoInimigo.definirTextura(Texturas.SLIMEKING[5]);
      quadro7SegundoInimigo.definirTextura(Texturas.SLIMEKING[6]);
      quadro8SegundoInimigo.definirTextura(Texturas.SLIMEKING[7]);
      quadro9SegundoInimigo.definirTextura(Texturas.SLIMEKING[8]);
      segundoInimigo.adicionarQuadro(quadro1SegundoInimigo);
      segundoInimigo.adicionarQuadro(quadro2SegundoInimigo);
      segundoInimigo.adicionarQuadro(quadro3SegundoInimigo);
      segundoInimigo.adicionarQuadro(quadro4SegundoInimigo);
      segundoInimigo.adicionarQuadro(quadro5SegundoInimigo);
      segundoInimigo.adicionarQuadro(quadro6SegundoInimigo);
      segundoInimigo.adicionarQuadro(quadro7SegundoInimigo);
      segundoInimigo.adicionarQuadro(quadro8SegundoInimigo);
      segundoInimigo.adicionarQuadro(quadro9SegundoInimigo);
      segundoInimigo.setQuadrosPorSegundo(3);

      Nivel niveis[] = new Nivel[2];
      niveis[0] = new Nivel(segundoInimigo, 10);
      niveis[1] = new Nivel(inimigo, 10);
      jogo = new Jogo(new Terreno(-Tela.WIDTH/2, -Tela.WIDTH/2, Tela.WIDTH, Tela.WIDTH, 10, 10), niveis);
      Camera.moverCameras(jogo.getTerreno().getPosX(), jogo.getTerreno().getPosY());
      controladorJogo = new ControladorJogo(jogo);
      jogo.getTerreno().adicionarFilho(new Circulo(100), 0, 0);
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
    xMouse = Mouse.getX();
    yMouse = Mouse.getY();
      
    //2D Initialization
    glClearColor(0.0f,0.0f,0.0f,0.0f);
    glDisable(GL_DEPTH_TEST);                        // Enables Depth Testing
    
    //Dica encontrada em http://forum.codecall.net/topic/66017-simple-lwjgl-lighting/
    luz = new Luz();
    luz.rotacionar(45f, -45f, 0f);
                
    glShadeModel(GL_SMOOTH);
    glMaterial(GL_FRONT, GL_SPECULAR, luz.getEspecular()); // sets specular material color
    glMaterialf(GL_FRONT, GL_SHININESS, 50.0f); // sets shininess

    glLight(GL_LIGHT0, GL_POSITION, luz.getPosicao());       // sets light position
    glLight(GL_LIGHT0, GL_SPECULAR, luz.getBranco());        // sets specular light to white
    glLight(GL_LIGHT0, GL_DIFFUSE, luz.getBranco());         // sets diffuse light to white
    glLightModel(GL_LIGHT_MODEL_AMBIENT, luz.getAmbiente()); // global ambient light

    glEnable(GL_LIGHTING);                                                  
    glEnable(GL_LIGHT0);                                                    

    glEnable(GL_COLOR_MATERIAL);  // enables opengl to use glColor3f to define material color
    glColorMaterial(GL_FRONT, GL_AMBIENT_AND_DIFFUSE); // tell opengl glColor3f effects the ambient and diffuse properties of material

    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    glEnable(GL_TEXTURE_2D);

    Texturas.inicializar(); //Tela.inicializar() utiliza texturas.
    Texto.inicializar(); //Tela.inicializar() utiliza textos.
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
        luz.rotacionar(1, 0, 0);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_E)){
        Camera.rotacionarCameras(1, 0, 0);
        luz.rotacionar(-1, 0, 0);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_A)){
        Camera.rotacionarCameras(0, -1, 0);
        luz.rotacionar(0, -1, 0);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_D)){
        Camera.rotacionarCameras(0, 1, 0);
        luz.rotacionar(0, 1, 0);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
        Camera.rotacionarCameras(0, 0, -1);
        jogo.getTerreno().rotacionarInimigosEmY(-1);
        jogo.getTerreno().rotacionarTorresEmY(-1);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_C)){
        Camera.rotacionarCameras(0, 0, 1);
        jogo.getTerreno().rotacionarInimigosEmY(1);
        jogo.getTerreno().rotacionarTorresEmY(1);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_ADD)
            || (Keyboard.isKeyDown(Keyboard.KEY_EQUALS)
                && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))){
        Camera.zoom(-5.0f);
    } else if(Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT)
            || Keyboard.isKeyDown(Keyboard.KEY_MINUS)){
        Camera.zoom(5.0f);
    }
  }

  public void processMouse() {
      int BOTAO_ESQUERDO_MOUSE = 0;
      boolean mouseMudouSuaPosicao = (xMouse != Mouse.getX() || yMouse != Mouse.getY());

      if(mouseMudouSuaPosicao){
          xMouse = Mouse.getX();
          yMouse = Mouse.getY();
          controladorJogo.mouseMoveu();
      }
      
      if(Mouse.isButtonDown(BOTAO_ESQUERDO_MOUSE)
              && 200 < temporizadorMouse.tempoDesdeUltimaMarcacao()){
          temporizadorMouse.marcarAgora();
          controladorJogo.houveMouseDown();
      }
  }

  public void render() {
    Temporizador.marcarAgoraPrincipal();

    glClear(GL_COLOR_BUFFER_BIT);

    luz.desenhar();

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