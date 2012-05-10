/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

import java.util.Vector;
import org.lwjgl.util.Color;

/**
 *
 * @author diogo
 */
public class TilePassadouro extends Tile{
    /**
     * Tipos de tiles passadouros.
     */
    public static enum TIPO_PASSADOURO{TERRA, AGUA};

    /**
     * Indica se a tile está ocupada por algum inimigo.
     */
    boolean ocupada;

    /**
     * Inimigo que ocupa esta tile.
     * A variável é atualizada para null quando o inimigo excede os limites.
     * O inimigo não é desenhado por esta classe.
     */
    Inimigo inimigoQueEstahAqui;

    /**
     * O tipo do passadouro, que define que tipos de inimigos podem passar por ele.
     */
    private TIPO_PASSADOURO tipo;

    public TilePassadouro(float _posX, float _posY, float _comprimento, float _largura){
        super(_posX, _posY, _comprimento, _largura);
        ocupada = false;
        setTipo(TIPO_PASSADOURO.TERRA);
    }
    public TilePassadouro(float _posX, float _posY, float _comprimento, float _largura, int _tamanhoEmPorcentagem){
        this(_posX, _posY, _comprimento, _largura);
    }
    public TilePassadouro(Tile _tile){
        super(_tile.getPosX(), _tile.getPosY(), _tile.getComprimento(), _tile.getLargura(), _tile.getTamanhoEmPorcentagem());
        mudarCor(_tile.getCor());
        setTipo(TIPO_PASSADOURO.TERRA);
        ocupada = false;
    }
    public TilePassadouro(TilePassadouro _tile){
        super(_tile.getPosX(), _tile.getPosY(), _tile.getComprimento(), _tile.getLargura(), _tile.getTamanhoEmPorcentagem());
        mudarCor(_tile.getCor());
        setTipo(_tile.getTipo());
        ocupada = false;
    }

    /**
     * Adiciona o inimigo a esta tile. O inimigo é o próprio, não uma cópia.
     * Deste modo, ele é sempre desenhado em sua posição correta.
     * @param _inimigo Inimigo que será adicionado.
     */
     public void adicionarInimigo(Inimigo _inimigo){
        inimigoQueEstahAqui = _inimigo;
     }

    /**
     * Retira todos inimigos desta tile.
     */
     public void retirarTodosInimigos(){
         inimigoQueEstahAqui = null;
     }

     public TIPO_PASSADOURO getTipo(){
         TIPO_PASSADOURO tipoReturn = tipo;
         return tipoReturn;
     }
     public void setTipo(TIPO_PASSADOURO _tipo){
         tipo = _tipo;
         if(tipo == TIPO_PASSADOURO.TERRA){
             cor = new Color(Color.GREEN);
         } else if(tipo == TIPO_PASSADOURO.AGUA){
             cor = new Color(Color.BLUE);
         }
     }

    /**
     * Desenha a tile na tela com base em seu tamanho e posição.
     */
    @Override
    public void desenhar(){
        super.desenhar();
    }
    
}
