/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import java.util.Vector;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author diogo
 */
public class TilePassadouro extends Tile{
    /**
     * Indica se a tile está ocupada por algum inimigo.
     */
    boolean ocupada;

    /**
     * Array com inimigos que ocupam esta tile.
     */
    Vector inimigosQueEstaoAqui;

    public TilePassadouro(float _posX, float _posY, float _comprimento, float _largura){
        super(_posX, _posY, _comprimento, _largura);
        ocupada = false;
        inimigosQueEstaoAqui = new Vector();
    }
    public TilePassadouro(float _posX, float _posY, float _comprimento, float _largura, int _tamanhoEmPorcentagem){
        super(_posX, _posY, _comprimento, _largura, _tamanhoEmPorcentagem);
        ocupada = false;
        inimigosQueEstaoAqui = new Vector();
    }
    public TilePassadouro(Tile _tile){
        super(_tile.getPosX(), _tile.getPosY(), _tile.getComprimento(), _tile.getLargura(), _tile.getTamanhoEmPorcentagem());
        mudarCor(_tile.getCor());
        ocupada = false;
        inimigosQueEstaoAqui = new Vector();
    }

    /**
     * Adiciona o inimigo a esta tile.
     * @param _inimigo Inimigo que será adicionado.
     */
     public void adicionarInimigo(Inimigo _inimigo){
        inimigosQueEstaoAqui.add(_inimigo);
    }

    /**
     * Retira todos inimigos desta tile.
     */
     public void retirarTodosInimigos(){
         inimigosQueEstaoAqui = new Vector();
     }

    /**
     * Desenha a tile na tela com base em seu tamanho e posição.
     */
    public void desenhar(){
        super.desenhar();
        Inimigo inimigoSendoDesenhado;
        for(Object inimigo : inimigosQueEstaoAqui){
            inimigoSendoDesenhado = (Inimigo) inimigo;
            inimigoSendoDesenhado.desenhar(posX, posY);
        }
    }

    
}
