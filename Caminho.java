package mlptd;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



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
    private int comprimento;
    private int[] linhasTiles;
    private int[] colunasTiles;

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
     * @param _coluna Coluna a ser testada.
     * @param _linha Linha a ser testada.
     * @return Booleano indicando se o caminho contém a posição dada.
     */
    public boolean contem(int _coluna, int _linha){
        boolean contem = false;
        int posicaoAtual;
        for(posicaoAtual=0; 
            posicaoAtual<linhasTiles.length && posicaoAtual<colunasTiles.length;
            posicaoAtual++){
            if(colunasTiles[posicaoAtual] == _coluna &&
               linhasTiles[posicaoAtual] == _linha){
                contem = true;
            }
        }
        return contem;
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
     */
    public static Caminho criarCaminhoLinhasAlternadas(int _colunaMaxima, int _linhaMaxima){
        int comprimentoCaminho = (_colunaMaxima+1)*(_linhaMaxima/2);
        Caminho caminhoLinhasAlternadas = new Caminho(comprimentoCaminho);
        int posicao=0;
        int linha;
        int coluna;
        int posicaoAuxiliar;
        for(linha=0; linha<_linhaMaxima; linha++){
            for(coluna=0; coluna<_colunaMaxima; coluna++){
                if((linha%4==0)){
                    caminhoLinhasAlternadas.adicionarTile(posicao, coluna, linha);
                    posicao++;
                } else if((linha%4==2)){
                    posicaoAuxiliar = posicao + (_colunaMaxima - (2*coluna+1));
                    caminhoLinhasAlternadas.adicionarTile(posicaoAuxiliar, coluna, linha);
                    posicao++;
                } else if(linha%4==1 && coluna == _colunaMaxima-1){
                    caminhoLinhasAlternadas.adicionarTile(posicao, coluna, linha);
                    posicao++;
                } else if(linha%4==3 && coluna == 0){
                    caminhoLinhasAlternadas.adicionarTile(posicao, coluna, linha);
                    posicao++;
                }
            }
        }
        
        return caminhoLinhasAlternadas;
    }
}
