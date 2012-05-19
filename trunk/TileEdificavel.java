/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mlp.td;

import java.util.Vector;

/**
 * Tile em que se pode construir torres.
 * @author diogo
 */
public class TileEdificavel extends Tile{
    /**
     * Torre edificada nesta tile.
     */
    private Torre torre;

    /**
     * Array que contém todos os filmes criados.
     * Utilizado para aplicação de eventos.
     */
    private static Vector<TileEdificavel> todasTilesEdificaveisCriadas;
    
    /**
     * @param _posX, _posY A posição do ponto superior esquerdo na tela.
     * @param _comprimento, _largura Comprimento e largura.
     * @param _tamanhoEmPorcentagem Usado para resize.
     */
    public TileEdificavel(float _posX, float _posY, float _comprimento, float _largura){
        super(_posX, _posY, _comprimento, _largura);
        definirTextura(Arquivos.ARQUIVO_TEXTURA_GRAMA);
        torre = null;
        if(todasTilesEdificaveisCriadas == null){
            todasTilesEdificaveisCriadas = new Vector<TileEdificavel>();
        }
    }
    public TileEdificavel(TileEdificavel _tile){
        super(_tile);
        filhos = new Vector<Desenho>();
        definirTextura(Arquivos.ARQUIVO_TEXTURA_GRAMA);
        if(_tile.ocupadaPorTorre()){
            construirTorre(new Torre(_tile.getTorre()));
            torre.rotacionar(-90, 0, 0);
        } else {
            torre = null;
        }
        if(todasTilesEdificaveisCriadas == null){
            todasTilesEdificaveisCriadas = new Vector<TileEdificavel>();
        }
    }

    /**
     * Registra os eventos de mouse.
     * Uma TileEdificavel só obedecerá a eventos quando esta função for invocada nele.
     */
    public void inicializarEventos(){
        todasTilesEdificaveisCriadas.add(this);
    }

    /**
     * @return Array com todos os tiles edificáveis já criados. Útil para aplicação de eventos.
     */
    public static Vector<TileEdificavel> getTodasTilesEdificaveisCriadas(){
        return todasTilesEdificaveisCriadas;
    }

    /**
     * Constrói uma torre nesta tile.
     * @param _torre A torre que deve ser construída.
     */
    public void construirTorre(Torre _torre){
        torre = new Torre(_torre);
        torre.rotacionar(90, 0, 0);
        adicionarFilho(torre, 10, 10);
        adicionarFilho(torre.getProjetil(), 10, 10);
    }
    
    /**
     * Caso haja, destrói a torre desta tile.
     */
    public void destruirTorre(){
        if(ocupadaPorTorre()){
            removerFilho(torre);
            torre = null;
        }
    }

    /**
     * @return A torre que possui, caso haja. Caso contrário, retorna null.
     */
    public Torre getTorre(){
        if(ocupadaPorTorre()){
            return torre;
        } else {
            return null;
        }
    }

    /**
     * @return Booleano indicando se a tile está ocupada por alguma torre.
     */
    public boolean ocupadaPorTorre(){
        boolean estah_ocupadaPorTorre = false;
        if(torre != null){
            estah_ocupadaPorTorre = true;
        }
        return estah_ocupadaPorTorre;
    }

    /**
     * Destrói este tile edificável, retirando-o da lista de tiles edificáveis existentes.
     */
    public void destruir() {
        int index=0;
        int indexDesteDesenho=0;
        boolean desenhoEncontrado = false;
        for(Desenho desenhoCriado : todasTilesEdificaveisCriadas){
            if(desenhoCriado.getIdentificacaoUnica() == getIdentificacaoUnica()){
                indexDesteDesenho = index;
                desenhoEncontrado = true;
            }
            index++;
        }
        if(desenhoEncontrado){
            todasTilesEdificaveisCriadas.remove(indexDesteDesenho);
        }
    }

}
