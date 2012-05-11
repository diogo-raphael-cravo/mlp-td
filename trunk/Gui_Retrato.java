/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import java.util.Vector;
import org.lwjgl.util.Color;

/**
 * Elemento da gui que é capaz de conter um retrato de algum desenho.
 * Utilizado em Gui_BarraInferior para mostrar o elemento que está selecionado.
 * @author drcravo
 */
public class Gui_Retrato extends Desenho {

    /**
     * Desenho mostrado por este retrato.
     */
    private Desenho desenhoExibido;

    /**
     * @param _posX, _posY A posi��o do ponto superior esquerdo na tela.
     * @param _comprimento, _largura, _altura Comprimento, largura e altura.
     * @param _tamanhoEmPorcentagem Usado para resize.
     */
    public Gui_Retrato(float _posX, float _posY, float _comprimento, float _largura,
            int _tamanhoEmPorcentagem){
        super(_posX, _posY, _comprimento, _largura, _tamanhoEmPorcentagem);
        cor = new Color(Color.BLACK);
        desenhoExibido = null;
    }

    /**
     * Exibe este desenho neste retrato.
     * Se houver desenho exibido, retira-o e desenha o de parâmetro.
     * @param _desenho Desenho a ser exibido.
     */
    public void exibir(Desenho _desenho){
        float xCentro = xCentroParaDesenho(_desenho) - posX;
        float yCentro = yCentroParaDesenho(_desenho) - posY;
        desenhoExibido = new Desenho(_desenho);
        desenhoExibido.restaurarTransparencia();
        adicionarFilho(desenhoExibido, xCentro, yCentro);
    }

    /**
     * Limpa o desenho exibido.
     */
    public void retirarDesenhoExibido(){
        desenhoExibido = null;
        filhos = new Vector<Desenho>();
    }

    /**
     * Desenha na tela com base em seu tamanho e posicao.
     * O objeto desenhado é um retângulo.
     */
    public void desenhar(){
        super.desenhar();
    }
    
}
