/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import java.awt.Font;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.util.glu.GLU.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.opengl.Texture;
import java.io.IOException;
import java.util.Vector;
import org.lwjgl.util.Color;
import static org.lwjgl.opengl.GL11.*;


/**
 * Classe que contém todos os elementos de interface, os quais precisam manter-se na tela o tempo todo.
 * Esta classe não é instanciável e possui somente funções estáticas.
 * @author drcravo
 */
public class Tela extends Desenho {
    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    /**
     * A única tela existente, que contém todos elementos de interface.
     */
    private static Tela tela;

    /**
     * Barra de informações que fica na base da tela.
     */
    private Gui_BarraInferior barraInformacoesInferior;

    /**
     * Barra de informações que fica no topo da tela.
     */
    private Gui_BarraSuperior barraInformacoesSuperior;

    /**
     * Exibe a quantidade de frames por segundo a que o projeto está rodando.
     */
    private CaixaDeTexto ctFramesPorSegundo;

    /**
     * Faz as inicializações necessárias para a tela.
     */
    public static void inicializar(){
        tela = new Tela();
        tela.inicializarInstancia();
    }

    /**
     * Inicializações privadas de cada elemento da classe.
     */
    private void inicializarInstancia(){
       barraInformacoesInferior = new Gui_BarraInferior();
       adicionarFilho(barraInformacoesInferior, 0.0f, 0.0f);

       barraInformacoesSuperior = new Gui_BarraSuperior();
       adicionarFilho(barraInformacoesSuperior, 0.0f, Tela.HEIGHT-barraInformacoesSuperior.largura);

       ctFramesPorSegundo = new CaixaDeTexto(200, 0);
       ctFramesPorSegundo.setTexto("Frames por segundo> ");
       ctFramesPorSegundo.setFonte(new Font("Lucida Console", Font.LAYOUT_LEFT_TO_RIGHT, 8));
       adicionarFilho(ctFramesPorSegundo, 0.0f, 0.0f);
    }

    private Tela(){
        super(0, 0, 0, 0, 100);
    }

    /**
     * @return A única tela existente. Retorna-se a própria, não uma cópia.
     */
    public static Tela getTela(){
        return tela;
    }

    public static Gui_BarraInferior getGuiBarraInferior(){
        return tela.barraInformacoesInferior;
    }
    public static Gui_BarraSuperior getGuiBarraSuperior(){
        return tela.barraInformacoesSuperior;
    }

    /**
     * Desenha na tela com base em seu tamanho e posicao.
     * O objeto desenhado é um retângulo.
     */
    public void desenhar(){
        glDisable(GL_LIGHTING);
        Camera.CAMERA cameraUsada = Camera.cameraAtiva();
        Camera.setCamera(Camera.CAMERA.ORTOGRAFICA_ESTATICA);
        super.desenhar();
        Camera.setCamera(cameraUsada);
        if(Temporizador.frameRate() == Integer.MAX_VALUE){
            ctFramesPorSegundo.setTexto("Frames por segundo> MÁXIMO");
        } else {
            ctFramesPorSegundo.setTexto("Frames por segundo> "+Temporizador.frameRate());
        }
        glEnable(GL_LIGHTING);
    }


}
