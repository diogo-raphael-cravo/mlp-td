/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp.td;

import org.lwjgl.util.Color;

/**
 * Uma barra de carregamento, que pode ser usada tanto para o início do jogo
 * quanto para a construção de torres.
 * @author diogo
 */
public class BarraCarregamento extends Desenho {
    /**
     * Valor máximo assumido pela barra.
     */
    public static final float VALOR_MAXIMO = 100;
    
    /**
     * Valor carregado da barra.
     */
    private float valorCarregado;
    
    /**
     * A barra sempre inicia vazia.
     * @param _posX, _posY A posição do ponto superior esquerdo na tela.
     * @param _comprimento O comprimento da barra quando está totalmente carregada.
     * @param _largura Largura do desenho.
     */
    public BarraCarregamento(float _posX, float _posY, float _comprimento, float _largura){
         super(_posX, _posY, _comprimento, _largura);
         valorCarregado = 0;
         cor = new Color(Color.GREEN);
    }
    
    public float getValorCarregado(){
        return valorCarregado;
    }
    
    /**
     * Carrega a barra com o valor dado. Se o valor for maior que a capacidade,
     * a barra fica cheia. Se o valor dado for menor que o valor já carregado,
     * fica-se com o valor de parâmetro.
     * @param _valorTotalBarra Valor com o qual a barra será preenchida.
     */
    public void carregarAteh(float _valorTotalBarra){
        if(_valorTotalBarra < VALOR_MAXIMO){
            valorCarregado = _valorTotalBarra;
        } else {
            valorCarregado = VALOR_MAXIMO;
        }
    }
    
    /**
     * Adiciona o valor ao que já foi carregado. Se o valor for maior que a capacidade,
     * a barra fica cheia.
     * @param _valorAdicao Valor a ser adicionado.
     */
    public void carregarCom(float _valorAdicao){
        if(valorCarregado+_valorAdicao < VALOR_MAXIMO){
            valorCarregado += _valorAdicao;
        } else {
            valorCarregado = VALOR_MAXIMO;
        }
    }
    
    /**
     * Desenha na tela com base em seu tamanho e posicao.
     * O objeto desenhado é um retângulo.
     */
    public void desenhar(){
        float xFatorEscalaAtual = fatorEscalaX;
        if(0 < valorCarregado){
            fatorEscalaX *= valorCarregado/VALOR_MAXIMO;
        }
        super.desenhar();
        fatorEscalaX = xFatorEscalaAtual;
    }
}
