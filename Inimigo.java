/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import org.lwjgl.util.Color;

/**
 *
 * @author diogo
 */
public class Inimigo extends Filme {
    /**
     * Vida máxima deste inimigo.
     * Existe para permitir cópia.
     */
    private float vidaMaxima;
    
    /**
     * Vida atual do inimigo.
     * Ao se esgotar, ele morre.
     */
    private float vida;
    
    /**
     * Barra que mede a vida visualmente.
     */
    private BarraCarregamento barraVida;
    
    /**
     * Velocidade do inimigo em Tiles por segundo.
     * Uma tile é medida pelo seu comprimento.
     * Assim, este é o número de tiles que o inimigo pode 
     * cruzar por segundo em rota horizontal.
     * Caso a rota possua componente no eixo y, o número de
     * tiles realmente passadas por segundo será um pouco menor.
     */
    private float velocidadeTilesSegundo;

    /**
     * @param _posX, _posY A posição do ponto superior esquerdo na tela.
     * @param _comprimento, _largura Comprimento e largura.
     */
    public Inimigo(){
        this(0, 0, 10, 10);
    }
    public Inimigo(float _posX, float _posY, float _comprimento, float _largura){
         super(_posX, _posY, _comprimento, _largura);
         cor = new Color(Color.WHITE);
         velocidadeTilesSegundo = 3;
         vidaMaxima = 100;
         vida = 100;
         barraVida = new BarraCarregamento(0, 0, 100, 20);
         barraVida.carregarCom(BarraCarregamento.VALOR_MAXIMO);
         adicionarFilho(barraVida, 0, 0);
    }
    public Inimigo(Inimigo _inimigo){
         super(_inimigo);
         
         Desenho quadro;
         float comprimentoQuadro;
         float larguraQuadro;
         
         if(getQuadros().isEmpty()){
             comprimentoQuadro = 100;
             larguraQuadro = 20;
         } else {
             quadro = getQuadros().firstElement();
             comprimentoQuadro = quadro.comprimento;
             larguraQuadro = quadro.largura;
         }
         
         velocidadeTilesSegundo = _inimigo.getVelocidadeTilesPorSegundo();
         vidaMaxima = _inimigo.getVidaMaxima();
         vida = _inimigo.getVida();
         barraVida = new BarraCarregamento(0, 0, comprimentoQuadro, larguraQuadro/10);
         barraVida.carregarCom(BarraCarregamento.VALOR_MAXIMO*_inimigo.getVida()/_inimigo.getVidaMaxima());
         adicionarFilho(barraVida, 0, larguraQuadro);
    }

    public float getVidaMaxima(){
        return vidaMaxima;
    }
    public float getVida(){
        return vida;
    }
    
    /**
     * @return Velocidade do inimigo em Tiles por segundo.
     */
     public float getVelocidadeTilesPorSegundo(){
         return velocidadeTilesSegundo;
     }

}
