/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlptd;

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
     * O caminho é algo do tipo:
     * 1 0 0 0
     * 0 1 0 0
     * 0 0 1 0
     * 0 0 0 1
     */
    public static Caminho criarCaminhoDiagonalDecrescente(int _comprimento, int _colunaMaxima, int _linhaMaxima){
        Caminho caminhoDiagonalDecrescente = new Caminho(_comprimento);
        int posicao;
        for(posicao=0; posicao<_colunaMaxima && posicao<_linhaMaxima; posicao++){
           caminhoDiagonalDecrescente.adicionarTile(posicao, posicao, posicao);
        }
        return caminhoDiagonalDecrescente;
    }

    /**
     * Cria um caminho que ocupa linhas alternadas, de (0,0) até o extremo inferior direito.
     * @param _comprimento O número de tiles no caminho.
     * @param _colunaMaxima Máximo valor para coluna no terreno de referência.
     * @param _linhaMaxima Máximo valor para linha no terreno de referência.
     * O caminho é algo do tipo:
     * 1 1 1 1
     * 0 0 0 1
     * 1 1 1 1
     * 1 0 0 0
     * 1 1 1 1
     *
     *   0 1 2 3
     * 0 0 1 2 3
     * 1 - - - 4
     * 2 8 7 6 5
     * 3 X - - -
     * 4 X X X X
     * 5 - - - X
     * 6 15X X X
     * 7 X - - -
     * 8 X X X X
     * 9 - - - X
     */
    public static Caminho criarCaminhoLinhasAlternadas(int _colunaMaxima, int _linhaMaxima){
        int comprimentoCaminho = (_colunaMaxima+1)*(_linhaMaxima/2);
        Caminho caminhoLinhasAlternadas = new Caminho(comprimentoCaminho);
        int posicao=0;
        int linha;
        int coluna;
        for(linha=0; linha<_linhaMaxima; linha++){
            for(coluna=0; coluna<_colunaMaxima; coluna++){
                if((linha%2 == 0 && linha%4==0)){
                    caminhoLinhasAlternadas.adicionarTile(posicao, coluna, linha);
                    posicao++;
                } else if((linha%2 == 0 && linha%4==2)){
                    posicao = ((linha/2)*(_colunaMaxima+1))-coluna;
                    caminhoLinhasAlternadas.adicionarTile(posicao, coluna, linha);
                    posicao = ((linha/2)*(_colunaMaxima+1));
                } else if(linha%2 == 1 && linha%4==1 && coluna == _colunaMaxima-1){
                    caminhoLinhasAlternadas.adicionarTile(posicao, coluna, linha);
                    posicao++;
                } else if(linha%2 == 1 && linha%4==3 && coluna == 0){
                    caminhoLinhasAlternadas.adicionarTile(posicao, coluna, linha);
                    posicao++;
                }
            }
        }
        return caminhoLinhasAlternadas;
    }
}
