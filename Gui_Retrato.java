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
    private Filme filmeExibido;

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
        float xCentro;
        float yCentro;
        float margemX = 10;
        float margemY = 10;
        if(comprimento-margemX < _desenho.comprimento){
            _desenho.comprimento = comprimento-margemX;
        }
        if(largura-margemY < _desenho.largura){
            _desenho.largura = largura-margemY;
        }
        xCentro = comprimento/2 - _desenho.comprimento/2;
        yCentro = largura/2 - _desenho.largura/2;
        desenhoExibido = new Desenho(_desenho);
        desenhoExibido.restaurarTransparencia();
        adicionarFilho(desenhoExibido, xCentro, yCentro);
    }
    public void exibir(Filme _filme){
        float xCentro = comprimento/2 - _filme.getQuadros().elementAt(0).getComprimento()/2;
        float yCentro = largura/2 - _filme.getQuadros().elementAt(0).getLargura()/2;
        filmeExibido = new Filme(_filme);
        filmeExibido.restaurarTransparencia();
        adicionarFilho(filmeExibido, xCentro, yCentro);
    }

    /**
     * Limpa o desenho exibido.
     */
    public void retirarDesenhoExibido(){
        desenhoExibido = null;
        filmeExibido = null;
        filhos = new Vector<Desenho>();
    }
    
}
