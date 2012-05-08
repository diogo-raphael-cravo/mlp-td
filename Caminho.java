/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlp.td;

/**
 * Um caminho por onde os inimigos podem movimentar-se.
 * É constituído por índices de tiles no array tiles de Terreno.
 * Um caminho só possui um início e um fim, sem bifurcações.
 * @author diogo
 */
public class Caminho {
    /**
     * Comprimento, em tiles, do caminho.
     */
    int comprimento;
    int[] linhasTiles;
    int[] colunasTiles;

    /**
     * Cria um novo caminho com o comprimento passado.
     * @param _comprimento O número de tiles que constituí o caminho.
     */
    public Caminho(int _comprimento){
        linhasTiles = new int[_comprimento];
        colunasTiles = new int[_comprimento];
        comprimento = _comprimento;
    }

    /**
     * Adiciona a tile à dada posição do caminho.
     * @param _posicao Posição, no caminho, em que ficará a tile.
     * @param _coluna A coluna na matriz de tiles, que fica em Terreno.
     * @param _linha A linha na matriz de tiles, que fica em Terreno.
     */
    public void adicionarTile(int _posicao, int _coluna, int _linha){
        linhasTiles[_posicao] = _linha;
        colunasTiles[_posicao] = _coluna;
    }

    /**
     * @param _posicao Posição, no caminho, em que fica a tile consultada.
     * @return A linha da tile.
     */
    public int getLinhaTile(int _posicao){
        return linhasTiles[_posicao];
    }

    /**
     * @param _posicao Posição, no caminho, em que fica a tile consultada.
     * @return A coluna da tile.
     */
    public int getColunaTile(int _posicao){
        return colunasTiles[_posicao];
    }

    /**
     * @return O comprimento em tiles deste caminho.
     */
    public int getComprimento(){
        return comprimento;
    }

    /**
     * Cria um caminho em diagonal, de (0,0) até o extremo inferior.
     * @param _comprimento O número de tiles no caminho.
     * @param _colunaMaxima Máximo valor para coluna no terreno de referência.
     * @param _linhaMaxima Máximo valor para linha no terreno de referência.
     */
    public static Caminho criarCaminhoDiagonalDecrescente(int _comprimento, int _colunaMaxima, int _linhaMaxima){
        Caminho caminhoDiagonalDecrescente = new Caminho(_comprimento);
        int posicao;
        for(posicao=0; posicao<_colunaMaxima && posicao<_linhaMaxima; posicao++){
           caminhoDiagonalDecrescente.adicionarTile(posicao, posicao, posicao);
        }
        return caminhoDiagonalDecrescente;
    }
}
