/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

/**
 * Tile em que se pode construir torres.
 * @author diogo
 */
public class TileEdificavel extends Tile{
    /**
     * Torre edificada nesta tile.
     */

    /**
     * @param _posX, _posY A posição do ponto superior esquerdo na tela.
     * @param _comprimento, _largura Comprimento e largura.
     * @param _tamanhoEmPorcentagem Usado para resize.
     */
    public TileEdificavel(float _posX, float _posY, float _comprimento, float _largura, int _tamanhoEmPorcentagem){
        super(_posX, _posY, _comprimento, _largura, _tamanhoEmPorcentagem);
        adicionarTextura(Arquivos.ARQUIVO_TEXTURA_GRAMA);
    }

    
    
}
